package edu.hkcity.cs;

public class PercentageOutput extends Output {
	public PercentageOutput() {
	}

	/* (non-Javadoc)
	 * @see edu.hkcity.cs.Output#print(java.lang.String, double)
	 */
	@Override
	public void print(String name, double ratio) {
		System.out.printf("%s : %.2f%%\n", name, ratio * 100);
	}
}
