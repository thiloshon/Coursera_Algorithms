package AlgorithmsOnGraphs.Week05;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Thiloshon on 10-Jan-17.
 */
public class test {
    public static void main(String[] args) {

        Queue priorityQueue = new PriorityQueue();

        priorityQueue.add(5);
        priorityQueue.add(55);
        priorityQueue.add(25);
        priorityQueue.add(35);
        priorityQueue.add(45);

        Iterator iterator = priorityQueue.iterator();

        while (iterator.hasNext()) {
            int num = (int)iterator.next();

            run(priorityQueue);
            System.out.println(num);


        }
        priorityQueue.remove(5);
    }

    static void run(Queue r){
        r.remove(5);
    }

}
