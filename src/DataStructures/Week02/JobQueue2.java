package DataStructures.Week02;

/**
 * Created by Thiloshon on 22-Sep-16.
 */

import java.io.*;
import java.util.StringTokenizer;

public class JobQueue2 {
    private int numWorkers;
    private long[] jobs;

    private long[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue2().solve();
    }

    private void readData() throws IOException {
        numWorkers = (int) in.nextInt();
        int m = (int) in.nextInt();
        jobs = new long[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }


    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobsEfficient();
        writeResponse();
        out.close();
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

        public long nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private void assignJobsEfficient() {
        Heap2 heap = new Heap2(numWorkers);
        assignedWorker = new long[jobs.length];
        startTime = new long[jobs.length];

        for (int x = 0; x < jobs.length; x++) {
            long[] top = heap.peekMin();
            long[] temp = heap.changePriority(1, top[1] + jobs[x]);
            assignedWorker[x] = top[0] - 1;
            startTime[x] = temp[1];
        }
    }
}


class Heap2 {


    int size = 0;
    long capacity;
    long[][] data;

    public Heap2(int capacity) {
        this.data = new long[capacity + 1][2];
        this.capacity = capacity;
        for (long i = 1; i <= capacity; i++) {
            initialize(i);
        }

    }

    int parent(int i) {
        return i / 2;
    }

    int leftChild(int i) {
        return 2 * i;
    }

    int rightChild(int i) {
        return (i * 2) + 1;
    }

    void siftUP(int i) {
        while (i > 1 && data[(int) parent(i)][1] < data[(int) i][1]) {
            long[] tmp = data[(int) parent(i)];
            data[(int) parent(i)] = data[i];
            data[(int) i] = tmp;
        }
    }

    void siftDown(int i) {

        int maxIndex = i;
        try {
            int l = leftChild(i);

            if (l <= data.length && data[l][1] < data[maxIndex][1]) {
                maxIndex = l;
            } else if (l <= data.length && data[l][1] == data[maxIndex][1] && data[l][0] < data[maxIndex][0]) {
                maxIndex = l;

            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }


        try {
            int r = rightChild(i);
            if (r <= data.length && data[r][1] < data[maxIndex][1]) {
                maxIndex = r;
            } else if (r <= data.length && data[r][1] == data[maxIndex][1] && data[r][0] < data[maxIndex][0]) {
                maxIndex = r;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
        }

        if (i != maxIndex) {

            long[] tmp = data[maxIndex];
            data[maxIndex] = data[i];
            data[i] = tmp;

            siftDown(maxIndex);
        }

    }

    void initialize(long p) {
        if (size > capacity + 1) {
            System.out.println("Max Size Reached");
        } else {
            size++;
            data[size][0] = p;
            data[size][1] = 0;
            siftUP(size);

        }
    }

    long[] peekMin() {
        long[] result = data[1];

        return result;

    }

    long[] changePriority(int i, long p) {
        long oldp = data[i][1];
        long index = data[i][0];
        data[i][1] = p;
        if (p > oldp) {
            siftDown(i);
        } else
            siftUP(i);
        long[] temp = new long[2];
        temp[0] = index;
        temp[1] = oldp;
        return temp;
    }
}

