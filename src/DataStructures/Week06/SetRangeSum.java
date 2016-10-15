package DataStructures.Week06;

/**
 * Created by Thiloshon on 01-Oct-16.
 */

import java.io.*;
import java.util.*;

public class SetRangeSum {

    BufferedReader br;
    PrintWriter out;
    StringTokenizer st;
    boolean eof;

    // Splay tree implementation

    // Vertex of a splay tree
    class Vertex {
        int key;
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        long sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(int key, long sum, Vertex left, Vertex right, Vertex parent) {
            this.key = key;
            this.sum = sum;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    void update(Vertex v) {
        if (v == null) return;
        v.sum = v.key + (v.left != null ? v.left.sum : 0) + (v.right != null ? v.right.sum : 0);
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

    void insert(int x) {
        Vertex left = null;
        Vertex right = null;
        Vertex new_vertex = null;
        VertexPair leftRight = split(root, x);
        left = leftRight.left;
        right = leftRight.right;
        if (right == null || right.key != x) {
            new_vertex = new Vertex(x, x, null, null, null);
        }
        root = merge(merge(left, new_vertex), right);
    }

    Vertex next(Vertex root) {
        try {
            if (root.right != null) {
                return leftDescendant(root.right);
            } else {
                return rightAncestor(root);
            }
        } catch (NullPointerException r) {

        }
        return null;
    }

    Vertex leftDescendant(Vertex root) {
        if (root.left == null) {
            return root;
        } else {
            return leftDescendant(root.left);
        }
    }

    Vertex rightAncestor(Vertex root) {
        int num;
        try {
            num = root.parent.key;
        } catch (NullPointerException t) {
            return root;
        }
        if (root.key < num) {
            return root.parent;
        } else {
            return rightAncestor(root.parent);
        }

    }


    void erase(int x) {
        // Implement erase yourself
        VertexPair vp = find(root, x);
        root = vp.right;


        // System.out.println("just to reassure");
        // inOrderTraversal(0, root);
        // System.out.println("_----------------------------  DOne!");
        try {
            // System.out.println("Found key is " + root.key + " and erasing " + x);
        } catch (NullPointerException r) {

        }

        Vertex vptemp = vp.left;

        if (vptemp != null) {
            if (root.left == null) {
                root = root.right;
                //Vertex temp = null;
                try {
                    root.parent = null;
                } catch (NullPointerException e) {

                }
            } else if (root.right == null) {
                root = root.left;
                root.parent = null;
            } else {
                Vertex temp = next(root);

                //System.out.println("Ha Found the key : " + temp.key);


                //System.out.println("Sooo Close now");
                //splay(temp);
                splay(temp);
                //inOrderTraversal(0, splay(temp));
                //System.out.println("And after final splaying:");
                splay(vp.right);
                // inOrderTraversal(0, splay(vp.right));
                // System.out.println("_----------------------------  DOne!");


                /*splay(temp);
                splay(vp.right);

                System.out.println("Sooo Close now");
                inOrderTraversal(0, root);
                System.out.println("_----------------------------  DOne!");*/


                Vertex xp = root.left;
                root = root.right;
                //splay(vp.left);

                try {
                    root.left = xp;
                    root.parent = null;
                } catch (NullPointerException e) {

                }
                //root=vp.left;
                /*Vertex L = vp.left.left;
                Vertex R = vp.left.right;
                try{
                    R.left = L;
                }catch (NullPointerException e){

                }
                try{
                    L.parent = R;
                }catch (NullPointerException e){

                }
                root=R;
                try{
                    R.parent=null;
                }catch (NullPointerException e){

                }*/
            }
            //System.out.println(vp.left);
            /*if (vp.left.key == x) {
                splay(next(root));
                splay(vp.left);
                Vertex L = vp.left.left;
                Vertex R = vp.left.right;
                try {
                    R.left = L;
                } catch (NullPointerException e) {

                }

                //System.out.println(R);
                try {
                    L.parent = R;
                } catch (NullPointerException e) {

                }
                root = R;

                try {
                    R.parent = null;
                } catch (NullPointerException e) {

                }

            }*/
        } /*else {
            root = vp.left;
        }*/


        update(root);


    }

    boolean find(int x) {
        // Implement find yourself
        VertexPair vp = find(root, x);

        root = vp.right;

        if (vp.left != null) {
            return (vp.left.key == x) ? true : false;
        }
        return false;


    }

    long sum(int from, int to) {
        VertexPair leftMiddle = split(root, from);
        Vertex left = leftMiddle.left;
        Vertex middle = leftMiddle.right;
        VertexPair middleRight = split(middle, to + 1);
        middle = middleRight.left;
        Vertex right = middleRight.right;
        long ans = 0;
        // Complete the implementation of sum

        //ans = inOrderTraversal(0, middle);
        try {
            ans = middle.sum;
        } catch (NullPointerException r) {

        }
        root = merge(merge(left, middle), right);

        return ans;

    }

    int inOrderTraversal(int total, Vertex vertex) {

        int num = -9;
        int num2 = -9;
        int num3 = -9;
        int num4 = -9;
        long num5 = -9;
        try {
            num = vertex.key;
        } catch (NullPointerException t) {


        }
        try {
            num2 = vertex.parent.key;
        } catch (NullPointerException t) {

        }
        try {
            num3 = vertex.left.key;
        } catch (NullPointerException t) {

        }
        try {
            num4 = vertex.right.key;
        } catch (NullPointerException t) {

        }
        try {
            num5 = vertex.sum;
        } catch (NullPointerException t) {

        }
        try {
            //System.out.println("Accessing : " + num + " " + num2 + " " + num3 + " " + num4 + " and sum is " + num5);
        } catch (NullPointerException t) {

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
                return total;
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


        return total;
    }


    public static final int MODULO = 1000000001;

    void solve() throws IOException {
        int n = nextInt();
        int last_sum_result = 0;
        for (int i = 0; i < n; i++) {
            char type = nextChar();
            switch (type) {
                case '+': {
                    int x = nextInt();
                    //System.out.println("Processing " + i + " " + type + " " + x);
                    insert((x + last_sum_result) % MODULO);
                    //inOrderTraversal(0, root);
                   // System.out.println(i + " Done!!");
                }
                break;
                case '-': {
                    int x = nextInt();
                   // System.out.println("Processing " + i + " " + type + " " + x);
                    erase((x + last_sum_result) % MODULO);
                    //inOrderTraversal(0, root);
                  //  System.out.println(i + " Done!!");
                }
                break;
                case '?': {
                    int x = nextInt();
                   // System.out.println("Processing " + i + " " + type + " " + x);
                    out.println(find((x + last_sum_result) % MODULO) ? "Found" : "Not found");
                   // inOrderTraversal(0, root);
                   // System.out.println(i + " Done!!");
                }
                break;
                case 's': {
                    int l = nextInt();
                    int r = nextInt();
                    //System.out.println("Processing " + i + " " + type + " " + l);
                    long res = sum((l + last_sum_result) % MODULO, (r + last_sum_result) % MODULO);
                    out.println(res);
                    last_sum_result = (int) (res % MODULO);
                    //inOrderTraversal(0, root);//TODO:comment out all these
                    //System.out.println(i + " Done!!");
                }
            }
        }
    }

    SetRangeSum() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        solve();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new SetRangeSum();
    }

    String nextToken() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                eof = true;
                return null;
            }
        }
        return st.nextToken();
    }

    int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    char nextChar() throws IOException {
        return nextToken().charAt(0);
    }
}
