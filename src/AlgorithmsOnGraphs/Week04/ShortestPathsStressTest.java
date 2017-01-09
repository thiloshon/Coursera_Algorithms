package AlgorithmsOnGraphs.Week04;

import java.util.*;

/**
 * Created by Thiloshon on 08-Jan-17.
 */
public class ShortestPathsStressTest {

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
        ShortestPathsStressTest n = new ShortestPathsStressTest();
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

                //double w2 = -Math.log(w);
                //adj[x - 1].array.add(y - 1);
                adj2[x - 1].add(y - 1);
                cost[x - 1].add(w);

                adj[x - 1].edges.add(new Edge(x - 1, y - 1, w));
            }

            long distance[] = new long[n];
            int reachable[] = new int[n];
            int shortest[] = new int[n];
            for (int i = 0; i < n; i++) {
                distance[i] = Long.MAX_VALUE;  // BZ: default Single Source.
                reachable[i] = 0;
                shortest[i] = 1;
            }
            shortestPaths(adj2, cost, start, distance, reachable, shortest);
            String ans2 = "";
            for (int i = 0; i < n; i++) {
                if (reachable[i] == 0) {
                    ans2 += "* ";
                } else if (shortest[i] == 0) {
                    ans2 += "- ";
                } else {
                    //System.out.println(distance[i]);
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

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,
                                      int s, long[] distance, int[] reachable, int[] shortest) {
        int n = adj.length;
        // BZ: how to start by S? rotation from s? set initial distance only.
        distance[s] = 0;
        // Run Bellman-Ford with V-1 relax().
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < n; u++)
                relax(u, adj[u], cost[u], distance);
        }
        // for (int i = 0; i < n; i++) System.out.print((i + 1) + ": " + distance[i] + "\t");
        //System.out.println();
        // Run V-th iteration to relax all edges.
        // Record into queue all vertices that change distance.
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            relax(u, adj[u], cost[u], distance, queue);
        }
        //System.out.println(queue);
        // BFS from the queue to mark no shortest path.
        Set<Integer> negativeCycle = bfs(queue, adj);
        //System.out.println(negativeCycle);
        for (int v : queue) distance[v] = -1;
        for (int v = 0; v < n; v++) {
            // If remaining vertex has +inf distance, unreachable case.
            if (distance[v] == Long.MAX_VALUE) continue;
            reachable[v] = 1;  // Else all other nodes are reachable.
            // BZ: shortest[S] is 0? will print S as '-'.
            // Mark all vertices in negative cycle as no shortest paths.
            if (v == s) shortest[v] = 1;
            else shortest[v] = negativeCycle.contains(v) ? 0 : 1;
        }
        // BZ: has shortest path but path length is negative?
        distance[s] = 0;
    }

    private static void relax(int u, List<Integer> adjU, List<Integer> costU,
                              long[] distance) {
        /**Relax only, without recording changed dist[v].*/
        relax(u, adjU, costU, distance, null);
    }

    private static void relax(int u, List<Integer> adjU, List<Integer> costU,
                              long[] distance, Queue<Integer> queue) {
        /**Relax on each adjacent edge from u; enqueue all vertices with changed distance.*/
        for (int i = 0; i < adjU.size(); i++) {
            int v = adjU.get(i), c = costU.get(i);
            // BZ: avoid +inf + c overflow.
            if (distance[u] != Long.MAX_VALUE && distance[v] > distance[u] + c) {
                distance[v] = distance[u] + c;
                if (queue != null) queue.offer(v);
            }
        }
    }

    private static Set<Integer> bfs(Queue<Integer> queue, List<Integer>[] adj) {
        /**BFS to explore all vertices reachable from existing queue.*/
        Set<Integer> tmp = new HashSet<>();
        boolean[] visited = new boolean[adj.length];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            tmp.add(u);
            visited[u] = true;
            for (int v : adj[u]) {
                if (!visited[v]) {
                    queue.offer(v);
                }
            }
        }
        return tmp;
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
