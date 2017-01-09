package AlgorithmsOnGraphs.Week04;

import java.util.*;

/**
 * Created by Thiloshon on 08-Jan-17.
 */
public class ShortestPathsStressTest2 {

    private String negativeCycle(Vertex[] adj, int start) {
        // write your code here
        adj[start].distance = 0;

        for (int x = 0; x < adj.length; x++) {
            for (Vertex v : adj) {
                Relax(v, adj, null);
            }
        }
        boolean check = false;
        ArrayList<Vertex> holder = new ArrayList<>();

        for (Vertex v : adj) {
            Relax(v, adj, holder);
        }

        if (holder.size() > 0) {
            // add to queue and breadth fisrt search and all thses are infinite arbitrage

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


        String ans1 = "";


        for (Vertex v : adj) {
            //System.out.print(v.distance + " ");
            if (v.num == start) {
                ans1 += "0 ";
                continue;
            }
            if (v.distance == Long.MAX_VALUE) {
                ans1 += "* ";
            } else if (v.inHolder) {
                ans1 += "- ";
            } else {
                ans1 += v.distance + " ";
            }
        }

        return ans1;
    }


    boolean Relax(Vertex v, Vertex[] adj, ArrayList<Vertex> holder) {

        boolean check = false;
        for (Edge edge : v.edges) {
            //System.out.println("Relaxing " + adj[edge.end].num);
            if (!v.leave && v.distance != Long.MAX_VALUE) {
                if (adj[(int) edge.end].distance > v.distance + edge.weight) {
                    //System.out.println("inside");
                    adj[(int) edge.end].distance = v.distance + edge.weight;
                    adj[(int) edge.end].parent = v.num;
                    check = true;
                    if (holder != null) {
                        holder.add(adj[(int) edge.end]);
                        System.out.println("Adding" + adj[(int) edge.end].num);
                        //holder.add(v);
                    }

                }
            }

        }
        return check;
    }


    public static void main(String[] args) {
        ShortestPathsStressTest2 n = new ShortestPathsStressTest2();
        n.run();
    }

    void run() {
        while (true) {
            int n = (int) (Math.random() * 10);
            int m = (int) (Math.random() * 10);
            if (n < 5) {
                continue;
            }
            if (m < n / 2) {
                continue;
            }
            int start = (int) (Math.random() * 10);

            if (start >= n) {
                continue;
            }
            ArrayList<Integer>[] adj2 = (ArrayList<Integer>[]) new ArrayList[n];
            ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj2[i] = new ArrayList<Integer>();
                cost[i] = new ArrayList<Integer>();
            }


            long distance[] = new long[n];
            int reachable[] = new int[n];
            int shortest[] = new int[n];
            for (int i = 0; i < n; i++) {
                distance[i] = Long.MAX_VALUE;
                reachable[i] = 0;
                shortest[i] = 1;
            }

            Vertex[] adj = (Vertex[]) new Vertex[n];
            //ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adj[i] = new Vertex(i);
            }
            System.out.println(n + " " + m);
            for (int i = 0; i < m; i++) {
                int x, y, w;
                x = (int) (Math.random() * 100);
                y = (int) (Math.random() * 100);
                if (x > n || y > n || x == 0 || y == 0) {
                    i--;
                    continue;
                }

                w = (int) (Math.random() * 100);
                int msd = (int) (Math.random() * 10);
                if (msd % 2 == 0) {
                    w *= -1;
                }

                System.out.println(x + " " + y + " " + w + "");


                adj2[x - 1].add(y - 1);
                cost[x - 1].add(w);


                adj[x - 1].edges.add(new Edge(x - 1, y - 1, w));
            }


            shortestPaths(adj2, cost, start, distance, reachable, shortest);

            String ans2 = "";
            for (int i = 0; i < n; i++) {
                if (reachable[i] == 0) {
                    ans2 += "* ";
                } else if (shortest[i] == 0) {
                    ans2 += "- ";
                } else {
                    ans2 += distance[i] + " ";
                }
            }


            String answer1 = negativeCycle(adj, start);

            System.out.println("Stack Rewind");

            System.out.println(ans2);
            System.out.println(answer1);

            if (ans2.equals(answer1)) {
                System.out.println("All Fine");
                System.out.println("");

            } else {
                System.out.println("Bump");
                break;

            }

        }

    }

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        //write your code here

        // using max long as inf
        // edge case: reaching or exceeding max long will cause node to be treated as inf

        Set<Integer> visited = new HashSet<>(); //global visited for dfs/bfs short circuiting
        Stack<Integer> stack = new Stack<>(); //global stack since each dfs will exit with empty stack anyway

        distance[s] = 0;

        for (int i = 0; i < adj.length - 1; i++) { //v - 1 iteration
            for (int u = 0; u < adj.length; u++) {
                List<Integer> adjList = adj[u];
                List<Integer> costList = cost[u];
                for (int j = 0; j < adjList.size(); j++) {
                    int v = adjList.get(j);
                    int w = costList.get(j);
                    long dist = distance[v];
                    long newDist = distance[u];

                    if (newDist == Long.MAX_VALUE) continue;

                    try {
                        newDist = Math.addExact(newDist, w);
                    } catch (ArithmeticException e) {
                        newDist = w > 0 ? Long.MAX_VALUE : Long.MIN_VALUE;
                    }

                    if (dist > newDist) distance[v] = newDist; //relax
                }
            }
        }

        // negative cycle iteration, refactor?
        for (int u = 0; u < adj.length; u++) {
            List<Integer> adjList = adj[u];
            List<Integer> costList = cost[u];
            for (int j = 0; j < adjList.size(); j++) {
                // relaxation
                int v = adjList.get(j);
                int w = costList.get(j);
                long dist = distance[v];
                long newDist = distance[u];

                if (newDist == Long.MAX_VALUE) continue;

                try {
                    newDist = Math.addExact(newDist, w);
                } catch (ArithmeticException e) {
                    newDist = w > 0 ? Long.MAX_VALUE : Long.MIN_VALUE;
                }

                // not relaxable || v in visited neg cycle visitable path
                if (dist <= newDist || visited.contains(v)) continue;

                //else, dfs (or bfs)
                stack.push(v);
                while (!stack.isEmpty()) {
                    int n = stack.pop();

                    visited.add(n);
                    shortest[n] = 0;

                    for (int m : adj[n])
                        if (!visited.contains(m)) stack.push(m);
                }
            }
        }

        for (int i = 0; i < distance.length; i++)
            if (distance[i] < Long.MAX_VALUE) reachable[i] = 1;
    }


    class Vertex {
        long num;
        long distance = Long.MAX_VALUE;
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
