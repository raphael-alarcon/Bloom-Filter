/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fr.ralarcon.bloomfilter;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author ralarcon
 */
public class BloomFilterLinkedList extends BloomFilter {

    public LinkedList<Boolean> bitsArray;
    
    public BloomFilterLinkedList(int size, int hashNumber) {
        super(size, hashNumber);
        bitsArray = new LinkedList<>();
        Collections.fill(bitsArray, false);
    }
    
}
