package edu.hkcity.cs;

import java.util.Arrays;

/**
 * The Class RegexComparar.
 */
public class RegexComparar extends Comparar {
	// Constructor
	/**
	 * Instantiates a new regex comparar.
	 */
	public RegexComparar() {
		super();
		info = "Comparar based on regex transformation";
	}

	/**
	 * Instantiates a new regex comparar.
	 *
	 * @param tar
	 *            the tar
	 * @param ori
	 *            the ori
	 */
	public RegexComparar(String tar, String ori) {
		super(tar, ori);
		info = "Comparar based on regex transformation";
	}

	/* (non-Javadoc)
	 * @see edu.hkcity.cs.Comparar#compare(edu.hkcity.cs.Formatter, edu.hkcity.cs.Output)
	 */
	public String compare(Formatter fmt, Output output) {
		String target = fmt.format(getTar());
		String original = fmt.format(getOri());

		Double result = calRegedSim(target, original);

		output.print(info, result);

		return Double.toString(result);
	}

	//
	/**
	 * Cal reged sim.
	 *
	 * @param tar
	 *            the tar
	 * @param ori
	 *            the ori
	 * @return the double
	 */
	protected double calRegedSim(String tar, String ori) {

		String[] tarVars = Utility.extractVarNames(tar);
		String[] oriVars = Utility.extractVarNames(ori);

		Arrays.sort(tarVars);
		Arrays.sort(oriVars);

		int tarLen = tarVars.length;
		int oriLen = oriVars.length;

		String[] sortedTar = new String[tarLen];
		String[] sortedOri = new String[oriLen];

		for (int i = 0; i < tarLen; ++i)
			sortedTar[i] = tarVars[tarLen - 1 - i];
		for (int i = 0; i < oriLen; ++i)
			sortedOri[i] = oriVars[oriLen - 1 - i];

		String[] regedTar = tar.replaceAll("\\s|;|" + Utility.join(sortedTar, "|"), "").split("");
		String[] regedOri = ori.replaceAll("\\s|;|" + Utility.join(sortedOri, "|"), "").split("");

		return (Utility.lcs(regedTar, regedOri)+Utility.lcs(regedOri, regedTar))/2;
	}
}
