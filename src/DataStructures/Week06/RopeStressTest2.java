package DataStructures.Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Thiloshon on 15-Oct-16.
 */


class RopeStressTest2 {

    class Rope {
        String s;

        Rope(String s) {
            this.s = s;
        }

        void process(int i, int j, int k) {
            // Replace this code with a faster implementation
            String t = s.substring(0, i) + s.substring(j + 1);
            s = t.substring(0, k) + s.substring(i, j + 1) + t.substring(k);
        }

        void processEfficient(int i, int j, int k) {

        }

        String result() {
            return s;
        }

    }

    public static void main(String[] args) throws IOException {
        new RopeStressTest2().run();
    }

    protected String getSaltString(int no) {
        String SALTCHARS = "qwertyuioplkjhgfdsazxcvbnm";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < no) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public void run() throws IOException {
        /*FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String st = in.next();

        //Vertex vt = new Vertex(0, 0, null, null, null, st.charAt(0));
        //root = vt;
        insertLooya(st);
        *//*for (int x = 1; x < st.length(); x++) {
            //insert(x, st.charAt(x));

        }*//*

        *//*System.out.println("The tree is");
        inOrderTraversal(0, root);*//*

        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();

            int k = in.nextInt();

            //rope.process(i, j, k);


            if (k >= j) {
                ReAlign(i, j, k);
            } else {
                ReAlign2(i, j, k);
            }
            *//*System.out.println("Entering");
            inOrderTraversal(0, root);
            System.out.println("Finished");*//*
        }

        inorder();*/


        while (true) {

            //int desh = (int) Math.random() * 1000+1;
            String st = getSaltString(5);

            long startTime = System.currentTimeMillis();

            System.out.println(st);
            Rope rope = new Rope(st);
            Vertex vt = new Vertex(0, 0, null, null, null, st.charAt(0));
            root = vt;
            //System.out.println("hey");
            insertLooya(st);

            //System.out.println("The deapth is " + maxDepth(root));

            //inOrderTraversal(0,root);

            //System.out.println("Dope");
            /*for (int x = 1; x < st.length(); x++) {
                //insert(x, st.charAt(x));

                if(x%1000==0)
                System.out.println(x);
            }*/

            int q = (int) (Math.random() * 10);
            q = 3;
            System.out.println(q);

            for (q = q; q > 0; q--) {
                int i = (int) (Math.random() * 10000) % st.length();
                int j = 0;
                while (j < i) {
                    j = (int) (Math.random() * 10000) % st.length();
                }

                int k = st.length();

                int sub = st.length() - (j - i + 1);
                while (k > sub) {
                    k = (int) (Math.random() * 10000) % st.length();
                }


                System.out.println(i + " " + j + " " + k);

                //rope.process(i, j, k);


                if (k >= j) {
                    ReAlign(i, j, k);
                } else {
                    ReAlign2(i, j, k);
                }


            }
            //System.out.println("now its " + maxDepth(root));

            //System.out.println("Done!");


            //char[] arr = new char[st.length()];
            //arr = inOrderTraversal(0, root, arr);

            String stsdf = inorder().toString();

            //System.out.println("Done");
            /*//String stsdf = "";
            for (int x = 0; x < st.length(); x++) {
                stsdf += arr[x];
            }*/
            String and = rope.result();
            System.out.println(stsdf);

            /*if (!and.equals(stsdf)) {
                System.out.println(" and the ans1 is " + and + " and ans2 is " + stsdf);
                break;
            } else {
                System.out.println(" and Done! " + " and the ans1 is " + and + " and ans2 is " + stsdf);
                System.out.println("");
            }*/

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Second is " + totalTime / 1000);
            System.out.println("And num is " + num);


        }


    }

    int maxDepth(Vertex node) {
        if (node == null)
            return 0;
        else {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }


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

    // Vertex of a splay tree
    class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        char character;
        long size;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent, char character) {
            this.key = key;
            //this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.character = character;
            long num1;
            long num2;

            //this.size = key + 1;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        //v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
        if (v.left != null) {
            v.left.parent = v;
        }
        if (v.right != null) {
            v.right.parent = v;
        }
    }

    void smallRotation(Vertex v) {
        Vertex parent = v.parent;
        if (parent == null) {
            return;
        }
        Vertex grandparent = v.parent.parent;
        if (parent.left == v) {
            Vertex m = v.right;
            v.right = parent;
            parent.left = m;
        } else {
            Vertex m = v.left;
            v.left = parent;
            parent.right = m;
        }
        update(parent);
        update(v);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
    }

    void bigRotation(Vertex v) {
        if (v.parent.left == v && v.parent.parent.left == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else if (v.parent.right == v && v.parent.parent.right == v.parent) {
            // Zig-zig
            smallRotation(v.parent);
            smallRotation(v);
        } else {
            // Zig-zag
            smallRotation(v);
            smallRotation(v);
        }
    }

    // Makes splay of the given vertex and returns the new root.
    Vertex splay(Vertex v) {
        if (v == null) return null;
        while (v.parent != null) {
            if (v.parent.parent == null) {
                smallRotation(v);
                break;
            }
            bigRotation(v);
        }
        return v;
    }

    class VertexPair {
        Vertex left;
        Vertex right;

        VertexPair() {
        }

        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex last = root;
        Vertex next = null;
        while (v != null) {
            if (v.key >= key && (next == null || v.key < next.key)) {
                next = v;
            }
            last = v;
            if (v.key == key) {
                break;
            }
            if (v.key < key) {
                v = v.right;
            } else {
                v = v.left;
            }
        }
        root = splay(last);
        return new VertexPair(next, root);
    }

    VertexPair split(Vertex root, int key) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        root = findAndRoot.right;
        result.right = findAndRoot.left;
        if (result.right == null) {
            result.left = root;
            return result;
        }
        result.right = splay(result.right);
        result.left = result.right.left;
        result.right.left = null;
        if (result.left != null) {
            result.left.parent = null;
        }
        update(result.left);
        update(result.right);
        return result;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
        while (right.left != null) {
            right = right.left;
        }
        right = splay(right);
        right.left = left;
        update(right);
        return right;
    }

    // Code that uses splay tree to solve the problem

    Vertex root = null;

    void insert(int x, char ch) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null, ch);
        }
        root = merge(merge(left, new_vertex), right);
    }

    void ReAlign(int from, int to, int pivot) {
        /*VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;

        VertexPair pivoting = split(right, ((pivot + 1) + (to - from + 1)) - 1);
        Vertex pivotLeft = pivoting.left;
        Vertex pivotRight = pivoting.right;*/


        VertexPair end = split(root, ((pivot + 1) + (to - from + 1)) - 1);
        Vertex middleleft = end.left;
        Vertex pivotRight = end.right;

        VertexPair middleRight = split(middleleft, to + 1);
        Vertex middle = middleRight.left;
        Vertex pivotLeft = middleRight.right;

        VertexPair leftMiddle = split(middle, from);
        Vertex left = leftMiddle.left;
        middle = leftMiddle.right;

        /*System.out.println("-----left-----");
        inOrderTraversal(0, left, new char[10]);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle, new char[10]);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft, new char[10]);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight, new char[10]);
        System.out.println("--------");*/

        int updateValue = -(to - from + 1);
        //inOrderUpdate(updateValue, pivotLeft);
        inorder2(updateValue, pivotLeft);
        int updateValue2 = pivot - from;
        //inOrderUpdate(updateValue2, middle);
        inorder2(updateValue2, middle);
        //inOrderUpdate(-updateValue, middle);

        /*System.out.println("And after updating,");

        System.out.println("-----left-----");
        inOrderTraversal(0, left, new char[10]);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle, new char[10]);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft, new char[10]);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight, new char[10]);
        System.out.println("--------");*/

        root = merge(middle, pivotRight);
        /*System.out.println("Checking mid way 01");
        inOrderTraversal(0, root, new char[10]);*/

        root = merge(pivotLeft, root);
        /*System.out.println("Checking mid way 02");
        inOrderTraversal(0, root, new char[10]);*/

        root = merge(left, root);
        /*System.out.println("Checking mid way 02");
        inOrderTraversal(0, root, new char[10]);*/

        //root = merge(left, merge(pivotLeft, merge(pivotRight, middle)));


    }

    /*void ReAlign2(int from, int to, int pivot) {
        if (pivot == 0) {
            pivot -= 1;
        }
        VertexPair leftMiddle = split(root, pivot); //pivot+1
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, from);
        middle = middleRight.left;
        Vertex right = middleRight.right;

        VertexPair pivoting = split(right, to + 1);
        Vertex pivotLeft = pivoting.left;
        Vertex pivotRight = pivoting.right;


        *//*System.out.println("-----left in 2-----");
        inOrderTraversal(0, left, new char[10]);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle, new char[10]);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft, new char[10]);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight, new char[10]);
        System.out.println("--------");*//*

        int updateValue = (to - from + 1);
        inOrderUpdate(updateValue, middle);
        //inOrderUpdate(updateValue, pivotRight);
        //pivot += 1;
        if (pivot < 0) {
            pivot++;
        }
        int updateValue2 = -(from - pivot);
        inOrderUpdate(updateValue2, pivotLeft);


        root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));


    }*/

    void ReAlign2(int from, int to, int pivot) {

        if (pivot <= from) {
            if (pivot == 0) {
                pivot -= 1;
            }
            VertexPair leftMiddle = split(root, pivot);
            Vertex left = leftMiddle.left;
            Vertex middle = leftMiddle.right;
            VertexPair middleRight = split(middle, from);
            middle = middleRight.left;
            Vertex right = middleRight.right;

            VertexPair pivoting = split(right, to + 1);
            Vertex pivotLeft = pivoting.left;
            Vertex pivotRight = pivoting.right;


            /*System.out.println("-----left in 2-----");
            inOrderTraversal(0, left, new char[10]);
            System.out.println("-----midddle-----");
            inOrderTraversal(0, middle, new char[10]);
            System.out.println("-----pivotLeft-----");
            inOrderTraversal(0, pivotLeft, new char[10]);
            System.out.println("-----pivotRight-----");
            inOrderTraversal(0, pivotRight, new char[10]);
            System.out.println("--------");*/

            int updateValue = (to - from + 1);
            //inOrderUpdate(updateValue, middle);
            inorder2(updateValue, middle);
            //inOrderUpdate(updateValue, pivotRight);
            if (pivot < 0) {
                pivot++;
            }

            int updateValue2 = -(from - pivot);
            //inOrderUpdate(updateValue2, pivotLeft);
            inorder2(updateValue2, pivotLeft);

          /*  System.out.println("And after updating,");

            System.out.println("-----left-----");
            inOrderTraversal(0, left, new char[10]);
            System.out.println("-----midddle-----");
            inOrderTraversal(0, middle, new char[10]);
            System.out.println("-----pivotLeft-----");
            inOrderTraversal(0, pivotLeft, new char[10]);
            System.out.println("-----pivotRight-----");
            inOrderTraversal(0, pivotRight, new char[10]);
            System.out.println("--------");*/


            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));
        } else {
            VertexPair leftMiddle = split(root, pivot + to - from + 1);
            Vertex pivotRight = leftMiddle.right;
            Vertex middle2 = leftMiddle.left;
            VertexPair middleRight = split(middle2, to + 1);
            Vertex pivotLeft = middleRight.right;
            Vertex mid = middleRight.left;

            VertexPair pivoting = split(mid, from);
            Vertex left = pivoting.left;
            Vertex middle = pivoting.right;

            int updateValue = (pivot - from);
            //inOrderUpdate(updateValue, middle);
            inorder2(updateValue, middle);
            //inOrderUpdate(updateValue, pivotRight);


            int updateValue2 = -(to - from + 1);
            //inOrderUpdate(updateValue2, pivotLeft);
            inorder2(updateValue2, pivotLeft);
            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));

        }


    }

    void inorder2(int value, Vertex vertex) {
        if (vertex == null) {
            return;
        }

        //keep the nodes in the path that are waiting to be visited
        Stack<Vertex> stack = new Stack<Vertex>();
        Vertex node = vertex;

        //first node to be visited will be the left one
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        // traverse the tree
        while (stack.size() > 0) {

            // visit the top node
            node = stack.pop();
            node.key += value;
            //System.out.print(node.data + " ");
            if (node.right != null) {
                node = node.right;

                // the next node to be visited is the leftmost
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
    }

    static int num = 0;

    void inOrderUpdate(int value, Vertex vertex) {
        num++;

        Vertex left = null;
        Vertex right = null;

        if (vertex == null) {
            return;
        }
        vertex.key += value;


        if (vertex.left != null) {

            left = vertex.left;
        }

        if (vertex.right != null) {
            right = vertex.right;
        }


        if (left == null && right == null) {
            return;
        }
        if (left != null) {
            inOrderUpdate(value, left);
        }
        if (right != null) {
            inOrderUpdate(value, right);
        }
    }



    StringBuilder inorder() {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return null;
        }

        //keep the nodes in the path that are waiting to be visited
        Stack<Vertex> stack = new Stack<Vertex>();
        Vertex node = root;

        //first node to be visited will be the left one
        while (node != null) {
            stack.push(node);
            node = node.left;
        }

        // traverse the tree
        while (stack.size() > 0) {

            // visit the top node
            node = stack.pop();
            //System.out.print(node.character);
            sb.append(node.character);
            if (node.right != null) {
                node = node.right;

                // the next node to be visited is the leftmost
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }

        return sb;
    }

    void inOrderTraversal(int total, Vertex vertex) {

        int num = -9;
        int num2 = -9;
        int num3 = -9;
        int num4 = -9;
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

    void insertNode2(int x, char ch) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null, ch);
        }
        root = merge(merge(left, new_vertex), right);

        System.out.println("-----------------------");
        inOrderTraversal(0, root);
    }

    void insertNode(int x, char ch) {
        if (root == null) {
            root = new Vertex(x, x, null, null, null, ch);
            return;
        }
        Vertex v = root;
        while (true) {
            if (x < v.key) {
                v = root.left;
                if (v == null) {
                    root.left = new Vertex(x, x, null, null, root, ch);
                    return;
                }
            } else if (x > v.key) {
                v = root.right;
                if (v == null) {
                    root.right = new Vertex(x, x, null, null, root, ch);
                    return;
                }
            }
        }

    }

    void insert(String st) {

        RopeStressTest2 tree = new RopeStressTest2();
        int arr[] = new int[st.length()];
        for (int x = 0; x < st.length(); x++) {
            arr[x] = x;
        }
        int n = arr.length;
        rootNode = tree.sortedArrayToBST(arr, 0, n - 1);
        //System.out.println("Preorder traversal of constructed BST");
        ArrayList<Integer> arr4 = new ArrayList();
        tree.preOrder(rootNode, arr4);
        //System.out.println("Preorder traversal of constructed BST Done");

        for (int x : arr4) {
            System.out.println(x + " getting added");
            insertNode(x, st.charAt(x));
            System.out.println(x + " is added");
            System.out.println("-----------------------");
            inOrderTraversal(0, root);
        }

    }

    void insertLooya(String st) {

        root = sortedArrayToBST(null, 0, st.length() - 1, st);
        //inOrderTraversal(0,root);

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

    static Node rootNode;

    /* A function that constructs Balanced Binary Search Tree
     from a sorted array */
    Node sortedArrayToBST(int arr[], int start, int end) {


        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = new Node(arr[mid]);
        //System.out.println(mid);

        /* Recursively construct the left subtree and make it
         left child of root */
        node.left = sortedArrayToBST(arr, start, mid - 1);

        /* Recursively construct the right subtree and make it
         right child of root */
        node.right = sortedArrayToBST(arr, mid + 1, end);

        return node;
    }

    /* A utility function to print preorder traversal of BST */
    void preOrder(Node node, ArrayList arr) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
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
}


