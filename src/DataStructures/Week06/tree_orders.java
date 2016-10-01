package DataStructures.Week06;

/**
 * Created by Thiloshon on 01-Oct-16.
 */

import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeOrders {
        int n;
        int[] key, left, right;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) {
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            inOrderTraversal(result, 0);

            return result;
        }

        ArrayList inOrderTraversal(ArrayList<Integer> arr, int index) {
            if (left[index] == -1 && right[index] == -1) {
                arr.add(key[index]);
                return arr;
            }
            if (left[index] != -1) {
                inOrderTraversal(arr, left[index]);
            }
            arr.add(key[index]);
            if (right[index] != -1) {
                inOrderTraversal(arr, right[index]);
            }
            return arr;
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            preOrderTraversal(result, 0);

            return result;
        }

        ArrayList preOrderTraversal(ArrayList<Integer> arr, int index) {
            if (left[index] == -1 && right[index] == -1) {
                arr.add(key[index]);
                return arr;
            }
            arr.add(key[index]);
            if (left[index] != -1) {
                preOrderTraversal(arr, left[index]);
            }
            if (right[index] != -1) {
                preOrderTraversal(arr, right[index]);
            }
            return arr;
        }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            // Finish the implementation
            // You may need to add a new recursive method to do that
            postOrderTraversal(result, 0);

            return result;
        }

        ArrayList postOrderTraversal(ArrayList<Integer> arr, int index) {
            if (left[index] == -1 && right[index] == -1) {
                arr.add(key[index]);
                return arr;
            }

            if (left[index] != -1) {
                postOrderTraversal(arr, left[index]);
            }
            if (right[index] != -1) {
                postOrderTraversal(arr, right[index]);
            }
            arr.add(key[index]);
            return arr;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_orders().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}

