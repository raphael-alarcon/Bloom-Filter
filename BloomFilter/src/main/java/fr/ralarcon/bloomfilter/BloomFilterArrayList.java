/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package fr.ralarcon.bloomfilter;

import fr.ralarcon.utils.Colors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author ralarcon
 */
public class BloomFilterArrayList extends BloomFilter {

    // Bits array is an ArrayList
    public ArrayList<Boolean> bitsArray;

    /**
     * Constructor
     * @param size size of the array
     * @param hashNumber number of hash functions
     */
    public BloomFilterArrayList(int size, int hashNumber) {
        super(size, hashNumber);
        bitsArray = new ArrayList<>(Arrays.asList(new Boolean[m]));
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

    @Override
    public boolean research(int x) {
        for (int i = 1; i <= k; i++) {
            if (!bitsArray.get(hash(x, i)))return false;
        }
        return true;
    }
    
    /**
     * Print the result of the research
     */
    @Override
    public void displayResult() {
        super.displayResult();
        System.out.println(bitsArray.toString());
        System.out.println((research(researchedValue) ? "Element " + Colors.GREEN_BOLD + researchedValue + Colors.RESET + " trouvé" : "Element " + Colors.RED_BOLD + researchedValue + Colors.RESET+" non trouvé"));
    }
}
