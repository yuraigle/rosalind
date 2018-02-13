package rosalind;

import java.math.BigDecimal;

public class Iprb {

    static class TestCase {
        int k, m, n;

        private TestCase(int k, int m, int n) {
            this.k = k;
            this.m = m;
            this.n = n;
        }

        private String pickOne() throws Exception {
            if (k + m + n < 1)
                throw new Exception("Nothing to pick!");

            double mate = Math.random() * (k + m + n);

            if (mate < k) {
                k--;
                return "AA";
            } else if (mate < k + m) {
                m--;
                return "Aa";
            } else {
                n--;
                return "aa";
            }
        }

        Boolean run() throws Exception {
            String couple = pickOne() + pickOne();

            if (couple.contains("AA"))  // AA + anything
                return true;  // => prob 100%
            if (couple.equals("AaAa"))  // Aa + Aa
                return Math.random() * 4 < 3;  // => prob 3/4
            if (couple.contains("A"))  // Aa + aa
                return Math.random() * 2 < 1;  // prob 1/2

            return false;  // aa + aa => prob 0%
        }
    }

    public static void main(String[] args) {
        Integer k = 2;  // homozygous dominant
        Integer m = 2;  // heterozygous
        Integer n = 2;  // homozygous recessive

        double win = 0;
        Integer total = 10000000;

        try {
            for (int i = 0; i < total; i++) {
                if ((new TestCase(k, m, n)).run())
                    win++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        BigDecimal prob = BigDecimal.valueOf(win / total).setScale(5, BigDecimal.ROUND_HALF_UP);
        System.out.println(prob);
    }
}