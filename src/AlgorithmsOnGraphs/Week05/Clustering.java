package AlgorithmsOnGraphs.Week05;

/**
 * Created by Thiloshon on 11-Jan-17.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Clustering {
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
            //System.out.println(b1.difference + " " + b2.difference);
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

    public static void main(String[] args) {
        Clustering c = new Clustering();
        c.run();
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] x = new Point[n];

        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            x[i] = new Point(x1, x2, new UniqueIdentifier(i));
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, k));
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


