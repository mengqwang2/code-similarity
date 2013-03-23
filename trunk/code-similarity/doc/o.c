#include <time.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdarg.h>

typedef int bool;
enum { false, true };

void procs_create_msg(char* who, int pid, int ppid, time_t timer) {
    
    char buffer[25];
    struct tm* tm_info;

    time(&timer);
    tm_info = localtime(&timer);

    strftime(buffer, 25, "%H:%M:%S", tm_info);
    printf("[%s] %s (%d) created. Its parent is %d.\n", 
        buffer, who, pid, ppid);
}

// Get user time of a process
double getUsertime(void)        
{
    struct timeval tim;        
    struct rusage ru;        
    getrusage(RUSAGE_SELF, &ru);        
    tim=ru.ru_utime;        
    double t=(double)tim.tv_sec + (double)tim.tv_usec / 1000000.0;             
    return t; 
}

// Get system time of a process
double getSystime(void)        
{
    struct timeval tim;        
    struct rusage ru;        
    getrusage(RUSAGE_SELF, &ru);        
    tim=ru.ru_stime;        
    double t=(double)tim.tv_sec + (double)tim.tv_usec / 1000000.0;             
    return t; 
} 

void procs_exit_msg(char* who, int pid) {

    char buffer[25];
    struct tm* tm_info;
    time_t timer;

    time(&timer);
    tm_info = localtime(&timer);

    strftime(buffer, 25, "%H:%M:%S", tm_info);
    printf("[%s] %s (%d) terminated. \n\tTime spend: System time: %f seconds. User time: %f.\n",
        buffer, who, pid, getSystime(), getUsertime()); 
}

void fork_err_msg() {
    perror("Fork error");
    exit(1);
}

// find the max elements among input
int max(int n, ...) {
    int max = -1;
    va_list vl;
    va_start(vl, n);
    for(int i=0; i != n; ++i) {
        int val=va_arg(vl,int);
        if(val > max) 
            max = val;
    }
    va_end(vl);
    return max;
}

void procs1(int stime, int life_time) {
    
    sleep(stime);
    
    pid_t pid = fork();
    clock_t ts_sys_proc1 = clock();
    time_t ts_usr_proc1 = time(NULL);
    
    if (pid == -1) {
        fork_err_msg();
    } else if(pid==0) {         

        // Child 1 Start
        procs_create_msg("Child 1", getpid(), getppid(),ts_usr_proc1);
        sleep(life_time);
        clock_t t_sys_proc1 = clock() - ts_sys_proc1;
        double t_usr_proc1 = difftime(time(NULL), ts_usr_proc1);
        procs_exit_msg("Child 1", getpid());
        exit(0);  
        // Child 1 End
    
    }
}

void procs2(int stime, int life_time, int gc1stime, 
        int gc2stime, int gc1ltime, int gc2ltime) {

    sleep(stime);

    pid_t pid = fork();
    clock_t ts_sys_proc2 = clock();
    time_t ts_usr_proc2 = time(NULL);
    
    if (pid == -1) {
        fork_err_msg();
    } else if(pid==0) { 
        
        // Child 2 Start 
        procs_create_msg("Child 2", getpid(), getppid(), ts_usr_proc2);

        sleep(gc1stime);

        pid_t pid = fork();
        clock_t ts_sys_proc2_1 = clock();
        time_t ts_usr_proc2_1 = time(NULL);
       
        if(pid == -1) {
            fork_err_msg();
        } else if(pid==0) {
            
            // -  GrandChild 1 Start
            procs_create_msg("-  GrandChild 1", 
                getpid(), getppid(), ts_usr_proc2_1);
            sleep(gc1ltime);
            clock_t t_sys_proc2_1 = clock() - ts_sys_proc2_1;
            double t_usr_proc2_1 = difftime(time(NULL), ts_usr_proc2_1);
            procs_exit_msg("-  GrandChild 1", getpid());
            exit(0);
            // -  GrandChild 1 End 
        } 

        sleep(gc2stime-gc1stime);

        pid = fork();
        clock_t ts_sys_proc2_2 = clock();
        time_t ts_usr_proc2_2 = time(NULL);
        
        if(pid==-1) {
            fork_err_msg();
        } else if(pid==0) { 
            
            // -  GrandChild 2 Start
            procs_create_msg("-  GrandChild 2", 
                getpid(), getppid(), ts_usr_proc2_2);
            sleep(gc2ltime);
            clock_t t_sys_proc2_2 = clock() - ts_sys_proc2_2;
            double t_usr_proc2_2 = difftime(time(NULL), ts_usr_proc2_2);
            procs_exit_msg("-  GrandChild 2", getpid());
            exit(0); 
            // -  GrandChild 2 End 
        }

        sleep(life_time-gc2stime);
        clock_t t_sys_proc2 = clock() - ts_sys_proc2;
        double t_usr_proc2 = difftime(time(NULL), ts_usr_proc2);
        procs_exit_msg("Child 2", getpid());
        exit(0);  
        // Child 2 End
    }

}

// get and validate the time
bool getTime(char* str, int* t){
    int flag = sscanf(str, "%d", t);
    return (*t >= 0 && flag != EOF && flag != 0);
} 

int main(int agrc, char* argv[]) {
    time_t cur_time = time(NULL);
    clock_t ts_sys_p = clock();
    procs_create_msg("Parent", getpid(), getppid(), cur_time);
    if(agrc != 9) {
        printf("Invalid usage. Usage: %s t1 t2 t3 t4 t5 t6 t7 t8. \n", argv[0]);
        exit(1);
    }

    int c1stime, c2stime, gc1stime, gc2stime;
    int c1ltime, c2ltime, gc1ltime, gc2ltime;

    // check whether all the input are valid
    bool isArgValid = true;
    if (isArgValid && false == getTime(argv[1], &c1stime)) {
        isArgValid= false;
    } 
    if (isArgValid && false == getTime(argv[2], &c2stime)) {
        isArgValid= false;
    }
    if (isArgValid && false == getTime(argv[3], &c1ltime)) {
        isArgValid= false;
    }
    if (isArgValid && false == getTime(argv[4], &c2ltime)) {
        isArgValid= false;
    }
    if (isArgValid && false == getTime(argv[5], &gc1stime)) {
        isArgValid= false;
    }
    if (isArgValid && false == getTime(argv[6], &gc2stime)) {
        isArgValid= false;
    }
    if (isArgValid && false == getTime(argv[7], &gc2ltime)) {
        isArgValid= false;
    }
    if (isArgValid && false == getTime(argv[8], &gc1ltime)) {
        isArgValid= false;
    }
    if (!isArgValid) {
        perror("Invalid time");
        exit(1);
    }
  
    if (c2ltime < gc1stime) {
        perror("The life time of child process 2 is too short to create grand child 1");
        exit(1);
    } else if (c2ltime < gc2stime) {
        perror("The life time of child process 2 is too short to create grand child 2");
        exit(1);
    } else if (gc1stime > gc2stime) {
        perror("grand child 1 must start before grand child 2");
        exit(1);
    }

    // count the second the parent need to wait until 
    // all the child and grand child process dead
    int parent_wait = max( 4, 
            c1stime+c1ltime, 
            c2stime+c2ltime, 
            c2stime+gc1stime+gc1ltime, 
            c2stime+gc2stime+gc2ltime )+1;

    if (c1stime < c2stime) {
        parent_wait -= c2stime;
        procs1(c1stime, c1ltime);
        procs2(c2stime-c1stime, c2ltime, gc1stime, 
            gc2stime, gc1ltime, gc2ltime);
    } else {
        parent_wait -= c1stime;
        procs2(c2stime, c2ltime, gc1stime, gc2stime, gc1ltime, gc2ltime);
        procs1(c1stime-c2stime, c1ltime);
    }

    // wait until all the child and grand child dead. 
    // comment the following line to disable the feature
    sleep(parent_wait);

    clock_t t_sys_p= clock() - ts_sys_p;
    double t_usr_p = difftime(time(NULL), cur_time);
    procs_exit_msg("Parent", getpid());
}