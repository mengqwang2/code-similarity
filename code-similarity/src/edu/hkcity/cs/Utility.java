package edu.hkcity.cs;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class Utility.
 */
public class Utility {

    /** The keywords. */
    // Predetermined keywords, operators and values, extensible
    protected static String[] keywords = {
            // keywords
            "auto", "double", "int", "struct", "break", "else", "long",
            "switch", "case", "enum", "register", "typedef", "char", "extern",
            "return", "union", "const", "float", "short", "unsigned",
            "continue", "for", "signed", "void", "default", "goto", "sizeof",
            "volatile", "do", "if", "static", "while", "main", "printf",
            // values
            "true", "false", "null", "\'.*\'", "\".*\"",
            // punctuation and blank
            "\\p{Punct}", "\\s" };

    /**
     * Join. Join elements in an array to a string with a delimiter
     *
     * @param arr
     *            the arr
     * @param delimiter
     *            the delimiter
     * @return the string
     */
    public static String join(String[] arr, String delimiter) {
        String result = "";
        int length = arr.length;
        for (int i = 0; i < length - 1; ++i)
            result += arr[i] + delimiter;
        result += arr[length - 1];
        return result;
    }


    /**
     * Checks if is var. Decide whether a token is predetermined keywords
     *
     * @param token
     *            the token
     * @return true, if is var
     */
    public static boolean isVar(String token) {
        return (java.util.regex.Pattern.matches("(_|[a-zA-Z])\\w*", token) && !java.util.regex.Pattern
                .matches(join(keywords, "|"), token));
    }


    /**
     * Removes the duplicate. Remove duplicate element in an string array
     *
     * @param arr
     *            the arr
     * @return the string[]
     */
    public static String[] removeDuplicate(String[] arr) {
        ArrayList<String> result = new ArrayList<String>();
        int length = arr.length;

        for (int i = 0; i < length; ++i) {
            String s = arr[i];
            if (!result.contains(s))
                result.add(s);
        }

        return result.toArray(new String[result.size()]);
    }


    /**
     * Extract var names. Extract a list of var names from a string based on predetermined keywords
     *
     * @param source
     *            the source
     * @return the string[]
     */
    public static String[] extractVarNames(String source) {
        ArrayList<String> list = new ArrayList<String>();
        String[] frag = source.split("\\s");
        int fragLeng = frag.length;
        String[][] vars = new String[fragLeng][];
        for (int i = 0; i < fragLeng; ++i) {
            vars[i] = frag[i].split("(\'.*\')|(\".*\")|(\\W)");
            int length = vars[i].length;
            for (int j = 0; j < length; ++j)
                if (isVar(vars[i][j]))
                    list.add(vars[i][j]);
        }
        return removeDuplicate(list.toArray(new String[list.size()]));
    }


    /**
     * Lcs.
     *
     * @param token1
     *            the token1
     * @param token2
     *            the token2
     * @return the double
     */
    public static double lcs(String[] token1, String[] token2) {
        int n = token1.length;
        int m = token2.length;

        int[][] C = new int[n + 1][m + 1];

        /* C[i][0] = 0 for 0<=i<=n */
        for (int i = 0; i <= n; i++) {
            C[i][0] = 0;
        }

        /* C[0][j] = 0 for 0<=j<=m */
        for (int j = 0; j <= m; j++) {
            C[0][j] = 0;
        }
        /* dynamic programming */
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                if (token1[i - 1].equals(token2[j - 1])) {
                    C[i][j] = C[i - 1][j - 1] + 1;
                } else if (C[i - 1][j] >= C[i][j - 1]) {
                    C[i][j] = C[i - 1][j];
                } else {
                    C[i][j] = C[i][j - 1];
                }
            }
        }

        return C[n][m] * 1.0 / n;
    }


    /**
     * Gets the tok.
     *
     * @param str
     *            the str
     * @return the tok
     */
    public static String[] getTok(String str) {
        // String to be scanned to find the pattern.
        String pattern = "(\\w+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(str);

        List<String> allMatches = new ArrayList<String>();
        while (m.find()) {

            allMatches.add(m.group());
        }
        return allMatches.toArray(new String[0]);
    }

    /*
     * buildRegexpPattern
     *     1. escape all the punctuation in the string to make it a regular punctuation in the regular expression
     *  2. append anonymous symbols to punctuation ";" "{" and "}"
     * @param string
     *
     * @return regexp_pattern
     *
     */
    private static String buildRegexpPattern(String str) {
        // String to be scanned to find the pattern.
        String punctPatt = "([\\p{Punct}&&[^_]])";
        // Create a Pattern object
        Pattern pattern = Pattern.compile(punctPatt);

        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();

        while (true == matcher.find()){
            String escapePunct = "\\\\" + matcher.group();
            if (matcher.group().equals(";") || matcher.group().equals("{") || matcher.group().equals("}")) {
                // append anonymous symbols
                escapePunct = new String(escapePunct+"(?:.*?)?");
            }
            matcher.appendReplacement(sb, escapePunct);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * extract var and build var-based Regexp
     *  1. build var-based regular expression.
     *  2. extract the var and store in the Vector<String>
     * @param str
     *
     * @param Vector<String>
     *
     * @return var-based regexp
     */
    private static String buildvarBasedRegexp(final String str, Vector<String> extractedvars) {
        // String to be scanned to find the pattern.

        String varPatternStr = "([\\w_]+)";

        // Create a Pattern object
        Pattern varPattern = Pattern.compile(varPatternStr);

        Matcher varMatcher = varPattern.matcher(str);

        StringBuffer varSb = new StringBuffer();

        while (varMatcher.find()) {
            if (isVar(varMatcher.group())) {
                int index = extractedvars.indexOf(varMatcher.group());
                if (index == -1) {
                    extractedvars.add(varMatcher.group());
                    varMatcher.appendReplacement(varSb, "(\\\\w*?)");
                } else {
                    varMatcher.appendReplacement(varSb,
                            "\\\\" + Integer.toString(index + 1));
                }
            }
        }
        varMatcher.appendTail(varSb);

        return varSb.toString();
    }

    /**
     * Replace.
     *
     * @param str
     *            the str
     * @param tar
     *            the tar
     * @return the string
     *
     * also see the buildRegexpPattern() function
     */
    public static String replace(String str, String tar) {
        str = str.replaceAll("[\n\\\\]", "");
        tar = tar.replaceAll("[\n\\\\]", "");

        if (str.length() > tar.length()) {
            String tmp = tar;
            tar = str;
            str = tmp;
        }

        // build regular expression
        str = buildRegexpPattern(str);

        // Vector to store the extracted var
        Vector<String> var = new Vector<String>();

        // build var based regular expression
        String varPatternStr = buildvarBasedRegexp(str, var);

        // Create a Pattern object
        Pattern varPattern = Pattern.compile(varPatternStr);
        Matcher varMatcher = varPattern.matcher(tar);
        if(varMatcher.matches()) {
            //varMatcher.find();
            int numMatches = varMatcher.groupCount();
            // debug i from 0 to 1
            for (int i = 0; i != numMatches; ++i) {
                String toBeReplaced = "\\b" + varMatcher.group(i+1) + "\\b";
                String middlewareReplacement = "0R" + var.get(i);
                tar = tar.replaceAll(toBeReplaced, middlewareReplacement);
            }
            tar = tar.replaceAll("\\b0R", "");
        }
        return tar;
    }


    /**
     * Split function.
     *
     * @param program
     *            the program
     * @return the array list
     */
    public static ArrayList<String> splitFunction(String program) {
        ArrayList<String> funcList = new ArrayList<String>();
        Pattern pattern = Pattern.compile(
                "[\\w\\*]* [\\w]*\\((.*)\\)[ \\r\\n]*\\{", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(program);
        int start = 0;
        int nextStart = 0;
        while (matcher.find()) {
            start = nextStart;
            nextStart = matcher.start();
            if (start != 0) {
                funcList.add(program.substring(start, nextStart).replaceAll(
                        "[\\r\\n\\s]+$", ""));
            }
        }
        funcList.add(program.substring(nextStart, program.length()).replaceAll(
                "[\\r\\n\\s]+$", ""));
        return funcList;
    }
}
