package com.husseinabdikarim;

//-----------------------------------------------------
// Title: Q2
// Author: Hussein Abdikarim Hussein
// ID: 99230997266
// Section: 5
// Assignment: 1
// Description: This class reads from a txt file and stores it in a stack,
// then compares those values in consecutive order.
//-----------------------------------------------------

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q2 {
//    As seen below, these 2 stacks are used for different calculations, the price one only
//    deals with the txt files while the other deals with the range values.
    static StackClass<Integer> prices = new StackClass<>();
    static StackClass<Integer> stack = new StackClass<>();
//    This array is used after reading from the txt to the stack.
    static int[] val;
    public static void main(String[] args) throws FileNotFoundException {
//        String location = "C:/Assignment/txt-files/txt-files/Q2/price3.txt";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input filename:");
        String loc = scanner.next();
        try {
            File file = new File(loc);
            Scanner scan = new Scanner(file);
//            Iterating through the scanner until its null.
            while (scan.hasNext()) {
                prices.push(scan.nextInt());
            }

            getRange();
            prices.printStack();
            stack.printStack();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not read the file");
        }

    }

    private static void getRange() {
        //--------------------------------------------------------
        // Summary: Uses an array for comparing values and returning numbers
        // based on the calculations which it later adds those numbers
        // to a stack and returns that.
        // Precondition: the val should not be null.
        // Postcondition: converts prices to array using the toArray method.
        // --------------------------------------------------------

        val = prices.toArray();

        int range = 0;
        for(int i = 0; i < val.length; i++) {
            range = 0;
            for(int j = i; j >= 0; j--) {
                if(val[i] <= val[j]){
                    range++;
                }else if(range == 1) {
                    range = 0;
                }
            }
            if(range == 0)
                stack.push(1);
            else
                stack.push(range);
        }
    }
}

class StackClass<T extends Number> {

    private Node first = null;
    private int size;

    private class Node {
        int value;
        Node next;

        public Node getNext(){
            return next;
        }
        public int getValue(){
            return value;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(int v) {
        Node oldFirst = first;
        first = new Node();
        first.value = v;
        first.next = oldFirst;
        size++;
    }

    public int pop() {
        int value = first.value;
        first = first.next;
        return value;
    }

    public int peek() {
        if(!isEmpty()) {
            return first.value;
        }else
            return -1;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void printStack() {
        Node temp = first;
        int[] val = new int[size];

        for(int i = size - 1; i >= 0; i--) {
            val[i] = temp.value;
            if(i != 0) {
                temp = temp.next;
            }
        }
        System.out.print("[");
        for(int j = 0; j < val.length; j++) {
            if( j == val.length - 1)
                System.out.print(val[j]);
            else
            System.out.print(val[j] + ", ");
        }
        System.out.println("]");
    }

    public int[] toArray() {
        Node temp = first;
        int[] array = new int[size];
        for(int i = size-1; i >= 0; i--) {
            array[i] = temp.getValue();
            temp = temp.getNext();
        }
        return array;
    }
}