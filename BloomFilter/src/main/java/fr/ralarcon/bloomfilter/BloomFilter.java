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
    
    public boolean[] bitsArray;
    
    int[] valuesArray;
    
    public static Random rdm = new Random();
    
    int researchedValue = 154;
    
    public BloomFilter(int size, int hashNumber) {
        this.m = size;
        this.k = hashNumber;
        bitsArray = new boolean[m];
        valuesArray = initArrayOfValues((int)(m*0.1), 200, k);
    }
    
    public int[] initArrayOfValues(int size, int bound, int k) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = rdm.nextInt(bound);
            hashValue(k, res[i]);
        }
        return res;
    }
    
    public int hash(Object x, int i) {
        return x.hashCode() * i * 31 % m;
    }

    public void hashValue(int k, int x) {
        for (int i = 1; i <= k; i++) {
            bitsArray[hash(x, i)] = true;
        }
    }

    public boolean research(int x) {
        for (int i = 1; i <= k; i++) {
            if (!bitsArray[hash(x, i)])return false;
        }
        return true;
    }

    public void displayResult() {
        System.out.println(Colors.YELLOW_BOLD+"---------------------------------------");
        System.out.println("\nDEBUG " + Colors.RESET + getClass().getName().toString()+"\n");

        System.out.println(Arrays.toString(valuesArray));
        System.out.println(Arrays.toString(bitsArray));
        System.out.println((research(researchedValue) ? "Element " +researchedValue+" trouvé" : "Element "+researchedValue+" non trouvé"));
        System.out.println(Colors.YELLOW_BOLD+"---------------------------------------"+ Colors.RESET);
    }
}
