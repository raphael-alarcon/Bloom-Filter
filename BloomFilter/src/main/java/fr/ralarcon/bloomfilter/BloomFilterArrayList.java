/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fr.ralarcon.bloomfilter;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author ralarcon
 */
public class BloomFilterArrayList extends BloomFilter {

    public ArrayList<Boolean> bitsArray;
    public BloomFilterArrayList(int size, int hashNumber) {
        super(size, hashNumber);
        bitsArray = new ArrayList<>(m);
        Collections.fill(bitsArray, false);

        displayResult();
    }
    
}
