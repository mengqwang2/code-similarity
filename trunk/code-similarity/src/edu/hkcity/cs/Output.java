package edu.hkcity.cs;

/**
 * The Class Output.
 */
public class Output {
	
	/**
	 * Instantiates a new output.
	 */
	public Output() {
	}

	/**
	 * Prints the.
	 * 
	 * @param name
	 *            the name
	 * @param ratio
	 *            the ratio
	 */
	public void print(String name, double ratio) {
		System.out.printf("%s : %.2f%%\n", name, ratio * 100);
	};
}
