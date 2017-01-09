package AlgorithmsOnGraphs.Week04;

/**
 * Created by Thiloshon on 12-Dec-16.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPathsv2 {
    private long negativeCycle(Vertex[] adj, int start) {
        // write your code here
        adj[start].distance = 0;

        for (int x = 0; x < adj.length; x++) {
            for (Vertex v : adj) {
                Relax(v, adj, null);
            }
        }
        ArrayList<Vertex> holder = new ArrayList<>();

        for (Vertex v : adj) {
            Relax(v, adj, holder);
        }

        if (holder.size() > 0) {
            // add to queue and breadth first search and all these are infinite arbitrage

            Queue<Vertex> q = new LinkedList();
            for (Vertex v : holder) {
                q.add(v);
                v.inHolder = true;
            }
            while (q.size() > 0) {
                Vertex temp = q.remove();
                if (!temp.leave) {
                    temp.leave = true;
                    temp.inHolder = true;
                    for (Edge e : temp.edges) {
                        q.add(adj[(int) e.end]);
                    }
                }
            }
        }

        for (Vertex v : adj) {
            if (v.distance == Long.MAX_VALUE) {
                System.out.println("*");
            } else if (v.inHolder) {
                System.out.println("-");
            } else {
                System.out.println(v.distance);
            }
        }
        return 0;
    }

    boolean Relax(Vertex v, Vertex[] adj, ArrayList<Vertex> holder) {

        boolean check = false;
        for (Edge edge : v.edges) {
            if (!v.leave && v.distance != Long.MAX_VALUE) {
                if (adj[(int) edge.end].distance > v.distance + edge.weight) {
                    adj[(int) edge.end].distance = v.distance + edge.weight;
                    adj[(int) edge.end].parent = v.num;
                    check = true;
                    if (holder != null) {
                        holder.add(adj[(int) edge.end]);
                    }

                }
            }

        }
        return check;
    }

    public static void main(String[] args) {
        ShortestPathsv2 n = new ShortestPathsv2();
        n.run();
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Vertex[] adj = new Vertex[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].edges.add(new Edge(x - 1, y - 1, w));
        }
        int start = scanner.nextInt() - 1;
        negativeCycle(adj, start);
    }

    class Vertex {
        long num;
        long distance = Long.MAX_VALUE;
        long parent = -1;
        ArrayList<Edge> edges = new ArrayList<Edge>();
        boolean leave = false;
        boolean inHolder = false;

        public Vertex(int num) {
            this.num = num;
        }
    }

    class Edge {
        long start;
        long end;
        long weight;


        public Edge(int start, int end, long weight) {
            this.end = end;
            this.start = start;
            this.weight = weight;
        }
    }

}

