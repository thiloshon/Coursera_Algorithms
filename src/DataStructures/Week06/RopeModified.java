package DataStructures.Week06;

/**
 * Created by Thiloshon on 17-Oct-16.
 */


import java.io.*;
import java.util.*;

class RopeModified {

    public static void main(String[] args) throws IOException {
        new RopeModified().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        PrintWriter out = new PrintWriter(System.out);
        String st = in.next();

        Vertex vt = new Vertex(0, 0, null, null, null, st.charAt(0));
        root = vt;
        insertLooya(st);
        /*for (int x = 1; x < st.length(); x++) {
            //insert(x, st.charAt(x));

        }*/

        //inOrderTraversal(0, root);

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
            /*System.out.println("Entering");
            inOrderTraversal(0, root);
            System.out.println("Finished");*/
        }

        inorder();
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

    void update(Vertex v) {
        if (v == null) return;
        long num1;
        long num2;
        try {
            num1 = v.left.size;
        } catch (NullPointerException e) {
            num1 = 0;
        }
        try {
            num2 = v.right.size;
        } catch (NullPointerException e) {
            num2 = 0;
        }

        v.size = num1 + num2 + 1;

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

        update(v);
        update(parent);
        v.parent = grandparent;
        if (grandparent != null) {
            if (grandparent.left == parent) {
                grandparent.left = v;
            } else {
                grandparent.right = v;
            }
        }
        update(v);
        update(parent);
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

        if (root == null) {
            VertexPair vt = new VertexPair();
            vt.left = null;
            vt.right = null;
            return vt;
        }
        long keys = root.key;
        Vertex temp = root;
        while (temp != null) {
            System.out.println();
            keys = temp.key;
            temp = temp.left;
        }
        long ke = key - keys + 1;

        Vertex current = OrderStatistics(root, ke);
        root = splay(current);
        //System.out.println("Heheheh");
        //inOrderTraversal(0, root);

        if (root == null) {
            VertexPair vt = new VertexPair();
            vt.left = null;
            vt.right = null;
            return vt;
        }

        if (root.key == key) {
            return new VertexPair(root, root);
        } else {
            Vertex next = OrderStatistics(root, ke + 1);
            return new VertexPair(next, root);
        }

        //System.out.println("down");
        //inOrderTraversal(0,next);
        /*inOrderTraversal(0,root);
        System.out.println("");
        inOrderTraversal(0,next);
        System.out.println("Done");*/

    }

    Vertex OrderStatistics2(Vertex R, long k) {
        //System.out.println(k);
        long s = 0;
        try {
            s = R.left.size;
        } catch (NullPointerException e) {
            s = 0;
        }

        if (k == s + 1) {
            return R;
        } else if (k < s + 1) {
            if (R.left != null) {
                return OrderStatistics(R.left, k);
            } else {
                return R;
            }
        } else if (k > s + 1) {
            //try{
            if (R.right != null) {
                return OrderStatistics(R.right, k - s - 1);
            } else {
                /*System.out.println("Bumba");
                inOrderTraversal(0,R);*/
                /*if (k>s+1){
                    return null;
                }else{*/
                //System.out.println("Sdfsdf" + s +" "+k+ " "+R.key);

                if (k == R.key || k == R.key + 1) {
                    return R;
                } else {
                    return null;
                }

                //}

            }

            /*}catch (NullPointerException e){
                return R;
            }*/
        }
        return R;
    }

    Vertex OrderStatistics(Vertex R, long k) {
        //System.out.println(k);
        long s = 0;

        if (R == null) {
            return null;
        }


        while (true) {
            try {
                s = R.left.size;
            } catch (NullPointerException e) {
                s = 0;
            }
            if (k == s + 1) {
                return R;
            } else if (k < s + 1) {
                if (R.left != null) {
                    R = R.left;
                } else {
                    return R;
                }
            } else if (k > s + 1) {
                //System.out.println("im here");
                //try{
                Vertex vt = null;
                try {
                    vt = R.right;
                    //System.out.println(R);
                } catch (NullPointerException r) {
                    //System.out.println("bum");
                }
                if (vt != null) {
                    R = R.right;
                    k = k - s - 1;
                } else {
                    //System.out.println("im in");
                    //System.out.println(k + " " +R.key);
                    if (k == R.size || k == R.size + 1) {
                        return R;
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    VertexPair split(Vertex root, int key) {
        // System.out.println("Searching for " + key + " in tree ");
        //inOrderTraversal(0, root);
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key);
        //System.out.println("Right is ");
        // inOrderTraversal(0, findAndRoot.right);
        // System.out.println("And left is ");
        //inOrderTraversal(0, findAndRoot.left);
        // System.out.println("THanks");
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
        //System.out.println("bumbum");
        // inOrderTraversal(0, result.right);
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

    void insert2(int x, char ch) {


        Vertex v = new Vertex(x, x, root, null, null, ch);
        root.parent = v;
        root = v;

    }

    /*void insert(String st) {

        root = sortedArrayToBST(st, 0, st.length()-1);

        postOrder(root);


    }*/

    void insertNode(int x, char ch) {
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

    void insertLooya(String st){

        root = sortedArrayToBST(null, 0, st.length()-1, st);
        postOrder(root);
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

    void insert(String st) {

        RopeModified tree = new RopeModified();
        int arr[] = new int[st.length()];
        for(int x =0; x<st.length(); x++){
            arr[x]=x;
        }
        int n = arr.length;
        rootNode = tree.sortedArrayToBST(arr, 0, n - 1);
        //System.out.println("Preorder traversal of constructed BST");
        ArrayList<Integer> arr4 = new ArrayList();
        tree.preOrder(rootNode, arr4);
        //System.out.println("Preorder traversal of constructed BST Done");

        for(int x:arr4){
            //System.out.print(x+" ");
            insertNode(x, st.charAt(x));
        }

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

    Vertex sortedArrayToBST(String st, int start, int end) {

        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Vertex node = new Vertex(mid, mid, null, null, null, st.charAt(mid));

        /* Recursively construct the left subtree and make it
         left child of root */
        Vertex vt = sortedArrayToBST(st, start, mid - 1);
        try{
            vt.parent = node;
        }catch (NullPointerException e){

        }
        node.left = vt;


        /* Recursively construct the right subtree and make it
         right child of root */
        vt = sortedArrayToBST(st, mid + 1, end);
        try{
            vt.parent = node;
        }catch (NullPointerException e){

        }

        node.right = vt;


        /*System.out.println("-------------");
        inOrderTraversal(0,root);
        System.out.println("-------------");*/
        return node;

    }

    public void postOrder(Vertex root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            //Visit the node by Printing the node data
            long num1;
            long num2;
            try{
                num1 = root.left.size;
            }catch (NullPointerException e){
                num1=0;
            }
            try{
                num2 = root.right.size;
            }catch (NullPointerException e){
                num2=0;
            }
            root.size = num1 + num2 + 1;
            //System.out.println(root.size);
        }
    }

    void ReAlign(int from, int to, int pivot) {
        //System.out.println("in");
        VertexPair end = split(root, ((pivot + 1) + (to - from + 1)) - 1);
        Vertex middleleft = end.left;
        Vertex pivotRight = end.right;

        //System.out.println("done11");
        //inOrderTraversal(0, middleleft);

        VertexPair middleRight = split(middleleft, to + 1);
        Vertex middle = middleRight.left;
        Vertex pivotLeft = middleRight.right;

        //System.out.println("done21");

        //inOrderTraversal(0, middle);

        VertexPair leftMiddle = split(middle, from);
        Vertex left = leftMiddle.left;
        middle = leftMiddle.right;

        /*System.out.println("-----left-----");
        inOrderTraversal(0, left);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight);
        System.out.println("--------");*/

        int updateValue = -(to - from + 1);
        //inOrderUpdate(updateValue, pivotLeft);
        inOrderUpdate(pivotLeft, updateValue);
        int updateValue2 = pivot - from;
        //inOrderUpdate(updateValue2, middle);
        inOrderUpdate(middle, updateValue2);
        //inOrderUpdate(-updateValue, middle);

        /*System.out.println("And after updating,");

        System.out.println("-----left-----");
        inOrderTraversal(0, left);
        System.out.println("-----midddle-----");
        inOrderTraversal(0, middle);
        System.out.println("-----pivotLeft-----");
        inOrderTraversal(0, pivotLeft);
        System.out.println("-----pivotRight-----");
        inOrderTraversal(0, pivotRight);
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

    void ReAlign2(int from, int to, int pivot) {

        if (pivot <= from) {
            if (pivot == 0) {
                pivot -= 1;
            }
            VertexPair leftMiddle = split(root, pivot);
            Vertex left = leftMiddle.left;
            Vertex middle = leftMiddle.right;
            //System.out.println("done1");
            //inOrderTraversal(0, middle);
            VertexPair middleRight = split(middle, from);
            middle = middleRight.left;
            Vertex right = middleRight.right;
            /*System.out.println("done2");
            inOrderTraversal(0, right);*/
            VertexPair pivoting = split(right, to + 1);
            Vertex pivotLeft = pivoting.left;
            Vertex pivotRight = pivoting.right;


            /*System.out.println("-----left in 2-----");
            inOrderTraversal(0, left);
            System.out.println("-----midddle-----");
            inOrderTraversal(0, middle);
            System.out.println("-----pivotLeft-----");
            inOrderTraversal(0, pivotLeft);
            System.out.println("-----pivotRight-----");
            inOrderTraversal(0, pivotRight);
            System.out.println("--------");*/

            int updateValue = (to - from + 1);
            //inOrderUpdate(updateValue, middle);
            inOrderUpdate(middle, updateValue);
            //inOrderUpdate(updateValue, pivotRight);
            if (pivot < 0) {
                pivot++;
            }

            int updateValue2 = -(from - pivot);
            //inOrderUpdate(updateValue2, pivotLeft);
            inOrderUpdate(pivotLeft, updateValue2);

            /*System.out.println("And after updating,");

            System.out.println("-----left-----");
            inOrderTraversal(0, left);
            System.out.println("-----midddle-----");
            inOrderTraversal(0, middle);
            System.out.println("-----pivotLeft-----");
            inOrderTraversal(0, pivotLeft);
            System.out.println("-----pivotRight-----");
            inOrderTraversal(0, pivotRight);
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
            inOrderUpdate(middle, updateValue);


            int updateValue2 = -(to - from + 1);
            inOrderUpdate(pivotLeft, updateValue2);
            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));

        }


    }

    void inorder() {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return;
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

        System.out.println(sb);
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
            System.out.println("Accessing : " + num + " " + " of char " + num11 + ", " + num2 + " " + " of char " + num22 + ", " + num3 + " of char " + num33 + ", " + num4 + " of char " + num44 + " and size is " + num5);
        } catch (NullPointerException t) {

        }
        //System.out.println(vertex.key);
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
    }

    void inOrderUpdate(Vertex root, int value) {
        if (root == null) {
            return;
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
            node.key += value;
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
}
