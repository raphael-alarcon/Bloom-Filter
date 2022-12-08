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

    public LinkedList<Boolean> bitsArray;
    
    public BloomFilterLinkedList(int size, int hashNumber) {
        super(size, hashNumber);
        bitsArray = new LinkedList<>(Arrays.asList(new Boolean[m]));
        valuesArray = initArrayOfValues(m, 500, k);
        Collections.fill(bitsArray, false);
    }
    
    @Override
    public int[] initArrayOfValues(int size, int bound, int k) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = rdm.nextInt(bound);
            hashValue(k, res[i]);
        }
        return res;
    }

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
    
    @Override
    public void displayResult() {
        super.displayResult();
        System.out.println(bitsArray.toString());
        System.out.println((research(researchedValue) ? "Element " +researchedValue+" trouvé" : "Element "+researchedValue+" non trouvé")+ Colors.RESET);
    }
}
