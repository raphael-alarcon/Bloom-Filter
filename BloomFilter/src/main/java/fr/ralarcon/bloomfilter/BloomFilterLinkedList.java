/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fr.ralarcon.bloomfilter;

import fr.ralarcon.utils.Colors;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author ralarcon
 */
public class BloomFilterLinkedList extends BloomFilter {

    // Bits array is a LinkedList
    public LinkedList<Boolean> bitsArray;

    /**
     * Constructor
     * @param size size of the array
     * @param hashNumber number of hash functions
     */
    public BloomFilterLinkedList(int size, int hashNumber) {
        super(size, hashNumber);
        bitsArray = new LinkedList<>(Arrays.asList(new Boolean[m]));
        valuesArray = initArrayOfValues(m, 500, k);
        Collections.fill(bitsArray, false);
    }

    /**
     * Initialize the array of values
     *
     * @param size size of the array
     * @param bound bound of the random values
     * @param k number of hash functions
     * @return the array of initialized values
     */
    @Override
    public int[] initArrayOfValues(int size, int bound, int k) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = rdm.nextInt(bound);
            hashValue(k, res[i]);
        }
        return res;
    }

    /**
     * Hash the value
     *
     * @param k number of hash functions
     * @param x value to hash
     */
    @Override
    public void hashValue(int k, int x) {
        for (int i = 1; i <= k; i++) {
            bitsArray.set(hash(x, i), true);
        }
    }

    /**
     * Research the value
     *
     * @param x value to research
     * @return true if the element at the index of the researched value is true, false otherwise
     */
    @Override
    public boolean research(int x) {
        for (int i = 1; i <= k; i++) {
            if (!bitsArray.get(hash(x, i)))return false;
        }
        return true;
    }

    /**
     * Display the result
     */
    @Override
    public void displayResult() {
        super.displayResult();
        System.out.println(bitsArray.toString());
        System.out.println((research(researchedValue) ? "Element " + Colors.GREEN_BOLD + researchedValue + Colors.RESET + " trouvé" : "Element " + Colors.RED_BOLD + researchedValue + Colors.RESET+" non trouvé"));
    }
}
