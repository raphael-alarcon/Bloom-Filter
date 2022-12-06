/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.ralarcon.bloomfilter;

/**
 *
 * @author ralarcon
 */
import fr.ralarcon.utils.Colors;
import java.util.Arrays;
import java.util.Random;
public abstract class BloomFilter {
    
    public int m;
    
    public int k;
    
    public boolean[] boolArray;
    
    int[] valuesArray = initArrayOfValues(20, 200, k);
    
    public Random rdm = new Random();
    
    int researchedValue = 154;
    
    public BloomFilter(int size, int hashNumber) {
        this.m = size;
        this.k = hashNumber;
        boolArray = new boolean[m];
    }
    
    public int[] initArrayOfValues(int size, int bound, int k) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = rdm.nextInt(bound);
            hashValue(k, res[i]);
        }
        return res;
    }
    
    public int hash(int x, int i) {
        return x * i % m;
    }

    public void hashValue(int k, int x) {
        for (int i = 1; i <= k; i++) {
            boolArray[hash(x, i)] = true;
        }
    }
    
    public boolean research(int x, int k) {
        boolean found = false;
        for (int i = 1; i <= k; i++) {
            if (boolArray[hash(x, i)]) {
                found = true;
            }
        }
        return found;
    }
    
    public void displayResult() {
        System.out.println(Colors.YELLOW_BOLD+"---------------------------------------");
        System.out.println("DEBUG");
        System.out.println(Arrays.toString(valuesArray));
        System.out.println(Arrays.toString(boolArray));
        
        System.out.println((research(researchedValue, k) ? "Element " +researchedValue+" trouvé" : "Element "+researchedValue+" non trouvé"));
    }
}
