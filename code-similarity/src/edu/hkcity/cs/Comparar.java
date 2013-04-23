package edu.hkcity.cs;

/**
 * The Class Comparar.
 */
public abstract class Comparar {

    /**
     * Instantiates a new comparer.
     */
    public Comparar() {
        info = "Abstract Comparar";
    }

    /**
     * Instantiates a new comparar.
     *
     * @param tarStr
     *            the tar
     * @param oriStr
     *            the ori
     */
    public Comparar(final String tarStr, final String oriStr) {
        this.setTar(tar);
        this.setOri(ori);
        info = "Abstract Comparar";
    }

    /**
     * Compare.
     *
     * @param fmt
     *            the fmt
     * @param output
     *            the output
     * @return the string
     */
    public abstract String compare(Formatter fmt, Output output);

    private void setTar(String tar) {
        this.tar = tar;
    }

    protected String getTar() {
        return tar;
    }

    private void setOri(String ori) {
        this.ori = ori;
    }

    protected String getOri() {
        return ori;
    }

    private String tar;
    private String ori;
    protected String info;
}
