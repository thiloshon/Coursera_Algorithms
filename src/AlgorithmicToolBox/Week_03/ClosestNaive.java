package AlgorithmicToolBox.Week_03;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class ClosestNaive {

    static void minimalDistance(Point[] b, Point y[]) {double answ1 = Double.POSITIVE_INFINITY;

        for (int i = 0; i <= b.length-1; i++) {
            for (int j = i + 1; j <= b.length-1; j++) {
                long a1 = (b[i].x - b[j].x) * (b[i].x - b[j].x);
                long a2 = (b[i].y - b[j].y) * (b[i].y - b[j].y);
                double m = Math.pow(a1 + a2, 0.5);

                //System.out.println("min is "+ m);

                if (m < answ1) {
                    answ1 = m;
                    //System.out.println("Ans is " + ans);
                    System.out.println(b[i].x+" " +b[j].x);
                }
            }
        }

        System.out.println(answ1);
    }



    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        /*int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }*/
        Point[] P = new Point[n];
        Point[] X = new Point[n];


        for (int i = 0; i < n; i++) {

            int v = nextInt();
            int c = nextInt();

            P[i] = new Point(v,c);
            X[i] = new Point(v,c);


        }
        minimalDistance(P, X);
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //@Override
        /*public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }*/
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
