/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.ralarcon.bloomfilter;

import fr.ralarcon.utils.Colors;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ralarcon
 */
public class BloomFilterMain {
  
    static Scanner scanner =  new Scanner(System.in);
    
    public static void main(String[] args) {
        
        // Fill the array of booleans with False
        BloomFilter filter = new BloomFilterArray(500, 3);
    }
}