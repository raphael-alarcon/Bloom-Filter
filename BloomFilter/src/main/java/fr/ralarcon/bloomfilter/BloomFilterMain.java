/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license―default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.ralarcon.bloomfilter;

import fr.ralarcon.utils.Colors;

import java.util.Scanner;

/**
 *
 * @author ralarcon
 */
public class BloomFilterMain {
  
    static Scanner scanner =  new Scanner(System.in);

    // Set the parameters of the benchmark here : (m, k, bound of the random values)
    static BloomFilterBenchmark benchmark = new BloomFilterBenchmark(300, 3, 1000000);
    
    public static void main(String[] args) {
        menu();
    }

    /**
     * Menu principal de l'application
     */
    public static void menu() {
        Colors.printSeparator();
        System.out.println("\nBloom Filter implementation by ALARCON Raphaël\n");
        System.out.println("[1] Run filter with simple array implementation (random values, m = 300, k = 3)");
        System.out.println("[2] Run filter with ArrayList implementation");
        System.out.println("[3] Run filter with LinkedList implementation");
        System.out.println("[4] Run benchmark on all three types of data structures ("+BloomFilterBenchmark.NB_SIMULATIONS+" simulations)");
        System.out.println("[5] Run detailled benchmark on a data structure");
        System.out.println("[6] Exit.");

        // If the user enters a non-integer value, the program will send an error message and ask for a new input
        int answer;
        try {
            answer = Integer.parseInt(scanner.nextLine());
            if (!(answer > 0 && answer <=6)) {
                menu();
                return;
            }
            runChoice(answer);
        } catch (NumberFormatException e) {
            System.out.println(Colors.RED_BOLD+"Error: "+ Colors.RESET+"Not a number, please enter a valid number from (1 - 6).");
            menu();
        }
    }

    /**
     * Run the user's choice from the menu
     *
     * @param answer The user's choice in form of an integer (1 - 6)
     */
    public static void runChoice(int answer) {
        BloomFilter filter;
        switch (answer) {
            case 1:
                filter = new BloomFilterArray(300, 3);
                filter.displayResult();
                break;
            case 2:
                filter = new BloomFilterArrayList(300, 3);
                filter.displayResult();
                break;
            case 3:
                filter = new BloomFilterLinkedList(300, 3);
                filter.displayResult();
                break;
            case 4:
                benchmark.run(); // Run the benchmark with m = 300, k = 3 and values going from 0 ― 1000000
                break;
            case 5:
                // Ask user for the type of data structure to use for the benchmark
                System.out.println("Enter the type of structure you want to use (\"LinkedList\", \"SimpleArray\" or \"ArrayList\"):");
                String choice = scanner.nextLine();
                switch (choice) {
                    case "LinkedList":
                        filter = benchmark.linkedListFilter;
                        break;
                    case "ArrayList":
                        filter = benchmark.arrayListFilter;
                        break;
                    case "SimpleArray":
                        filter = benchmark.arrayFilter;
                        break;
                    default:
                        runChoice(answer);
                        return;
                }
                benchmark.displayBenchmark(filter);
                break;
            case 6:
                System.exit(0);
                break;
        }
        menu();
    }
}