package AlgorithmsOnGraphs.Week04;

/**
 * Created by Thiloshon on 08-Jan-17.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ShortestPaths2 {


    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost,
                                      int s, long[] distance, int[] reachable, int[] shortest) {
        int n = adj.length;
        distance[s] = 0;
        for (int i = 0; i < n; i++) {
            for (int u = 0; u < n; u++)
                relax(u, adj[u], cost[u], distance);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int u = 0; u < n; u++) {
            relax(u, adj[u], cost[u], distance, queue);
        }
        Set<Integer> negativeCycle = bfs(queue, adj);
        for (int v : queue) distance[v] = -1;
        for (int v = 0; v < n; v++) {
            if (distance[v] == Long.MAX_VALUE) continue;
            reachable[v] = 1;  // Else all other nodes are reachable.
            if (v == s) shortest[v] = 1;
            else shortest[v] = negativeCycle.contains(v) ? 0 : 1;
        }
        distance[s] = 0;
    }

    private static void relax(int u, List<Integer> adjU, List<Integer> costU,
                              long[] distance) {
        relax(u, adjU, costU, distance, null);
    }

    private static void relax(int u, List<Integer> adjU, List<Integer> costU,
                              long[] distance, Queue<Integer> queue) {
        for (int i = 0; i < adjU.size(); i++) {
            int v = adjU.get(i), c = costU.get(i);
            if (distance[u] != Long.MAX_VALUE && distance[v] > distance[u] + c) {
                distance[v] = distance[u] + c;
                if (queue != null) queue.offer(v);
            }
        }
    }

    private static Set<Integer> bfs(Queue<Integer> queue, List<Integer>[] adj) {
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj2 = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj2[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj2[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;  // BZ: default Single Source.
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj2, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
        scanner.close();
    }
}