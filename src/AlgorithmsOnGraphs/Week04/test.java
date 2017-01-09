package AlgorithmsOnGraphs.Week04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Thiloshon on 12-Dec-16.
 */
public class test {

    public static void main(String[] args) {


        Comparator<Vertex> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        /*Comparator<Vertex> adjacencyComparator = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex left, Vertex right) {
                if(left.num< (right.num)){
                    return 1;
                }
                return -1;
            }
        };*/

        Queue<Vertex> queueB = new PriorityQueue(10, adjacencyComparator);


        Vertex v1 = new Vertex(5);
        v1.distance=5;
        Vertex v2 = new Vertex(1);
        v2.distance=4;
        Vertex v3 = new Vertex(15);
        v3.distance=15;


        queueB.add(v1);
        queueB.add(v2);
        queueB.add(v3);

        System.out.println(queueB.remove().distance);
        System.out.println(queueB.remove().distance);
        System.out.println(queueB.remove().distance);

        queueB.add(v1);
        queueB.add(v2);


        v3.distance = 1;

        queueB.add(v3);

        System.out.println(queueB.remove().distance);
        System.out.println(queueB.remove().distance);
        System.out.println(queueB.remove().distance);

    }


    /**
     *
     */
    static class Vertex {
        int num;
        int distance = (int)Double.POSITIVE_INFINITY;
        int parent = -1;
        ArrayList<Integer> array = new ArrayList<Integer>();

        public Vertex(int num) {
            this.num = num;
        }


    }
}

