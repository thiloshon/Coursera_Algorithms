package AlgorithmsOnGraphs.Week04;

/**
 * Created by Thiloshon on 12-Dec-16.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private int negativeCycle(Vertex[] adj) {
        // write your code here
        boolean answer = false;
        adj[0].distance = 0;
        //System.out.println(adj.length);

        /*for(Vertex v : adj){
            System.out.println(v.distance+" is explored");
            for(Edge e : v.edges){
                System.out.println(e.weight);
            }
        }*/

        for (int x = 0; x <= adj.length; x++) {
            //System.out.println(x);
            boolean check = false;
            for (Vertex v : adj) {
                //check = Relax(v, adj);
                if(Relax(v, adj) == true){
                    check = true;
                }
            }


            if (check && x == adj.length) {
                //System.out.println("im ok");
                answer = true;
            }

            /*if (!check) {
                break;
            }*/

        }


        if (answer == true)
            return 1;

        return 0;
    }

    boolean Relax(Vertex v, Vertex[] adj) {

        boolean check = false;
        for (Edge edge : v.edges) {
            //System.out.println("Relaxing " + adj[edge.end].num);
            if (adj[edge.end].distance > v.distance + edge.weight) {
                //System.out.println("inside");
                adj[edge.end].distance = v.distance + edge.weight;
                adj[edge.end].parent = v.num;
                check = true;
            }
        }
        return check;
    }

    public static void main(String[] args) {
        NegativeCycle n = new NegativeCycle();
        n.run();
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

            //double w2 = -Math.log(w);
            //adj[x - 1].array.add(y - 1);
            adj[x - 1].edges.add(new Edge(x - 1, y - 1, w));
        }
        System.out.println(negativeCycle(adj));
    }

    class Vertex {
        int num;
        double distance = (int) 1000000000;
        int parent = -1;
        ArrayList<Edge> edges = new ArrayList<Edge>();

        public Vertex(int num) {
            this.num = num;
        }
    }

    class Edge {
        int start;
        int end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.end = end;
            this.start = start;
            this.weight = weight;
        }
    }

}

