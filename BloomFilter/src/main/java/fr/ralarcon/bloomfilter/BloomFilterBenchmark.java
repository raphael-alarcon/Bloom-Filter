package fr.ralarcon.bloomfilter;

import fr.ralarcon.utils.Colors;
import fr.ralarcon.utils.StringUtils;
import java.util.Arrays;
import java.util.Random;

public class BloomFilterBenchmark {
    public BloomFilterArray arrayFilter;
    public BloomFilterArrayList arrayListFilter;
    public BloomFilterLinkedList linkedListFilter;

    private final int bound;
    public static int NB_SIMULATIONS = 10000000;

    public BloomFilterBenchmark(int m, int k, int bound) {
        arrayFilter = new BloomFilterArray(m, k);
        arrayListFilter = new BloomFilterArrayList(m, k);
        linkedListFilter = new BloomFilterLinkedList(m, k);
        this.bound = bound;
        
    }

    public void run() {
        long startTime, endTime;
        Random random = new Random();

        Colors.printSeparator();
        System.out.println(Colors.YELLOW_BOLD+"\nFULL BENCHMARK\n"+Colors.RESET);
        System.out.println("Properties:");
        System.out.println("    - m = "+this.arrayFilter.m + " (Size of the filter)");
        System.out.println("    - k = "+this.arrayFilter.k + " (Number of hash functions)");
        System.out.println("    - n = "+(int)(NB_SIMULATIONS*0.1) + " (Number of values added in the filter)");
        System.out.println("    - "+NB_SIMULATIONS+" searching simulations (random values from 0 - "+this.bound+")\n");
        System.out.println("Testing execution times: ");
        
        // Test adding elements to the Bloom filter.
        startTime = System.currentTimeMillis();
        arrayFilter.initArrayOfValues((int)(NB_SIMULATIONS*0.1), bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("Array filter fill values: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        arrayListFilter.initArrayOfValues((int)(NB_SIMULATIONS*0.1), bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList filter fill values: " + (endTime - startTime) + "ms");
        
        startTime = System.currentTimeMillis();
        linkedListFilter.initArrayOfValues((int)(NB_SIMULATIONS*0.1), bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList filter fill values: " + (endTime - startTime) + "ms");

        System.out.println("\nTesting research times: ");
        // Test searching elements in the Bloom filter.
        startTime = System.currentTimeMillis();
        for (int i = 0; i < NB_SIMULATIONS; i++) {
            arrayFilter.research(random.nextInt(bound));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Array filter research: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < NB_SIMULATIONS; i++) {
            arrayListFilter.research(random.nextInt(bound));
        }
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList filter research: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < NB_SIMULATIONS; i++) {
            linkedListFilter.research(random.nextInt(bound));
        }
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList filter research: " + (endTime - startTime) + "ms");
    }
    
    public void displayBenchmark(BloomFilter filter) {
        long startTotalTime, endTotalTime;
        
        double[] nValues = {0.01,0.05,0.1};
        int[] kValues = {1,3,5};
        
        startTotalTime = System.currentTimeMillis();
        Colors.printSeparator();
        System.out.println(Colors.YELLOW_BOLD+"\nBENCHMARK " + Colors.RESET + filter.getClass().getName()+"\n");
        System.out.println("Result of researching values in the filter :");
        System.out.println("m = " + filter.m+"\n");
        System.out.printf("%5s|%5s|%5s|%5s|%n", StringUtils.center("k/n",5), StringUtils.center(""+nValues[0], 5), StringUtils.center(""+nValues[1], 5), StringUtils.center(""+nValues[2], 5));
        StringBuilder line = new StringBuilder();
        for (int kValue : kValues) {
            line.append(String.format("%5s|", StringUtils.center("" + kValue, 5)));
            for (double nValue : nValues) {
                filter.valuesArray = filter.initArrayOfValues((int) (filter.m * nValue), bound, kValue);
                line.append(String.format("%5s|", StringUtils.center(falsePositiveRatio(filter) + "%", 5)));
            }
            System.out.println(Colors.CROSSED_TEXT + "     +     +     +     +" + Colors.RESET);
            System.out.println(line);
            line = new StringBuilder();
        }
        System.out.println(Colors.CROSSED_TEXT+"     +     +     +     +"+Colors.RESET);
        endTotalTime = System.currentTimeMillis();
        System.out.println("\nNumber of values: " + filter.m*nValues[0] + ", " + filter.m*nValues[1] + ", " + filter.m*nValues[2]);
        System.out.println("Values: 0 - " + bound);
        System.out.println("Total Time: " + (endTotalTime - startTotalTime) + "ms"+ Colors.RESET);
    }
    
    public double falsePositiveRatio(BloomFilter filter) {
        double result = 0;
        Random random = new Random();
        int[] researchedValues = new int[100];
        
        // Add random values to the list of researched values
        // (that are not actually in the valuesArray of the filter)
        // so that we can evaluate the false positive ratio
        for(int i=0;i<researchedValues.length;i++) {
            int value = random.nextInt(bound);
            while (Arrays.asList(filter.valuesArray).contains(value)) {
                value = random.nextInt(bound);
            }
            researchedValues[i]=random.nextInt(value);
        }
        
        //
        for (int researchedValue : researchedValues) {
            if (filter.research(researchedValue)) {
                result++;
            }
        }
        return result/researchedValues.length;
    }
}