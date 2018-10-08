package com.test.dsalg.company.rotatelinkedlist;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class App {

	public static void main(String[] args) {
		App app = new App();
		app.invoke();
	}

	public void invoke() {
		
		SortableLinkedList<Integer> myLinkedList = new SortableLinkedList<>();
		
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("Number of elements");
			Integer numOfElements = Integer.parseInt("10");

			int[] numbers = printRandomNumbers(numOfElements.intValue(), numOfElements.intValue());
			for (int i : numbers) {
				myLinkedList.add(i);
			}
			
			System.out.println("Index to start soring");
			Integer indexSorting = Integer.parseInt("5");
			
			System.out.println(myLinkedList);
			myLinkedList.sortIndexBased(indexSorting.intValue());
			System.out.println(myLinkedList);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public final Random gen = new Random();  
	public int[] printRandomNumbers(int n, int maxRange) {  
	    assert n <= maxRange : "cannot get more unique numbers than the size of the range";  
	      
	    int[] result = new int[n];  
	    Set<Integer> used = new HashSet<Integer>();  
	      
	    for (int i = 0; i < n; i++) {  
	          
	        int newRandom;  
	        do {  
	            newRandom = gen.nextInt(maxRange+1);  
	        } while (used.contains(newRandom));  
	        result[i] = newRandom;  
	        used.add(newRandom);  
	    }  
	    return result;  
	}  
}
