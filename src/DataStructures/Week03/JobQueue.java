package DataStructures.Week03;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
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

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private void assignJobsEfficient() {
        Heap heap = new Heap(numWorkers);
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];

        for (int x = 0; x < jobs.length; x++) {
            int[] top = heap.peekMin();
            int[] temp = heap.changePriority(1, top[1] + jobs[x]);
            assignedWorker[x] = top[0] - 1;
            startTime[x] = temp[1];

            System.out.println("--------------------");
            System.out.println("--------------------");
            for (int[] f : heap.data) {
                System.out.println(f[0] + " " + f[1]);
            }
            System.out.println("returned to main loop. next is " + (x + 1));

        }

        System.out.println(heap.extractMin()[0]);
        System.out.println(heap.extractMin()[0]);
        System.out.println(heap.extractMin()[0]);

        System.out.println("Done!");

    }
}


class Heap {


    int size = 0;
    int capacity;
    int[][] data; //TODO: Change the initialization value

    public Heap(int capacity) {
        this.data = new int[capacity + 1][2];
        this.capacity = capacity;
        for (int i = 1; i <= capacity; i++) {
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
        while (i > 1 && data[parent(i)][1] < data[i][1]) {
            int[] tmp = data[parent(i)];
            data[parent(i)] = data[i];
            data[i] = tmp;
        }
    }

    void siftDown(int i) {
        //System.out.println("im in " + i);
        int maxIndex = i;
        try {
            int l = leftChild(i);

            if (l <= data.length && data[l][1] < data[maxIndex][1]) {
                maxIndex = l;
                //System.out.println("in one inner");
            } else if (l <= data.length && data[l][1] == data[maxIndex][1] && data[l][0] < data[maxIndex][0]) {
                maxIndex = l;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            //System.out.println("in one");
        }


        try {
            int r = rightChild(i);
            //System.out.println("data[r][l] = " + data[r][1] + " data[maxIndex][1] " + data[maxIndex][1]);
            if (r <= data.length && data[r][1] < data[maxIndex][1]) {
                maxIndex = r;
                //System.out.println("in two inner");
            } else if (r <= data.length && data[r][1] == data[maxIndex][1] && data[r][0] < data[maxIndex][0]) {
                maxIndex = r;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("in two");
        }

        if (i != maxIndex) {
            //System.out.println(data[i]);

            int[] tmp = data[maxIndex];
            data[maxIndex] = data[i];
            data[i] = tmp;

            if (data[i][1] == data[i + 1][1] && data[i][0] > data[i + 1][0] && (i % 2 == 0)) {
                tmp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = tmp;
            }

            //System.out.println("Inside");
            for (int[] g : data) {
                System.out.println(g[0] + " " + g[1]);
            }
            System.out.println("");


            siftDown(maxIndex);
        }

        try {
            if (data[i][1] == data[i + 1][1] && data[i][0] > data[i + 1][0] && (i % 2 == 0)) {
                int[] tmp = data[i];
                data[i] = data[i + 1];
                data[i + 1] = tmp;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }

    void initialize(int p) {
        if (size > capacity + 1) {
            System.out.println("Max Size Reached");
        } else {
            size++;
            data[size][0] = p;
            data[size][1] = 0;
            siftUP(size);

        }

        for (int[] f : data) {
            System.out.println(f[0] + " " + f[1]);
        }
        System.out.println("--------------------");


    }

    void insert(int p) {
        if (size > capacity) {
            System.out.println("Max Size Reached");
        } else {
            size++;
            data[size][1] = p;
            siftUP(size);
        }
    }

    int[] extractMin() {
        int[] result = data[1];
        data[1] = data[size];
        size--;
        siftDown(1);

        return result;

    }

    int[] peekMin() {
        int[] result = data[1];

        return result;

    }

    void remove(int p) {
        data[p][1] = (int) Double.NEGATIVE_INFINITY;
        siftUP(p);
        extractMin();
    }

    int[] changePriority(int i, int p) {
        int oldp = data[i][1];
        int index = data[i][0];
        data[i][1] = p;
        if (p > oldp) {
            siftDown(i);
        } else
            siftUP(i);
        int[] temp = new int[2];
        temp[0] = index;
        temp[1] = oldp;
        return temp;
    }

    @Override
    public String toString() {
        return "Heap{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}

