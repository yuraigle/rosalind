package rosalind;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CG {  // Rosalind CG Problem

    // read file in FASTA format
    private static List<Dna> readFasta(String fn) throws IOException {
        List<Dna> fasta = new ArrayList<>();

        String content = new String(Files.readAllBytes(Paths.get(fn)));
        for (String s : content.split(">")) {
            String name = s.replaceAll("\\r?\\n.*", "");
            String cont = s.replaceFirst("^.*\\r?\\n", "")
                    .replaceAll("\\r?\\n", "");

            if (!name.isEmpty())
                fasta.add(new Dna(name, cont));
        }

        return fasta;
    }

    public static void main(String[] args) {
        try {
            String fName = System.getProperty("user.home") + "/Desktop/data_cg.txt";
            List<Dna> fasta = readFasta(fName);
            fasta.sort(Comparator.comparing(Dna::computeCG));
            Dna maxCg = fasta.get(fasta.size() - 1);

            System.out.println(maxCg.getName());
            System.out.println(maxCg.computeCG().setScale(6, BigDecimal.ROUND_HALF_UP));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
