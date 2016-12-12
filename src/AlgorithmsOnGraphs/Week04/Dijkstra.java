package AlgorithmsOnGraphs.Week04;

/**
 * Created by Thiloshon on 12-Dec-16.
 */

import java.util.*;

public class Dijkstra {
    private int distance(Vertex[] adj, int s, int t) {

       /* for(Vertex v : adj){
            //System.out.println(v.num+" is explored");
            for(Edge e : v.edges){
                //System.out.println(e.end);
            }
        }*/

        Comparator<Vertex> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        Queue<Vertex> queueB = new PriorityQueue(adj.length, adjacencyComparator);

        adj[s].distance = 0;

        for (int x = 0; x < adj.length; x++) {
            queueB.add(adj[x]);
        }

        while (queueB.size() > 0) {
            Vertex temp = queueB.remove();
            //System.out.println("removed " + temp.num);

            for (Edge edge : temp.edges) {
                if (adj[edge.end].distance > temp.distance + edge.weight) {
                    queueB.remove(adj[edge.end]);
                    adj[edge.end].distance = temp.distance + edge.weight;
                    adj[edge.end].parent = temp.num;
                    //System.out.println("Adding "+ adj[edge.end].num+" with weight " + adj[edge.end].distance);
                    queueB.add(adj[edge.end]);
                }
            }
        }

        if(adj[t].distance== 1000000000){
            return -1;
        }

        return adj[t].distance;
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Vertex[] adj = (Vertex[]) new Vertex[n];
        //ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            //adj[x - 1].array.add(y - 1);
            adj[x - 1].edges.add(new Edge(x - 1, y - 1, w));
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }

    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        d.run();
    }

    class Vertex {
        int num;
        int distance = (int) 1000000000;
        int parent = -1;
        ArrayList<Edge> edges = new ArrayList<Edge>();

        public Vertex(int num) {
            this.num = num;
        }
    }

    class Edge {
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight) {
            this.end = end;
            this.start = start;
            this.weight = weight;
        }
    }

}


