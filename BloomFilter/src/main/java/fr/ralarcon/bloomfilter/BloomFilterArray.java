/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fr.ralarcon.bloomfilter;

import java.util.Arrays;

/**
 *
 * @author ralarcon
 */
public class BloomFilterArray extends BloomFilter {
    
    public BloomFilterArray(int size, int hashNumber) {
        super(size, hashNumber);
        bitsArray = new boolean[this.m];
        Arrays.fill(bitsArray, false);
        
        displayResult();
    }
}
