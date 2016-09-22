package DataStructures.Week02;

/**
 * Created by Thiloshon on 22-Sep-16.
 * <p>
 * DISCLAIMER:
 * This is a 1 indexed heap , not 0 indexed.
 * This is a min heap , not max heap.
 */
public class PerfectHeap {

    int capacity = 10;
    int size = 0;
    private int[] data = new int[capacity]; //TODO: Change the initialization value

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
            int tmp = data[parent(i)];
            data[parent(i)] = data[i];
            data[i] = tmp;
        }
    }

    private void siftDown(int i) {
        //System.out.println("im in " + i);
        int maxIndex = i;
        try {
            int l = leftChild(i);

            if (l <= data.length && data[l] < data[maxIndex]) {
                maxIndex = l;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        try {
            int r = rightChild(i);

            if (r <= data.length && data[r] < data[maxIndex]) {
                maxIndex = r;
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }

        if (i != maxIndex) {
            //System.out.println(data[i]);

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

    private void insert(int p) {
        if (size >= capacity) {
            System.out.println("Max Size Reached");
        } else {
            size++;
            data[size] = p;
            siftUP(size);
        }
    }

    private int extractMin() {
        int result = data[1];
        data[1] = data[size];
        size--;
        siftDown(1);

        return result;

    }

    private void remove(int p) {
        data[p] = (int) Double.NEGATIVE_INFINITY;
        siftUP(p);
        extractMin();
    }

    private void changePriority(int i, int p) {
        int oldp = data[i];
        data[i] = p;
        if (p > oldp) {
            siftDown(i);
        } else
            siftDown(i);
    }

}
