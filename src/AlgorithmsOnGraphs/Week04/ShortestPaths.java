package AlgorithmsOnGraphs.Week04;

/**
 * Created by Thiloshon on 12-Dec-16.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPaths {
    private long negativeCycle(Vertex[] adj, int start) {
        // write your code here
        boolean answer = false;
        adj[start].distance = 0;

        while (!answer) {
            for (int x = 0; x <= adj.length; x++) {
                boolean check = false;
                ArrayList<Vertex> holder = new ArrayList<>();
                /*int rollingNo = start;
                for (int y = 0; y < adj.length; y++) {
                    rollingNo = rollingNo % adj.length;
                    if (Relax(adj[rollingNo], adj, holder) == true) {
                        check = true; // checking if relaxation happened at all
                    }
                    rollingNo++;
                }*/
                for (Vertex v : adj) {
                    if (Relax(v, adj, holder) == true) {
                        check = true; // checking if relaxation happened at all
                    }
                }

                if (check && x == adj.length) { // Checking if relaxation happened in the vth iteration
                    answer = false;
                    if (holder.size() > 0) {
                        // add to queue and breadth fisrt search and all thses are infinite arbitrage

                        Queue<Vertex> q = new LinkedList();
                        for (Vertex v : holder) {
                            q.add(v);
                            Vertex parent = adj[(int)v.parent];
                            v.inHolder=true;
                            while(parent.num!=v.num){
                                System.out.println(v.num);
                                parent = adj[(int)parent.parent];
                                parent.inHolder=true;
                                System.out.println(parent.num);
                            }

                            //System.out.println("done");
                        }
                        while (q.size() > 0) {
                            Vertex temp = q.remove();
                            if (!temp.leave) {
                                temp.leave = true;
                                for (Edge e : temp.edges) {
                                    q.add(adj[(int) e.end]);
                                }
                            }
                        }
                    }
                } else {
                    answer = true;
                }
            }
        }

        Queue<Vertex> q = new LinkedList();
        q.add(adj[start]);
        boolean tracker = false;
        while (q.size() > 0) {
            Vertex temp = q.remove();
            if (temp.inHolder) {
                tracker = true;
            }
            if (!temp.finalcheck) {
                temp.finalcheck = true;
                for (Edge e : temp.edges) {
                    q.add(adj[(int) e.end]);
                    //System.out.println(adj[(int) e.end].num);
                }
                if (tracker) {
                    temp.afterCycle = true;
                }
            }
        }
        //System.out.println("done");

        /*for (Vertex v : adj) {
            if (v.num == start) {
                System.out.println("0");
                continue;
            }
            *//*if (v.leave && v.finalcheck){
                System.out.println("-");
            }else if(v.leave){
                System.out.println("*");
            }else if(Math.abs(v.distance) >=1000000000){
                System.out.println("*");
            }else {
                System.out.println( v.distance);
            }*//*
            if (v.leave) {
                System.out.println("-");
            } else if (Math.abs(v.distance) >= 1000000000) {
                System.out.println("*");
            } else {
                System.out.println(v.distance);
            }
        }*/

        for (Vertex v : adj) {
            //System.out.print(v.distance + " ");
            if (v.num == start) {
                System.out.println("0");
                continue;
            }
            if (!v.finalcheck) {
                System.out.println("*");
            } else if (v.leave && v.afterCycle) {
                System.out.println("-");
            } else {
                System.out.println(v.distance);
            }
        }

        /*for (Vertex v : adj) {

        }*/
        return 0;
    }

    boolean Relax(Vertex v, Vertex[] adj, ArrayList<Vertex> holder) {

        boolean check = false;
        for (Edge edge : v.edges) {
            //System.out.println("Relaxing " + adj[edge.end].num);
            if (!v.leave) {
                if (adj[(int) edge.end].distance > v.distance + edge.weight) {
                    //System.out.println("inside");
                    adj[(int) edge.end].distance = v.distance + edge.weight;
                    adj[(int) edge.end].parent = v.num;
                    check = true;
                    holder.add(adj[(int) edge.end]);
                    //holder.add(v);
                }
            }

        }
        return check;
    }

    public static void main(String[] args) {
        ShortestPaths n = new ShortestPaths();
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
        int start = scanner.nextInt() - 1;
        negativeCycle(adj, start);
    }

    class Vertex {
        long num;
        long distance = 1000000000;
        long parent = -1;
        ArrayList<Edge> edges = new ArrayList<Edge>();
        boolean leave = false;
        boolean finalcheck = false;
        boolean inHolder = false;
        boolean afterCycle = false;

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

