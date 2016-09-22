package DataStructures.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Thiloshon on 16-Sep-16.
 */
// Java program to find height using parent array
// Java program to find height using parent array
public class ArbitaryTreeHeight {

    // This function fills depth of i'th element in parent[].  The depth is
    // filled in depth[i].
    void fillHeight(int parent[], int i, int depth[]) {

        // If depth[i] is already filled
        if (depth[i] != 0) {
            return;
        }

        // If node at index i is root
        if (parent[i] == -1) {
            depth[i] = 1;
            return;
        }

        // If depth of parent is not evaluated before, then evaluate
        // depth of parent first
        if (depth[parent[i]] == 0) {
            fillHeight(parent, parent[i], depth);
        }

        // Depth of this node is depth of parent plus 1
        depth[i] = depth[parent[i]] + 1;
    }

    // This function returns height of binary tree represented by
    // parent array
    int findHeight(int parent[], int n) {

        // Create an array to store depth of all nodes/ and
        // initialize depth of every node as 0 (an invalid
        // value). Depth of root is 1
        int depth[] = new int[n];
        for (int i = 0; i < n; i++) {
            depth[i] = 0;
        }

        // fill depth of all nodes
        for (int i = 0; i < n; i++) {
            fillHeight(parent, i, depth);
        }

        // The height of binary tree is maximum of all depths.
        // Find the maximum value in depth[] and assign it to ht.
        int ht = depth[0];
        for (int i = 1; i < n; i++) {
            if (ht < depth[i]) {
                ht = depth[i];
            }
        }
        return ht;
    }

    // Driver program to test above functions
    /*public static void main(String args[]) {

        BinaryTree tree = new BinaryTree();

        // int parent[] = {1, 5, 5, 2, 2, -1, 3};
        int parent[] = new int[]{-1, 0, 0, 1, 1, 3, 5};

        int n = parent.length;
        System.out.println("Height is  " + tree.findHeight(parent, n));
    }*/
    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new DataStructures.Week01.treeheight().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }


}

class treeheight {
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

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }

            /*for(int h : parent) {
                System.out.println(h);

            }*/
        }

        void computeHeight() {
            // Replace this code with a faster implementation
            ArbitaryTreeHeight tree = new ArbitaryTreeHeight();

            System.out.println(tree.findHeight(parent, n));

        }
    }


    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        tree.computeHeight();
    }
}
