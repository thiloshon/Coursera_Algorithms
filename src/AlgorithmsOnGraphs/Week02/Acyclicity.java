package AlgorithmsOnGraphs.Week02;

/**
 * Created by Thiloshon on 22-Nov-16.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Acyclicity {
    static public boolean hasCycle = false;


    private static int acyclic(Vertex[] adj) {
        //write your code here
        Stack stack = new Stack();
        for(int x = 0; x<adj.length; x++){
            if (!adj[x].reached){
                Explore(adj[x], adj, stack);
            }
        }

        if(hasCycle){
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        Vertex[] adj = (Vertex[]) new Vertex[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].array.add(y - 1);
        }
        System.out.println(acyclic(adj));
    }


    public static void Explore(Vertex verty, Vertex[] adj, Stack stack) {

        if (verty.array.size() > 0) {
            verty.inStack = true;
            stack.add(verty);
            for (Integer num : verty.array) {
                if (!adj[num].reached) {
                    if (!adj[num].inStack) {
                        Explore(adj[num], adj, stack);
                    } else {
                        hasCycle = true;

                    }

                }
            }
            verty.inStack=false;
            verty.reached=true;

            return;

        } else {
            verty.reached = true;
            verty.inStack=false;
            //Explore((Vertex) stack.pop(), adj, stack);
            return;
        }
    }

    static class Vertex {
        int num;
        boolean reached = false;
        boolean inStack = false;
        ArrayList<Integer> array = new ArrayList<Integer>();

        public Vertex(int num) {
            this.num = num;
        }
    }
}

