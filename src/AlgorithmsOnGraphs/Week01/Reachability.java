package AlgorithmsOnGraphs.Week01;

/**
 * Created by Thiloshon on 19-Nov-16.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Reachability {
    private static int reach(Vertex[] adj, int x, int y) {
        //write your code here
        Explore(adj[x], adj);

        if(adj[y].reached){
            return 1;
        }
        return 0;
    }

    public static void Explore(Vertex verty, Vertex[] adj) {
        verty.reached = true;
        for (Integer num : verty.array) {
            if (!adj[num].reached) {
                Explore(adj[num], adj);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
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
        System.out.println(reach(adj, x, y));
    }


    static class Vertex {
        int num;
        boolean reached = false;
        ArrayList<Integer> array = new ArrayList<Integer>();

        public Vertex(int num) {
            this.num = num;
        }
    }
}

