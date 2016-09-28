package DataStructures.Week03;

/**
 * Created by Thiloshon on 21-Sep-16.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private int[] data;
    private List<Swap> swaps = new ArrayList<>();

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new int[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        // The following naive implementation just sorts
        // the given sequence using selection sort algorithm
        // and saves the resulting sequence of swaps.
        // This turns the given array into a heap,
        // but in the worst case gives a quadratic number of swaps.
        //
        // TODO: replace by a more efficient implementation
        for (int i = 0; i < data.length; ++i) {
            for (int j = i + 1; j < data.length; ++j) {
                if (data[i] > data[j]) {
                    swaps.add(new Swap(i, j));
                    int tmp = data[i];
                    data[i] = data[j];
                    data[j] = tmp;
                }
            }
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwapsEfficient();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    /**
     * All The Below Are Mine :)
     */

    private void generateSwapsEfficient() {
        for(int g = data.length/2; g>0; g--){
            siftDown(g);
        }

    }

    private int parent(int i) {
        return i / 2;
    }

    private int leftChild(int i) {
        return 2 * i;
    }

    private int rightChild(int i) {
        return (i * 2) + 1;
    }

    private void siftUP(int i) {
        while (i > 1 && data[parent(i)] < data[i]) {
            swaps.add(new Swap(parent(i), i));
            int tmp = data[parent(i)];
            data[parent(i)] = data[i];
            data[i] = tmp;
        }
    }

    private void siftDown(int i) {
        //System.out.println("im in " + i);
        int maxIndex = i;
        try{
            int l = leftChild(i);

            if (l <= data.length && data[l] < data[maxIndex]) {
                maxIndex = l;
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }


        try{
            int r = rightChild(i);

            if (r <= data.length && data[r] < data[maxIndex]) {
                maxIndex=r;
            }
        }catch (ArrayIndexOutOfBoundsException e){

        }

        if(i!=maxIndex){
            //System.out.println(data[i]);
            swaps.add(new Swap(i-1, maxIndex-1));
            int tmp = data[maxIndex];
            data[maxIndex] = data[i];
            data[i] = tmp;
            /*for(int g : data){
                System.out.print(g+" ");
            }*/
            //System.out.println("");
            siftDown(maxIndex);
        }
    }

    /*private void insert(int p) {
        if(data.length==)
    }*/
}



























