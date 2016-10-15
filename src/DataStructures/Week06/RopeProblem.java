package DataStructures.Week06;

/**
 * Created by Thiloshon on 11-Oct-16.
 */

import java.io.*;
import java.util.*;

class RopeProblem {

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
        new RopeProblem().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String st = in.next();
        Rope rope = new Rope(st);
        Vertex vt = new Vertex(0, 0, null, null, null, st.charAt(0));
        root = vt;
        for (int x = 1; x < st.length(); x++) {
            insert(x, st.charAt(x));
        }

        //inOrderTraversal(0, root, new char[st.length()]);

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


            System.out.println("Entering");
            inOrderTraversal(0, root, new char[st.length()]);
            System.out.println("Finished");
        }


        char[] arr = new char[st.length()];
        arr = inOrderTraversal(0, root, arr);
        String stsdf = "";
        for (int x = 0; x < st.length(); x++) {

            System.out.print(arr[x]);
            //stsdf+=arr[x];
            /*System.out.println(find(root,x).right.character);*/
           /* VertexPair vtd = find(root, x);
            root=vtd.right;
            splay(vtd.left);
            //System.out.print(root.character);
            System.out.println("------------");
            inOrderTraversal(0, root);
            System.out.println("------------");*/
        }


        //out.println(rope.result());
        //System.out.println(stsdf);
        //out.close();
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
        long key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        char character;
        //long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(long key, long sum, Vertex left, Vertex right, Vertex parent, char character) {
            this.key = key;
            //this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.character = character;
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

        System.out.println("-----left-----");
        inOrderTraversal(0, left, new char[10]);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle, new char[10]);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft, new char[10]);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight, new char[10]);
        System.out.println("--------");

        int updateValue = -(to - from + 1);
        inOrderUpdate(updateValue, pivotLeft);
        int updateValue2 = pivot - from;
        inOrderUpdate(updateValue2, middle);
        //inOrderUpdate(-updateValue, middle);

        System.out.println("And after updating,");

        System.out.println("-----left-----");
        inOrderTraversal(0, left, new char[10]);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle, new char[10]);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft, new char[10]);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight, new char[10]);
        System.out.println("--------");

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


            System.out.println("-----left in 2-----");
            inOrderTraversal(0, left, new char[10]);
            System.out.println("-----midddle-----");
            inOrderTraversal(0, middle, new char[10]);
            System.out.println("-----pivotLeft-----");
            inOrderTraversal(0, pivotLeft, new char[10]);
            System.out.println("-----pivotRight-----");
            inOrderTraversal(0, pivotRight, new char[10]);
            System.out.println("--------");

            int updateValue = (to - from + 1);
            inOrderUpdate(updateValue, middle);
            //inOrderUpdate(updateValue, pivotRight);
            if (pivot < 0) {
                pivot++;
            }

            int updateValue2 = -(from - pivot);
            inOrderUpdate(updateValue2, pivotLeft);

            System.out.println("And after updating,");

            System.out.println("-----left-----");
            inOrderTraversal(0, left, new char[10]);
            System.out.println("-----midddle-----");
            inOrderTraversal(0, middle, new char[10]);
            System.out.println("-----pivotLeft-----");
            inOrderTraversal(0, pivotLeft, new char[10]);
            System.out.println("-----pivotRight-----");
            inOrderTraversal(0, pivotRight, new char[10]);
            System.out.println("--------");


            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));
        }else{
            VertexPair leftMiddle = split(root, pivot+to-from+1);
            Vertex pivotRight = leftMiddle.right;
            Vertex middle2 = leftMiddle.left;
            VertexPair middleRight = split(middle2, to + 1);
            Vertex pivotLeft = middleRight.right;
            Vertex mid = middleRight.left;

            VertexPair pivoting = split(mid, from);
            Vertex left = pivoting.left;
            Vertex middle = pivoting.right;

            int updateValue = (pivot - from );
            inOrderUpdate(updateValue, middle);
            //inOrderUpdate(updateValue, pivotRight);


            int updateValue2 = -(to - from +1);
            inOrderUpdate(updateValue2, pivotLeft);
            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));

        }


    }

    void inOrderUpdate(int value, Vertex vertex) {

        Vertex left = null;
        Vertex right = null;

        try {
            vertex.key += value;
        } catch (NullPointerException e) {
        }
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
                return;
            }
            if (left != null) {
                inOrderUpdate(value, left);
            }
            if (right != null) {
                inOrderUpdate(value, right);
            }
        }
        return;
    }

    char[] inOrderTraversal(int total, Vertex vertex, char[] arr) {

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
            //num5 = vertex.sum;
        } catch (NullPointerException t) {
        }

        try {
            System.out.println("Accessing : " + num + " " + " of char " + num11 + ", " + num2 + " " + " of char " + num22 + ", " + num3 + " of char " + num33 + ", " + num4 + " of char " + num44 + " and sum is " + num5);
        } catch (NullPointerException t) {

        }
        //System.out.println(vertex.key);
        try {
            arr[(int) vertex.key] = vertex.character;
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

                return arr;
            }

            if (left != null) {
                total += vertex.key;
                inOrderTraversal(total, left, arr);
            }
            if (right != null) {
                total += vertex.key;
                inOrderTraversal(total, right, arr);
            }
        }


        return arr;
    }
}

