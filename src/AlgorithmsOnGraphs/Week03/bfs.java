package AlgorithmsOnGraphs.Week03;

/**
 * Created by Thiloshon on 10-Dec-16.
 */

import java.util.*;

public class BFS {
    private static int distance(Vertex[] adj, int s, int t) {
        //write your code here

        adj[s].distance = 0;

        Queue<Vertex> queue = new LinkedList<Vertex>();

        queue.add(adj[s]);

        while (queue.size() > 0) {
            Vertex temp = queue.remove();
            temp.array.stream().filter(num -> adj[num].distance == -1).forEach(num -> {
                queue.add(adj[num]);
                adj[num].distance = temp.distance + 1;
            });
        }


        /*while(queue.size()>0){
            Vertex temp = queue.remove();
            for(int num: temp.array){
                if(adj[num].distance==-1){
                    queue.add(adj[num]);
                    adj[num].distance=temp.distance+1;
                }
            }
        }*/



        return adj[t].distance;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        Vertex[] adj = (Vertex[]) new Vertex[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].array.add(y - 1);
            adj[y - 1].array.add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }

    static class Vertex {
        int num;
        boolean reached = false;
        int distance = -1;
        ArrayList<Integer> array = new ArrayList<Integer>();

        public Vertex(int num) {
            this.num = num;
        }
    }
}

