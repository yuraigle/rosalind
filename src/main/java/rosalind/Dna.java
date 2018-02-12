package rosalind;

import java.math.BigDecimal;

public class Dna {
    private String name;

    private String content;

    public String getName() {
        return name;
    }

    public Dna(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // The GC-content of a DNA string is given by the percentage of symbols in
    // the string that are 'C' or 'G'.
    public BigDecimal computeCG() {
        Double cg = (double) content.replaceAll("[^CG]*", "").length();
        Integer ttl = content.length();

        return BigDecimal.valueOf(cg / ttl * 100);
    }

}
