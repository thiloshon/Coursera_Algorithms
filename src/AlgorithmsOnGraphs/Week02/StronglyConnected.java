package AlgorithmsOnGraphs.Week02;

/**
 * Created by Thiloshon on 09-Dec-16.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StronglyConnected {
    static int clock = 1;

    private static int numberOfStronglyConnectedComponents(Vertex[] adj, Vertex[] adjReverse) {
        //write your code here

        /*System.out.println("______________________________");
        for(Vertex v: adj){
            System.out.println(v.num);
            for (int n : v.array){
                System.out.print(n+ " ");
            }
            System.out.println("");
        }
        System.out.println("______________________________");
        for(Vertex v: adjReverse){
            System.out.println(v.num);
            for (int n : v.array){
                System.out.print(n+ " ");
            }
            System.out.println("");
        }
        System.out.println("______________________________");*/

        int ans = 0;
        ArrayList<Vertex> finalList = new ArrayList<>();

        for (int x = 0; x < adj.length; x++) {
            if (!adjReverse[x].reached) {
                Explore(adjReverse[x], adjReverse, finalList);
            }
        }

        for (int x = 0; x < adj.length; x++) {
            adj[x].postOrder = adjReverse[x].postOrder;
        }


        /*System.out.println("______________________________");
        for(Vertex v: adj){
            System.out.print(v.num);

            System.out.println(" "+ v.postOrder);
        }
        System.out.println("______________________________");

        for(Vertex v: finalList){
            System.out.print(v.num);

            System.out.println(" "+ v.postOrder);
        }
        System.out.println("______________________________");*/


        for (int x = finalList.size() - 1; x >= 0; x--) {

            int num = finalList.get(x).num;

            if (!adj[num].scc) {
                //System.out.println("x is " + adj[num].postOrder);
                Explore2(num, adj, ans);
                ans++;
            } else {
                //ans++;
            }
        }

       /* for (Vertex v : adj) {
            System.out.print(v.postOrder + " " + v.sccNum + "     ");
        }
        System.out.println(" ");*/

        return ans;
    }


    public static void Explore(Vertex verty, Vertex[] adj, ArrayList<Vertex> finalList) {
        verty.reached = true;
        verty.preOrder = clock;
        clock++;
        for (Integer num : verty.array) {
            if (!adj[num].reached) {
                Explore(adj[num], adj, finalList);
            }
        }
        verty.postOrder = clock;
        finalList.add(verty);
        clock++;
    }

    public static void Explore2(int number, Vertex[] adj, int numer) {
        adj[number].scc = true;
        adj[number].sccNum = numer;
        for (Integer num : adj[number].array) {

            /*System.out.println("exploring " + adj[num].postOrder + "  " + num + " and values are");
            for (int m : adj[num].array) {
                System.out.println(" " + m);
            }*/
            if (!adj[num].scc) {
                Explore2(num, adj, numer);
            }
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        //ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        Vertex[] adj = (Vertex[]) new Vertex[n];
        Vertex[] adjReverse = (Vertex[]) new Vertex[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new Vertex(i);
        }
        for (int i = 0; i < n; i++) {
            adjReverse[i] = new Vertex(i);
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].array.add(y - 1);
            adjReverse[y - 1].array.add(x - 1); // reverse graph
        }
        System.out.println(numberOfStronglyConnectedComponents(adj, adjReverse));
    }

    static class Vertex {
        int num;
        int preOrder;
        int postOrder;
        boolean reached = false;
        boolean scc = false;
        int sccNum;
        ArrayList<Integer> array = new ArrayList<Integer>();

        public Vertex(int num) {
            this.num = num;
        }
    }
}

