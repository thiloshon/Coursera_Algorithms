package AlgorithmsOnGraphs.Week05;

/**
 * Created by Thiloshon on 11-Jan-17.
 */

import java.util.*;

public class ClusteringStressTest {
    private double clustering(Point[] x, int k) {

       /* for (Point p : x) {
            System.out.println((p.x + " " + p.y + ") , " + " " + p.UID.UID + " "));
        }
        System.out.println();*/

        ArrayList<Difference> differences = new ArrayList();

        for (int g = 0; g < x.length; g++) {
            for (int h = 0; h < x.length; h++) {
                if (g == h) continue;

                double a1 = x[g].x - x[h].x;
                double a2 = x[g].y - x[h].y;
                a1 *= a1;
                a2 *= a2;
                double root = Math.pow(a1 + a2, 0.5);
                differences.add(new Difference(x[g], x[h], root));
            }
        }

        Comparator comp = (Comparator<Difference>) (b1, b2) -> {
            System.out.println(b1.difference + " " + b2.difference);
            if (b1.difference > b2.difference)
                return 1;
            else if(b1.difference < b2.difference)
                return -1;
            return 0;
        };

        differences.sort(comp);

       /* for (Difference d : differences) {
            System.out.println("(" + d.p1.x + " " + d.p1.y + ") , (" + d.p2.x + " " + d.p2.y + ") " + d.difference);

        }*/

        int noOfDisjointSets = x.length;
        double d = 0;

        while (noOfDisjointSets >= k) {
            //System.out.println(noOfDisjointSets);

            Difference temp = differences.get(0);
            differences.remove(0);


            //System.out.println("checking (" + temp.p1.x + " " + temp.p1.y + ") , (" + temp.p2.x + " " + temp.p2.y + ") " + temp.difference + " " + temp.p1.UID.UID + " " + temp.p2.UID.UID);


            if (temp.p1.UID.UID != temp.p2.UID.UID) {
                int num = temp.p1.UID.UID;
                for (Point po : x) {
                    //System.out.println(po.UID.UID);
                    if (po.UID.UID == num) {
                        po.UID.UID = temp.p2.UID.UID;
                        //System.out.println("And now " + po.UID.UID);
                    }
                }
                noOfDisjointSets--;


                d = temp.difference;

                //System.out.println("setting ");
                /*temp.p2.parent.parent = temp.p1;
                temp.p1.lastChild = temp.p2.lastChild;

                Point temppy = temp.p2.lastChild;
                while (temppy.parent != temppy) {
                    System.out.println("Setting id of "+ temppy.UID.UID+ " as "+ temp.p1.UID.UID);
                    temppy.UID.UID = temp.p1.UID.UID;


                    temppy = temppy.parent;
                }*/
            }


        }

        //write your code here

        /*for (Point p : x) {
            System.out.println((p.x + " " + p.y + ") , " + " " + p.UID.UID + " "));
        }
        System.out.println(k);*/
        return d;
    }

    private static double clustering(int[] x, int[] y, int k) {
        int n = x.length;
        DisjointSets sets = new DisjointSets(n);
        for (int i = 0; i < n; i++) sets.makeSet(i);
        // Generate all edges and offer into PQ.
        Comparator<Edge> cmp = new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return e1.w < e2.w ? -1 : 1;
            }
        };
        PriorityQueue<Edge> pq = new PriorityQueue<>(cmp);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pq.offer(new Edge(i, j, distance(x[i], y[i], x[j], y[j])));
            }
        }
        // Collections.reverseOrder(cmp).
        // BZ: initial capacity, instead of fixed capacity.
        PriorityQueue<Edge> mst = new PriorityQueue<>(k - 1, Collections.reverseOrder(cmp));
        while (!pq.isEmpty()) {
            Edge lightest = pq.poll();
            // System.out.println(lightest.toString());
            if (sets.find(lightest.u) != sets.find(lightest.v)) {
                sets.union(lightest.u, lightest.v);
                mst.offer(lightest);
            }
        }
        // BZ: How to find the (K-1)th largest edge?
        //for (Edge e : mst) System.out.println(e.toString());
        for (int i = 1; i <= k - 2; i++) mst.poll();
        return mst.peek().w;
    }

    private static class DisjointSets {
        // BZ: Sets instead of Set; store all parents.
        int[] parents;
        // BZ: Heuristic method to compress layers.
        int[] ranks;

        public DisjointSets(int n) {
            parents = new int[n];
            ranks = new int[n];
        }

        public void makeSet(int i) {
            parents[i] = i;
            ranks[i] = 1;
        }

        public int find(int i) {
            /**Find root of given node and compress tree on the path.*/
            // BZ: base case of root itself.
            if (parents[i] == i) return i;
            parents[i] = find(parents[i]);
            return parents[i];
        }

        public void union(int i, int j) {
            /**Link the "shorter" tree under the "taller" one.*/
            int r1 = find(i), r2 = find(j);
            // BZ: If same root then no need to union.
            if (r1 == r2) return;
            if (ranks[r1] < ranks[r2]) {
                parents[r1] = r2;
                // BZ: same rank for taller root.
            } else if (ranks[r2] < ranks[r1]) {
                parents[r2] = r1;
            } else {
                parents[r2] = r1;
                ranks[r1] += 1;
            }
        }
    }

    private static class Edge {
        int u, v;
        double w;

        public Edge(int u, int v, double w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        public String toString() {
            return u + "\t" + v + "\t" + w;
        }
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        /**Compute distance between points a and b.*/
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {
        ClusteringStressTest c = new ClusteringStressTest();
        c.run();
    }

    void run() {

        while (true) {

            Scanner scanner = new Scanner(System.in);

            int n = (int) (Math.random() * 1000);

            System.out.println("n is " + n);
            if (n < 4) continue;

            Point[] x = new Point[n];
            int[] x3 = new int[n];
            int[] y = new int[n];


            for (int i = 0; i < n; i++) {
                int x1 = (int) (Math.random() * 1000);
                int x2 = (int) (Math.random() * 1000);

                if (x1 > n || x2 > n) {
                    i--;
                    continue;
                }
                x[i] = new Point(x1, x2, new UniqueIdentifier(i));
                x3[i] = x1;
                y[i] = x2;
            }
            int k = (int) (Math.random() * 1000);
            if (k > n||k<2) continue;

            System.out.println(k);

            for (Point p : x) {
                System.out.println(p.x + " " + p.y);
            }

            if (clustering(x, k) != clustering(x3, y, k)) {
                System.out.println("boom");
                System.out.println(clustering(x, k));
                System.out.println(clustering(x3, y, k));



                break;

            } else {
                System.out.println("Okay");
            }


        }


    }

    class Point {
        int x;
        int y;
        UniqueIdentifier UID;

        public Point(int x, int y, UniqueIdentifier i) {
            this.x = x;
            this.y = y;
            this.UID = i;
        }
    }

    class UniqueIdentifier {
        int UID;

        public UniqueIdentifier(int UID) {
            this.UID = UID;
        }
    }

    class Difference {
        Point p1;
        Point p2;
        double difference;

        public Difference(Point p1, Point p2, double difference) {
            this.p1 = p1;
            this.p2 = p2;
            this.difference = difference;
        }
    }
}


