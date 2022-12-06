package fr.ralarcon.bloomfilter;

import java.util.Random;

public class BloomFilterBenchmark {
    private BloomFilterArray arrayFilter;
    private BloomFilterArrayList arrayListFilter;
    private BloomFilterLinkedList linkedListFilter;

    private int bound;

    public BloomFilterBenchmark(int m, int k, int bound) {
        arrayFilter = new BloomFilterArray(m, k);
        arrayListFilter = new BloomFilterArrayList(m, k);
        linkedListFilter = new BloomFilterLinkedList(m, k);
        this.bound = bound;
    }

    public void run() {
        long startTime, endTime;
        Random random = new Random();

        // Test adding elements to the Bloom filter.
        startTime = System.currentTimeMillis();
        arrayFilter.initArrayOfValues(10000000, bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("Array filter fill values: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        arrayListFilter.initArrayOfValues(10000000, bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList filter fill values: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        linkedListFilter.initArrayOfValues(10000000, bound, arrayFilter.k);
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList filter fill values: " + (endTime - startTime) + "ms");

        // Test testing elements in the Bloom filter.
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            arrayFilter.research(random.nextInt(bound));
        }
        endTime = System.currentTimeMillis();
        System.out.println("Array filter research: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            arrayListFilter.research(random.nextInt(bound));
        }
        endTime = System.currentTimeMillis();
        System.out.println("ArrayList filter research: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            linkedListFilter.research(random.nextInt(bound));
        }
        endTime = System.currentTimeMillis();
        System.out.println("LinkedList filter research: " + (endTime - startTime) + "ms");
    }
}