package AlgorithmsOnGraphs.Week03;

/**
 * Created by Thiloshon on 10-Dec-16.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(Vertex[] adj) {
        //write your code here

        boolean answer = false;

        for (Vertex v : adj) {
            if (v.distance == -1) {
                v.distance = 0;

                Queue<Vertex> queue = new LinkedList<Vertex>();

                queue.add(v);

                while (queue.size() > 0) {
                    Vertex temp = queue.remove();
                    for (int num : temp.array) {
                        if (adj[num].distance == -1) {
                            queue.add(adj[num]);
                            adj[num].distance = temp.distance + 1;
                        } else if (adj[num].distance == temp.distance) {
                            answer = true;
                        }
                    }
                }
            }
        }

        if (answer){
            return 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
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
        System.out.println(bipartite(adj));
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

