package AlgorithmsOnGraphs.Week04;

/**
 * Created by Thiloshon on 09-Jan-17.
 * code from github. not mine
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShortestPaths3 {

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

    // default main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj2 = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
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
            distance[i] = Long.MAX_VALUE;
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
    }

    // dev main
//    public static void main (String[] args) {
//        try(Stream<String> stream = Files.lines(Paths.get("res/shortest-paths-input-4.txt"))) {
//            List<List<Integer>> list = stream
//                    .map(s -> Arrays.stream(s.split(" "))
//                            .map(Integer::parseInt)
//                            .collect(Collectors.toList())).collect(Collectors.toList()
//                    );
//
//            List<Integer> last = list.remove(list.size() - 1);
//            List<Integer> first = list.remove(0);
//            int n = first.get(0), m = first.get(1), s = last.get(0) - 1;
//
//            List<List<Integer>> adj = IntStream.range(0, n)
//                    .mapToObj(i -> new ArrayList<Integer>())
//                    .collect(Collectors.toList());
//
//            List<List<Integer>> cost = IntStream.range(0, n)
//                    .mapToObj(i -> new ArrayList<Integer>())
//                    .collect(Collectors.toList());
//
//            list.forEach(p -> {
//                int x = p.get(0) - 1;
//                adj.get(x).add(p.get(1) - 1);
//                cost.get(x).add(p.get(2));
//            });
//
//            long distance[] = new long[n];
//            int reachable[] = new int[n];
//            int shortest[] = new int[n];
//            for (int i = 0; i < n; i++) {
//                distance[i] = Long.MAX_VALUE;
//                reachable[i] = 0;
//                shortest[i] = 1;
//            }
//
//            shortestPaths(
//                    adj.toArray(new ArrayList[adj.size()]),
//                    cost.toArray(new ArrayList[cost.size()]),
//                    s, distance, reachable, shortest
//            );
//
//            for (int i = 0; i < n; i++) {
//                if (reachable[i] == 0) {
//                    System.out.println('*');
//                } else if (shortest[i] == 0) {
//                    System.out.println('-');
//                } else {
//                    System.out.println(distance[i]);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}