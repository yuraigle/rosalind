package rosalind;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hamm {
    public static void main(String[] args) {
        try {
            String fName = System.getProperty("user.home") + "/Desktop/data_hamm.txt";
            String content = new String(Files.readAllBytes(Paths.get(fName)));
            String[] s = content.split("\\r?\\n");
            Integer dh = 0;

            for (Integer i = 0; i < s[0].length(); i++) {
                if (s[0].charAt(i) != s[1].charAt(i))
                    dh++;
            }

            System.out.println(dh);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
