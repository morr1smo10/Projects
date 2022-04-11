/*Morris Mo
CSC112 Fall 2021
Programming Assignment 4
November 15, 2021
This program implements bubble sort and merge sort
The largest integer array that could be declared has an approximate array size of 267124749
The largest short array that could be declared has an approximate array size of 534249505
Mergesort couldn't take 5 minutes, even though I declared the largest array I could declare, the mergesort is still
quick and less than 5 minutes. So I did the largest array that could be sorted by both bubblesort and mergesort in
5 minutes.
For an integer array that has a size of 640000, the time of execution of bubblesort is 292797 milliseconds (about 4.88
mintues), while the time of execution of mergesort is 155 milliseconds (about 0.0026 minutes). So in five minutes, the
program could at most sorts approximately an integer array with the size of 640000
 */


import java.util.Scanner;
import java.util.Random;

public class Main {
    public static int countb = 0;
    public static int countm = 0;
    public static int printIt = 1;

    public static void main(String args[]){
        Random rand = new Random();
        Scanner scnr = new Scanner(System.in);
        int size, choice;
        long start,end;

        System.out.println("What size array do you want to sort?"); //determine the size of array
        size = scnr.nextInt();

        int[] ar = new int[size];
        int[] arb = new int[size];
        for (int i=0; i<size; i++){
            ar[i] = rand.nextInt(100); //create the array for mergesort
        }
        for (int i=0; i<size; i++){
            arb[i] = ar[i]; //copy the created array for bubblesort
        }

        System.out.println("Which sort do you want to do? Type 1 for bubblesort, 2 for mergesort, or 3 for both.");
        choice = scnr.nextInt();

        switch (choice){ // chose the sort method that you want to do
            case 1:
                if (size<=20){ //if size is larger than 20, don't print
                    System.out.println("Original array for bubblesort:");
                    print(arb);
                }
                start = System.currentTimeMillis(); //the timer
                bubbleSort(arb);
                end = System.currentTimeMillis();
                System.out.println("Time for execution of bubblesort is " + (end - start) + " milliseconds.");
                if (size<=20){
                    System.out.println("After bubblesort:");
                    print(arb);
                }
                System.out.println("Number of comparisons in bubblesort is " + countb);
                break;
            case 2:
                if (size<=20){
                    System.out.println("Original array for mergesort:");
                    print(ar);
                }
                start = System.currentTimeMillis();
                mergeSort(ar,0); // 0 means not printing left or right
                end = System.currentTimeMillis();
                System.out.println("Time for execution of mergesort is " + (end - start) + " milliseconds.");
                if (size<=20){
                    System.out.println("After mergesort:");
                    print(ar);
                }
                System.out.println("Number of comparisons in mergesort is " + countm);
                break;
            case 3: // just put case 1 and case 2 together
                if (size<=20){
                    System.out.println("Original array for bubblesort:");
                    print(arb);
                }
                start = System.currentTimeMillis();
                bubbleSort(arb);
                end = System.currentTimeMillis();
                System.out.println("Time for execution of bubblesort is " + (end - start) + " milliseconds.");
                if (size<=20){
                    System.out.println("After bubblesort:");
                    print(arb);
                }
                System.out.println("Number of comparisons in bubblesort is " + countb);
                System.out.println();

                if (size<=20){
                    System.out.println("Original array for mergesort:");
                    print(ar);
                }
                start = System.currentTimeMillis();
                mergeSort(ar,0);
                end = System.currentTimeMillis();
                System.out.println("Time for execution of mergesort is " + (end - start) + " milliseconds.");
                if (size<=20){
                    System.out.println("After mergesort:");
                    print(ar);
                }
                System.out.println("Number of comparisons in mergesort is " + countm);
                break;
            default:
                System.out.println("Invalid choice");
                break;

        }

    }

    public static void bubbleSort(int[] array){
        for (int i = 0; i<array.length-1; i++){
            int indexOfLargest = 0;
            int max = array[0];
            for (int j = 0; j<array.length-1-i; j++){
                countb++; // the counter of bubblesort comparison
                if (array[j+1]>max){//the efficient way of doing bubblesort: not swaping everyting, only swap the larget
                    max=array[j+1];
                    indexOfLargest=j+1;
                }
            }
            //this is the swap
            int temp = array[array.length-1-i];
            array[array.length-1-i] = array[indexOfLargest];
            array[indexOfLargest] = temp;
        }
    }

    public static void mergeSort(int[] array, int side){ //add a parameter
        // add print left right statement
        // add a case when nothing
        if (printIt == 1){ //this block print the left side content and right side content
            if (side == 1){
                System.out.println("Left side:");
                print(array);
            }
            else if (side == 2){
                System.out.println("Right side:");
                print(array);
            }
            else {

            }
        }

        int n = array.length;
        if (n<=1){ // this is the base case of the recursion
            return;
        }
        int middle = n/2;
        int[] left = new int[middle]; // we split the array into left part and right part
        int[] right = new int[n-middle];
        for(int i =0; i<=middle-1; i++){
            left[i] = array[i]; // fill in the created array accordingly
        }
        for(int i =middle; i<=n-1; i++){
            right[i-middle] = array[i];
        }
        mergeSort(left,1); // the recursion
        mergeSort(right,2);
        merge(left,right,array); // call merge to merge the left and right
    }

    public static void merge(int[] left, int[] right, int[] array){
        int nLeft = left.length;
        int nRight = right.length;
        int i=0, j=0, k=0;
        while (i<nLeft && j<nRight){ //this merge all the lefts and rights into the array
            if(left[i]<=right[j]){
                array[k]=left[i];
                i++;
                countm++; // this count the number of comparison
            }
            else{
                array[k]=right[j];
                j++;
                countm++;
            }
            k++;
        }
        while (i<nLeft){ // these two while are in case there are remaining not merged in left or right
            array[k]=left[i];
            i++;
            k++;
            countm++;
        }
        while (j<nRight){
            array[k]=right[j];
            j++;
            k++;
            countm++;
        }
    }

    public static void print(int[] array){ // this is a print array method
        for (int i = 0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }
}
