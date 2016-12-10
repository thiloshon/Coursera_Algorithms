package AlgorithmsOnGraphs.Week01;

/**
 * Created by Thiloshon on 21-Nov-16.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(Vertex[] adj, int num) {
        int result = 0;
        //write your code here
        //int ans = 0;
        for (int c = 0; c < num; c++) {
            if (!adj[c].reached) {
                Explore(adj[c], adj);
                result++;
            }
        }
        return result;
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
        /*int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;*/
        System.out.println(numberOfComponents(adj,  n));
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

