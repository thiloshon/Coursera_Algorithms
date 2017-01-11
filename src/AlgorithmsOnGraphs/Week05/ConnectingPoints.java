package AlgorithmsOnGraphs.Week05;

/**
 * Created by Thiloshon on 09-Jan-17.
 */


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(Point[] x) {
        double result = 0.;
        //write your code here
        for (int i = 0; i < x.length; i++) {
            double a1 = x[i].x * x[i].x;
            double a2 = x[i].y * x[i].y;
            x[i].ranking = Math.pow(a1 + a2, 0.5);
        }


        Arrays.sort(x, new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.ranking > b2.ranking)
                    return 1;
                return -1;
            }
        });

        /*for (Point p : x) {
            System.out.println(p.ranking + " " + p.ID);
        }*/

        for (int t = 1; t < x.length; t++) {
            if (x[t - 1].ID != x[t].ID) {
                //System.out.println("comparing " + x[t - 1].x + " " + x[t - 1].y + " and " + x[t].x + " " + x[t].y);

                double ans = Double.MAX_VALUE;
                for (int h = 0; h < t; h++) {
                    double a1 = x[h].x - x[t].x;
                    double a2 = x[h].y - x[t].y;
                    a1 *= a1;
                    a2 *= a2;
                    if (Math.pow(a1 + a2, 0.5) < ans) {
                        ans = Math.pow(a1 + a2, 0.5);
                    }
                }

                /*double a1 = x[t - 1].x - x[t].x;
                double a2 = x[t - 1].y - x[t].y;*/

                /*a1 *= a1;
                a2 *= a2;*/
                //System.out.println(a1 + " " + a2);
                /*ans = Math.pow(a1 + a2, 0.5);*/
                /*if(ans>x[t].ranking){
                    ans=x[t].ranking;
                }*/
                result += ans;
                x[t].ID = x[t - 1].ID;
            }
        }


        return result;
    }


    public static void main(String[] args) {
        ConnectingPoints c = new ConnectingPoints();
        c.run();
    }

    void run() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] x = new Point[n];
        //int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            x[i] = new Point(x1, x2, i);
        }
        System.out.println(minimumDistance(x));
    }

    class Point {
        int x;
        int y;
        double ranking;
        int ID;

        public Point(int x, int y, int i) {
            this.x = x;
            this.y = y;
            this.ID = i;
        }
    }
}

