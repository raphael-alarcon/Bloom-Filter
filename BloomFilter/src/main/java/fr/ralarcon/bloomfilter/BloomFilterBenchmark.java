package fr.ralarcon.bloomfilter;

import fr.ralarcon.utils.Colors;
import fr.ralarcon.utils.StringUtils;
import java.util.Arrays;
import java.util.Random;

public class BloomFilterBenchmark {
    private BloomFilterArray arrayFilter;
    private BloomFilterArrayList arrayListFilter;
    private BloomFilterLinkedList linkedListFilter;

    private int bound;
    
    private static int NB_SIMULATIONS = 10000000;
    
    private int numberOfValues;

    public BloomFilterBenchmark(int m, int k, int bound) {
        arrayFilter = new BloomFilterArray(m, k);
        arrayListFilter = new BloomFilterArrayList(m, k);
        linkedListFilter = new BloomFilterLinkedList(m, k);
        numberOfValues = (int)(m*0.01);
        this.bound = bound;
        
    }

    public void run() {
        long startTime, endTime;
        Random random = new Random();

        System.out.println("n = Number of values (1%, 5% and 10% of m)\n");
        System.out.println("k = number of hash functions (1, 3 and 5)\n");
        // Test adding elements to the Bloom filter.
        displayBenchmark(arrayFilter);

        startTime = System.currentTimeMillis();
        linkedListFilter.initArrayOfValues(numberOfValues, bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList filter fill values: " + (endTime - startTime) + "ms");

        // Test testing elements in the Bloom filter.
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
        Random random = new Random();
        
        int[] researchedValues = new int[100];
        for(int i=0;i<researchedValues.length;i++)researchedValues[i]=random.nextInt(bound);
        
        double[] nValues = {0.01,0.05,0.1};
        int[] kValues = {1,3,5};
        
        System.out.println(Colors.YELLOW_BOLD+"---------------------------------------");
        System.out.println("\nBenchmark " + Colors.RESET + filter.getClass().getName()+"\n");
        System.out.println("Result of researching values in the filter :");
        System.out.println("m = " + filter.m);
        System.out.println("Researching: " + Arrays.toString(researchedValues)+"\n");
        System.out.println(String.format("%5s|%5s|%5s|%5s|", StringUtils.center("k/n",5), StringUtils.center(""+nValues[0], 5), StringUtils.center(""+nValues[1], 5), StringUtils.center(""+nValues[2], 5)));
        String line = "";
        for (int i = 0; i<kValues.length; i++) {
            line+=String.format("%5s|", StringUtils.center(""+kValues[i], 5));
            for (int j = 0; j<nValues.length;j++) {
                filter.valuesArray = filter.initArrayOfValues((int)(filter.m*nValues[j]), bound, kValues[i]);
                line+=String.format("%5s|", StringUtils.center(falsePositiveRatio(researchedValues, filter)+"%", 5));
            }
            System.out.println("-----+-----+-----+-----+");
            System.out.println(line);
            line="";
        }
        
        startTotalTime = System.currentTimeMillis();
        filter.initArrayOfValues(numberOfValues, bound, filter.k);
        System.out.println("\nNumber of values: " + numberOfValues);
        System.out.println("Values: 0 - " + bound);
        
        endTotalTime = System.currentTimeMillis();
        System.out.println("Total Time: " + (endTotalTime - startTotalTime) + "ms");
        System.out.println(Colors.YELLOW_BOLD+"---------------------------------------"+ Colors.RESET);
    }
    
    public double falsePositiveRatio(int[] researchValues, BloomFilter filter) {
        double result = 0;
        for (int i = 0;i<researchValues.length;i++) {
            if (filter.research(researchValues[i]) && !Arrays.asList(filter.valuesArray).contains(researchValues[i])) {
                result++;
            }
        }
        return result/researchValues.length;
    }
}