/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.ralarcon.bloomfilter;

import java.util.Scanner;

/**
 *
 * @author ralarcon
 */
public class BloomFilterMain {
  
    static Scanner scanner =  new Scanner(System.in);
    
    public static void main(String[] args) {
        
        // Fill the array of booleans with False
        BloomFilter filterArray = new BloomFilterArray(500, 3);
        filterArray = new BloomFilterArrayList(500, 3);
        filterArray = new BloomFilterLinkedList(500, 3);

        BloomFilterBenchmark benchmark = new BloomFilterBenchmark(10000, 3, 1000);
        benchmark.run();
    }
}