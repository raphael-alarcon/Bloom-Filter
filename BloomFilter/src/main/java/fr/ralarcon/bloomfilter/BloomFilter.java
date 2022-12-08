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
    
    private boolean[] bitsArray;
    
    int[] valuesArray;
    
    public static Random rdm = new Random();
    
    int researchedValue = 154;
    
    public BloomFilter(int size, int hashNumber) {
        this.m = size;
        this.k = hashNumber;
    }
    
    public int hash(Object x, int i) {
        return x.hashCode() * i * 31 % m;
    }
    
    public int[] initArrayOfValues(int size, int bound, int k) {
        return new int[size];
    }

    public void hashValue(int k, int x) {
    }

    public boolean research(int x) {
        return false;
    }
        

    public void displayResult() {
        System.out.println(Colors.YELLOW_BOLD+"\n―――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println("\nDEBUG " + Colors.RESET + getClass().getName()+"\n");
        System.out.println(Arrays.toString(valuesArray));
    }
}
