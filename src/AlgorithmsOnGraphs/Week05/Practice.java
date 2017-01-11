package AlgorithmsOnGraphs.Week05;

import java.util.Scanner;

/**
 * Created by Thiloshon on 11-Jan-17.
 */
public class Practice {

    public static void main(String[] args) {

        Practice p=new Practice();
        p.run();






    }


    void run(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Point[] x = new Point[n];
        //int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            int x1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            x[i] = new Point(x1, x2, i);
        }

        for (int i = 0; i < x.length; i++) {
            double a1 = x[i].x * x[i].x;
            double a2 = x[i].y * x[i].y;
            System.out.println(Math.pow(a1 + a2, 0.5)); ;
        }
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
