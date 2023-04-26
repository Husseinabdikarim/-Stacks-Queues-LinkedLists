package com.husseinabdikarim;

//-----------------------------------------------------
// Title: Q1A
// Author: Hussein Abdikarim Hussein
// Description: This class gets matrix from a txt file and then returns it in spiral form
//-----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Q1A {

    public static int[][] readFile(File fileName) throws FileNotFoundException{

    //--------------------------------------------------------
    // Summary: reads a file takes every line and stores it as
    // a linked list
    // Precondition: requires the filename from the user in
    // order to access it
    // Postcondition: returns a linked list containing every
    // line of the matrix in form of linked lists
    // --------------------------------------------------------


        Scanner inputFile = new Scanner(fileName);

        //creating the linked list to store out matrix lines
        LinkedList<LinkedList<Integer>> bigList = new LinkedList<>();

        int[][] twoDarray;

        //iterating through the lines and splitting
        while (inputFile.hasNext()) {

            LinkedList<Integer> list = new LinkedList<>();

            String line = inputFile.nextLine();
            String[] txtSplitter = line.split(" ");
            for (int i = 0; i < txtSplitter.length; i++) {
                list.add(Integer.parseInt(txtSplitter[i]));
            }

            int thisNumber = list.size();
            if (thisNumber <= 5) {
                bigList.add(list);
            } else {
                break;
            }
        }
//      Checking if the list is not empty, if so we convert the linkList to a 2D array and return that.
        boolean bgL = !bigList.isEmpty();
        if (bgL) {
//            Initializing the 2D Array
            twoDarray = new int[bigList.size()][bigList.getFirst().size()];

            for (int i = 0; i < bigList.size(); i++) {
                for (int j = 0; j < bigList.getFirst().size(); j++) {
                    twoDarray[i][j] = bigList.get(i).get(j);
                }
            }
        }
        twoDarray = new int[0][0];


        return twoDarray;
    }

    public static void main(String[] args) throws IOException {
        String txtFile = "C:\\Assignment\\txt-files\\txt-files\\Q1A\\";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Input filename:");
        String filename = scanner.next();
        File file = new File(filename);
        Scanner scan = new Scanner(file);


        LinkList<Integer> result = spiralForm(readFile(file));
        result.print();
    }
    public static LinkList<Integer> spiralForm ( int[][] matrix){

        //--------------------------------------------------------
        // Summary: creates 4 boundaries and iterates through the matrix in spiral order
        // Precondition: The input matrix is an int[][]
        // Postcondition: It requires a 2D matrix as an input
        // --------------------------------------------------------

        LinkList<Integer> spiralOrder = new LinkList<>();

//       Here, we are creating the 4 boundaries that are used in the iteration of the 2D array
        int beginRow = 0;
        int endRow = matrix.length - 1;
        int beginColumn = 0;
        int endColomn = matrix[0].length - 1;

        all:
        while (beginRow <= endRow && beginColumn <= endColomn) {

            for (int i = beginRow; i <= endRow; i++) {
                if (matrix[i][beginColumn] == -1) {
//                  breaks from all the loops because at this point in found -1.
                    break all;
                } else
                    spiralOrder.add(matrix[i][beginColumn]);
            }
            beginColumn++;

            for (int i = beginColumn; i <= endColomn; i++) {
//                Checking if the current number equals -1.
                if (matrix[endRow][i] == -1) {
                    break all;
                } else
//                  Adding the 2D array in a LinkList
                    spiralOrder.add(matrix[endRow][i]);
            }
            endRow--;
//          Checking if up and bottom boundaries collided.
            if (beginRow <= endRow) {
                for (int i = endRow; i >= beginRow; i--) {
                    if (matrix[i][endColomn] == -1) {
                        break all;
                    } else
                        spiralOrder.add(matrix[i][endColomn]);
                }
            }
            endColomn--;

            if (beginColumn <= endColomn) {
                for (int i = endColomn; i >= beginColumn; i--) {
                    if (matrix[beginRow][i] == -1) {
                        break all;
                    } else
                        spiralOrder.add(matrix[beginRow][i]);
                }
            }
            beginRow++;

            if (beginRow <= endRow) {
                for (int i = beginRow; i <= endRow; i++) {
                    if (matrix[i][beginColumn] == -1) {
                        break all;
                    } else
                        spiralOrder.add(matrix[i][beginColumn]);
                }
            }
            beginColumn++;
//            Checking if right and left collide.
            if (beginColumn <= endColomn) {
                for (int i = beginColumn; i <= endColomn; i++) {
                    if (matrix[endRow][i] == -1) {
                        break all;
                    } else
                        spiralOrder.add(matrix[endRow][i]);
                }
            }
        }
        return spiralOrder;
    }
}

class LinkList<I extends Number> {
    private SingleNode head;
    private SingleNode tail;
    private int Size;

    public LinkList() {
        this.head = null;
        this.tail = null;
    }

    private class SingleNode<I extends Number> {
        int item;
        SingleNode next;
        SingleNode previous;

        public SingleNode(int item) {
            this.item = item;
        }

        public int getItem() {
            return item;
        }

        public void setItem(int item) {
            this.item = item;
        }

        public SingleNode getNext() {
            return next;
        }

        public void setNext(SingleNode next) {
            this.next = next;
        }

        public SingleNode getPrevious() {
            return previous;
        }

        public void setPrevious(SingleNode previous) {
            this.previous = previous;
        }

        @Override
        public String toString() {
            return ("[ " + item + " ]");
        }
    }
//  the addLast method of the linkList
    public void add(int t) {
        SingleNode node = new SingleNode(t);
        if (head == null) {
            head = tail = new SingleNode(t);
            Size++;
        } else if (head != null) {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
            Size++;
        }
    }

//  Printing the LinkList method
    public void print() {
        SingleNode iter = head;
        System.out.print("[");
        for (int i = 0; i < Size; i++) {
            if (iter == tail)
                System.out.print(iter.getItem());
            else
                System.out.print(iter.getItem() + " ,");
            iter = iter.getNext();
        }
        System.out.print("]");
    }

}


