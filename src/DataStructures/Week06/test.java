package DataStructures.Week06;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by Thiloshon on 16-Oct-16.
 */
public class test {
    public static void main(String[] args) {

        /*test tree = new test();
        int arr[] = new int[20];
        for(int x =0; x<20; x++){
            arr[x]=x;
        }
        int n = arr.length;
        root = tree.sortedArrayToBST(arr, 0, n - 1);
        System.out.println("Preorder traversal of constructed BST");
        ArrayList<Integer> arr4 = new ArrayList();
        tree.preOrder(root, arr4);
        System.out.println("Preorder traversal of constructed BST Done");

        for(int x:arr4){
            System.out.print(x+" ");
        }*/



        test te = new test();
        //te.insert();



    }

    void insertLooya(String st){

        Vertex e = sortedArrayToBST(null, 0, st.length()-1, st);
        inOrderTraversal(0,e);

    }

    /* A function that constructs Balanced Binary Search Tree
     from a sorted array */
    Vertex sortedArrayToBST(Vertex arr, int start, int end, String st) {


        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Vertex node = new Vertex(mid, mid, null, null, arr, st.charAt(mid));
        //node.parent = arr;
        //System.out.println(mid);

        /* Recursively construct the left subtree and make it
         left child of root */
        node.left = sortedArrayToBST(node, start, mid - 1, st);

        /* Recursively construct the right subtree and make it
         right child of root */
        node.right = sortedArrayToBST(node, mid + 1, end, st);

        return node;
    }

    class Vertex {
        long key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        char character;
        long size;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(long key, long sum, Vertex left, Vertex right, Vertex parent, char character) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.character = character;
            long num1;
            long num2;
            /*try {
                num1 = this.left.size;
            } catch (NullPointerException e) {
                num1 = 0;
            }
            try {
                num2 = this.right.size;
            } catch (NullPointerException e) {
                num2 = 0;
            }

            this.size = num1 + num2 + 1;*/

            //this.size = key + 1;
        }
    }

    Vertex root1 = null;

    static Node root;



    /* A utility function to print preorder traversal of BST */
    void preOrder(Node node, ArrayList arr ) {
        if (node == null) {
            return;
        }
        //System.out.print(node.data + " ");
        arr.add(node.data);
        preOrder(node.left, arr);
        preOrder(node.right, arr);
    }

    class Node {

        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }
    static int[] sortedArrayToBST(int start, int end, int[] arr, int num) {

        System.out.println("num is " + num);

        /* Base Case */
        if (start > end) {
            return arr;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;

        arr[num] = mid;
        num++;

        /* Recursively construct the left subtree and make it
         left child of root */
        sortedArrayToBST(start, mid - 1, arr, num);
        num++;

        /* Recursively construct the right subtree and make it
         right child of root */
        sortedArrayToBST(mid + 1, end, arr, num);
        num++;


        return arr;

    }

    void inOrderTraversal(int total, Vertex vertex) {

        long num = -9;
        long num2 = -9;
        long num3 = -9;
        long num4 = -9;
        long num5 = -9;
        char num11 = '.';
        char num22 = '.';
        char num33 = '.';
        char num44 = '.';
        char num55 = '.';
        try {
            num = vertex.key;
            num11 = vertex.character;
        } catch (NullPointerException t) {
        }
        try {
            num2 = vertex.parent.key;
            num22 = vertex.parent.character;
        } catch (NullPointerException t) {
        }
        try {
            num33 = vertex.left.character;
            num3 = vertex.left.key;
        } catch (NullPointerException t) {
        }
        try {
            num44 = vertex.right.character;
            num4 = vertex.right.key;
        } catch (NullPointerException t) {
        }
        try {
            num5 = vertex.size;
        } catch (NullPointerException t) {
        }

        try {
            System.out.println("Accessing : " + num + " " + " of char " + num11 + ", " + num2 + " " + " of char " + num22 + ", " + num3 + " of char " + num33 + ", " + num4 + " of char " + num44 + " and sum is " + num5);
        } catch (NullPointerException t) {

        }
        //System.out.println(vertex.key);
        try {
            //arr[vertex.key] = vertex.character;
        } catch (NullPointerException e) {

        }
        Vertex left = null;
        Vertex right = null;

        if (vertex != null) {
            try {
                left = vertex.left;
            } catch (NullPointerException e) {
                left = null;
            }
            try {
                right = vertex.right;
            } catch (NullPointerException e) {
                right = null;
            }

            if (left == null && right == null) {
                total += vertex.key;

                return;
            }

            if (left != null) {
                total += vertex.key;
                inOrderTraversal(total, left);
            }
            if (right != null) {
                total += vertex.key;
                inOrderTraversal(total, right);
            }
        }


        return;
    }
}
