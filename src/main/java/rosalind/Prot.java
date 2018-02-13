package rosalind;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Prot {
    public static void main(String[] args) throws IOException {
        String fName = System.getProperty("user.home") + "/Desktop/rosa_prot.txt";
        String content = new String(Files.readAllBytes(Paths.get(fName)));

        String RNA_codon =
                "UUU F      CUU L      AUU I      GUU V\n" +
                "UUC F      CUC L      AUC I      GUC V\n" +
                "UUA L      CUA L      AUA I      GUA V\n" +
                "UUG L      CUG L      AUG M      GUG V\n" +
                "UCU S      CCU P      ACU T      GCU A\n" +
                "UCC S      CCC P      ACC T      GCC A\n" +
                "UCA S      CCA P      ACA T      GCA A\n" +
                "UCG S      CCG P      ACG T      GCG A\n" +
                "UAU Y      CAU H      AAU N      GAU D\n" +
                "UAC Y      CAC H      AAC N      GAC D\n" +
                "UAA Stop   CAA Q      AAA K      GAA E\n" +
                "UAG Stop   CAG Q      AAG K      GAG E\n" +
                "UGU C      CGU R      AGU S      GGU G\n" +
                "UGC C      CGC R      AGC S      GGC G\n" +
                "UGA Stop   CGA R      AGA R      GGA G\n" +
                "UGG W      CGG R      AGG R      GGG G";

        Map<String, String> tblRnaCodon = new HashMap<>();
        for (String s : RNA_codon.split("\\n|\\s\\s+"))
            tblRnaCodon.put(s.substring(0, 3), s.substring(4));

        StringReader sr = new StringReader(content);
        StringBuilder sb = new StringBuilder();

        char[] buff = new char[3];
        while (sr.read(buff) > 0) {
            String p = tblRnaCodon.get(String.valueOf(buff));
            if (p.equals("Stop"))
                break;

            sb.append(p);
        }

        System.out.println(sb.toString());
    }
}
