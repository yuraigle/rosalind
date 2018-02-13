package rosalind;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Iprb_2 {

    static class TestCase {
        int k, m, n;

        private TestCase(int k, int m, int n) {
            this.k = k;
            this.m = m;
            this.n = n;
        }

        private String pickOne() throws IllegalArgumentException {
            if (k + m + n < 1)
                throw new IllegalArgumentException("Nothing to pick!");

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

        Boolean run() throws IllegalArgumentException {
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

        // многопоточная реализация использует все 8 ядер на 100%
        // почему-то работает в 3 раза дольше, чем обычная на 1 ядре WTF??
        Integer totalTests = 10000000;
        Integer numThreads = 8;

        Callable<Double> task = () -> {
            double win = 0;
            Integer ttlPerTask = totalTests / numThreads;

            try {
                for (int i = 0; i < ttlPerTask; i++) {
                    if ((new TestCase(k, m, n)).run())
                        win++;
                }
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            return win / ttlPerTask;
        };

        List<Callable<Double>> callableTasks = new ArrayList<>();
        for (int i = 0; i < numThreads; i++)
            callableTasks.add(task);

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        try {
            List<Future<Double>> futures = executor.invokeAll(callableTasks);
            Double sum = .0;
            for (Future<Double> future : futures)
                sum += future.get();

            BigDecimal prob = BigDecimal.valueOf(sum / futures.size())
                    .setScale(5, BigDecimal.ROUND_HALF_UP);

            System.out.println(prob);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
