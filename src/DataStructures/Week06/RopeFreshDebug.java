package DataStructures.Week06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by Thiloshon on 19-Oct-16.
 */
public class RopeFreshDebug {

    Vertex root = null;
    String st = "";

    public static void main(String[] args) throws IOException {
        new RopeFreshDebug().run();
    }

    public void run() throws IOException {
        FastScanner in = new FastScanner();
        st = in.next();

        insertToTree(st);

        for (int q = in.nextInt(); q > 0; q--) {
            int i = in.nextInt();
            int j = in.nextInt();
            int k = in.nextInt();

            if (k >= j) {
                ReAlign(i, j, k);
            } else {
                ReAlign2(i, j, k);
            }
        }
        inOrderPrint();
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
        int adjustmentValue = 0;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(long key, long sum, Vertex left, Vertex right, Vertex parent, char character) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.character = character;

        }
    }

    //to update size after splitting or merging tree.
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


    /**
     * Searches for the given key in the tree with the given root
     * // and calls splay for the deepest visited node after that.
     * // Returns pair of the result and the new root.
     * // If found, result is a pointer to the node with the given key.
     * // Otherwise, result is a pointer to the node with the smallest
     * // bigger key (next value in the order).
     * // If the key is bigger than all keys in the tree,
     * // then result is null.
     */
    VertexPair find(Vertex root, int key, int num, int end) {
        if (root == null) {
            VertexPair vt = new VertexPair();
            vt.left = null;
            vt.right = null;
            return vt;
        }

        int width = end - num + 1;
        long ke = key - num + 1;

        Vertex current = OrderStatistics(root, ke);
        root = splay(current);

        if (root == null) {
            VertexPair vt = new VertexPair();
            vt.left = null;
            vt.right = null;
            return vt;
        }

        if (ke<=width){
            return new VertexPair(root, root);
        }else{
            Vertex next = OrderStatistics(root, ke + 1);
            return new VertexPair(next, root);
        }
    }

    // to find nth smallest value in a tree.
    Vertex OrderStatistics(Vertex R, long k) {
        long s;

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
                Vertex vt = null;
                try {
                    vt = R.right;
                } catch (NullPointerException r) {
                }
                if (vt != null) {
                    R = R.right;
                    k = k - s - 1;
                } else {
                    if (k == R.size || k == R.size + 1) {
                        return R;
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    VertexPair split(Vertex root, int key, int num, int end) {
        VertexPair result = new VertexPair();
        VertexPair findAndRoot = find(root, key, num, end);
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

    void insertToTree(String st) {
        root = sortedArrayToBST(null, 0, st.length() - 1, st);
        postOrder(root);
    }

    /* A function that constructs Balanced Binary Search Tree
     from a sorted array */
    Vertex sortedArrayToBST(Vertex arr, int start, int end, String st) {
        // Base Case
        if (start > end) {
            return null;
        }

        // Get the middle element and make it root */
        int mid = (start + end) / 2;
        Vertex node = new Vertex(mid, mid, null, null, arr, st.charAt(mid));

        // Recursively construct the left subtree and make it
        // left child of root
        node.left = sortedArrayToBST(node, start, mid - 1, st);

        // Recursively construct the right subtree and make it
        // right child of root */
        node.right = sortedArrayToBST(node, mid + 1, end, st);

        return node;
    }

    //To Update size after creating tree
    public void postOrder(Vertex root) {
        if (root != null) {
            postOrder(root.left);
            postOrder(root.right);
            //Visit the node by Printing the node data
            long num1;
            long num2;
            try {
                num1 = root.left.size;
            } catch (NullPointerException e) {
                num1 = 0;
            }
            try {
                num2 = root.right.size;
            } catch (NullPointerException e) {
                num2 = 0;
            }
            root.size = num1 + num2 + 1;
        }
    }

    void ReAlign(int from, int to, int pivot) {
        VertexPair end = split(root, ((pivot + 1) + (to - from + 1)) - 1, 0, st.length()-1);
        Vertex middleleft = end.left;
        Vertex pivotRight = end.right;

        VertexPair middleRight = split(middleleft, to + 1, 0, (pivot + 1) + (to - from + 1) - 2);
        Vertex middle = middleRight.left;
        Vertex pivotLeft = middleRight.right;

        VertexPair leftMiddle = split(middle, from, 0, to);
        Vertex left = leftMiddle.left;
        middle = leftMiddle.right;

        int updateValue = -(to - from + 1);
        constantUpdate(pivotLeft, updateValue);
        int updateValue2 = pivot - from;
        constantUpdate(middle, updateValue2);

        root = merge(middle, pivotRight);
        root = merge(pivotLeft, root);
        root = merge(left, root);
    }

    void ReAlign2(int from, int to, int pivot) {
        if (pivot <= from) {
            if (pivot == 0) {
                pivot -= 1;
            }
            VertexPair leftMiddle = split(root, pivot, 0, st.length()-1);
            Vertex left = leftMiddle.left;
            Vertex middle = leftMiddle.right;

            if (pivot < 0) {
                pivot++;
            }

            VertexPair middleRight = split(middle, from, pivot, st.length()-1);
            middle = middleRight.left;
            Vertex right = middleRight.right;
            VertexPair pivoting = split(right, to + 1, from, st.length()-1);
            Vertex pivotLeft = pivoting.left;
            Vertex pivotRight = pivoting.right;

            int updateValue = (to - from + 1);
            constantUpdate(middle, updateValue);


            int updateValue2 = -(from - pivot);
            constantUpdate(pivotLeft, updateValue2);
            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));

        } else {
            VertexPair leftMiddle = split(root, pivot + to - from + 1, 0, st.length()-1);
            Vertex pivotRight = leftMiddle.right;
            Vertex middle2 = leftMiddle.left;
            VertexPair middleRight = split(middle2, to + 1, 0, pivot + to - from);
            Vertex pivotLeft = middleRight.right;
            Vertex mid = middleRight.left;

            VertexPair pivoting = split(mid, from, 0, to);
            Vertex left = pivoting.left;
            Vertex middle = pivoting.right;

            int updateValue = (pivot - from);
            constantUpdate(middle, updateValue);

            int updateValue2 = -(to - from + 1);
            constantUpdate(pivotLeft, updateValue2);
            root = merge(left, merge(pivotLeft, merge(middle, pivotRight)));
        }
    }

    void inOrderPrint() {
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

    void constantUpdate(Vertex root, int value) {
        if (root == null) return;
        root.adjustmentValue += value;
    }
}
