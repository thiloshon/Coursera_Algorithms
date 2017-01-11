package AlgorithmsOnGraphs.Week05;

/**
 * Created by Thiloshon on 09-Jan-17.
 */


import java.util.*;

public class ConnectingPointsV2 {
    private double minimumDistance(Point[] points) {
        double result = 0.;
        //write your code here

        points[0].cost = 0;

        ArrayList<Point> queue = new ArrayList<>();
        ArrayList<Point> tree = new ArrayList<>();

        for (Point p : points) {
            queue.add(p);
        }

        Point cont = queue.get(0);

        while (queue.size() > 0) {
            result += cont.cost;
            tree.add(cont);
            queue.remove(cont);

            double temp = Double.MAX_VALUE;
            Point tempy = cont;
            for (Point point : queue) {
                for (Point pointTree : tree) {
                    double a1 = pointTree.x - point.x;
                    double a2 = pointTree.y - point.y;
                    a1 *= a1;
                    a2 *= a2;
                    double root = Math.pow(a1 + a2, 0.5);
                    //System.out.println("root is " + root);
                    double a = point.cost;
                    if (a > root) {
                        point.cost = root;
                    }
                    if (root <= temp) {
                        temp = root;
                        tempy = point;
                    }
                }
            }
            cont = tempy;
        }

        return result;
    }


    public static void main(String[] args) {
        ConnectingPointsV2 c = new ConnectingPointsV2();
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
        double cost = Double.MAX_VALUE;

        public Point(int x, int y, double i) {
            this.x = x;
            this.y = y;
        }
    }
}



         /*Comparator comp = new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.cost > b2.cost)
                    return 1;
                return -1;
            }
        };*/

        /*Queue<Point> priorityQueue = new PriorityQueue(comp);

        for (Point p : points) {
            priorityQueue.add(p);
        }


        while (priorityQueue.size() > 0) {
            Point v = priorityQueue.poll();
            System.out.println("Removing (" + v.x + " " + v.y + ") " + v.cost);

            result += v.cost;

            Iterator iterator = priorityQueue.iterator();


            while (iterator.hasNext()) {
                Point point = (Point) iterator.next();
                double a1 = v.x - point.x;
                double a2 = v.y - point.y;
                a1 *= a1;
                a2 *= a2;
                double root = Math.pow(a1 + a2, 0.5);
                //System.out.println("root is " + root);
                double a = point.cost;
                if (a > root) {
                    Point tem = new Point(point.x, point.y, root);
                    tem.cost = root;
                    priorityQueue.add(tem);
                    priorityQueue.remove(point);
                    //point.cost = root;

                    System.out.println("Setting min as (" + point.x + " " + point.y + " )" + point.cost);
                }
            }
        }*/


