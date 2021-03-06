package AlgorithmicToolBox.Week_03;

import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class ClosestStressTest {

    static double minimalDistance(Point[] x, Point y[]) {


        Arrays.sort(x, new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.x > b2.x)
                    return 1;
                return -1;
            }
        });

        /*for( Point p : x){
            System.out.println(p.x+" "+p.y);
        }
        System.out.println("-----------------------------");*/

        Arrays.sort(y, new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.x > b2.x)
                    return 1;
                return -1;
            }
        });



        double ans = Double.POSITIVE_INFINITY;
        ans= recursionFunction(x, y, 0, x.length - 1, ans);

        return ans;

        //write your code here

    }

    static double recursionFunction(Point[] x, Point y[], int left, int right, double ans) {
        /*double ans = Double.POSITIVE_INFINITY;*/

        if (right - left <= 3) {
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    int a1 = (x[i].x - x[j].x) * (x[i].x - x[j].x);
                    int a2 = (x[i].y - x[j].y) * (x[i].y - x[j].y);
                    double m = Math.pow(a1 + a2, 0.5);

                    //System.out.println("min is "+ m);

                    if (m < ans) {
                        ans = m;
                        //System.out.println("Ans is " + ans);
                    }
                }
            }

            return ans;
        }


        int mid = (left + right) / 2;



        Point[] temp = new Point[right + 1];
        Point[] temp2 = new Point[(right + 1)];

        sort(y, temp, left, mid);

       /* System.out.println("-----------------------------");
        for( Point p : y){
            System.out.println(p.x+" "+p.y);
        }
        System.out.println("-----------------------------");*/

        sort(y, temp2, mid + 1, right);

        int tem = x[mid].x;
        //System.out.println(x[mid].x+" "+x[mid].y);

        for(int d = mid+1; x[d].x==tem&&d<=right;d++){
            //System.out.println("im in");
            mid++;
        }


        /*System.out.println("-----------------------------");
        for( Point p : x){
            System.out.println(p.x+" "+p.y);
        }
        System.out.println("-----------------------------");*/



        double ans2 = recursionFunction(x, y, left, mid, ans);
        double ans3 = recursionFunction(x, y, mid + 1, right, ans);

        double ansFromRecursion;

        //System.out.println("ive herererer");

        if (ans2 < ans3) {
            ansFromRecursion = ans2;
            ans = ans2;
        } else {
            ansFromRecursion = ans3;
            ans = ans3;
        }

        //System.out.println("ansFromRecursion is " + ansFromRecursion);

        ArrayList<Point> arr = new ArrayList();

        /*Arrays.sort(y, new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.y > b2.y)
                    return 1;
                return -1;
            }
        });*/

        /*for(Point p : y){
            System.out.println(p.y);
        }
        System.out.println("   hfhf"+mid);*/

        for (int g = left; g <= right; g++) {
            if (y[g].x >= (x[mid].x - ansFromRecursion) && y[g].x <= x[mid].x + ansFromRecursion) {
                arr.add(y[g]);
            }
        }


        /*for(Point p : arr){
            System.out.println(p.x+" "+p.y);
        }*/


        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size() && j < 8; j++) {

                int a1 = (arr.get(i).x - arr.get(j).x) * (arr.get(i).x - arr.get(j).x);
                int a2 = (arr.get(i).y - arr.get(j).y) * (arr.get(i).y - arr.get(j).y);
                //System.out.print(a1 + " " + a2 + " ");
                double m = Math.pow(a1 + a2, 0.5);

                //System.out.println(m);

                if (m < ansFromRecursion) {
                    ansFromRecursion = m;
                    ans = m;
                    //System.out.println("ansFromRecursion is " + ansFromRecursion);
                }
            }
        }

        //System.out.println("im heer too");


        return ans;
    }


    private static long sort(Point[] a, Point[] b, int left, int right) {

       /* System.out.println("Sorting: a");*/
        /*for(int o = 0; o<a.length;o++){
            System.out.println(a[o].x+" "+a[o].y);
        }*/

        /*System.out.println("from " +left+" to "+ right +" and length is "+ b.length);*/
        /*for(int o = 0; o<b.length;o++){
            if(b[o]!=null)
            System.out.println(b[o].x+" "+b[o].y);
        }*/

        long numberOfInversions = 0;
        if (right <= left + 1) {
            if (right - left > 0) {
                if (a[left].y > a[right].y) {
                    numberOfInversions++;
                    Point temp = a[left];
                    a[left] = a[right];
                    a[right] = temp;
                }
            }

            return numberOfInversions;
        }

        int ave = (left + right) / 2;
        //System.out.println("lrft "+ left+" right "+right+" avh " + ave);
        numberOfInversions += sort(a, b, left, ave);
        numberOfInversions += sort(a, b, ave + 1, right);
        int LHS = left;
        int mid = ave + 1;
        //int RHS = right;
        for (int x = left; x <= right; x++) {
            if (ave >= right) {

                for (int z = x; z <= right; z++) {
                    //System.out.println(z);
                    b[z] = a[LHS];
                    LHS++;
                }
                break;
            }
            if (LHS >= mid) {
                for (int z = x; z <= right; z++) {
                    //System.out.println(z);
                    b[z] = a[x];
                    x++;
                }
                break;
            }
            if (a[LHS].y > a[ave + 1].y) {
                numberOfInversions += ave + 1 - x;
                //System.out.println((ave+1)+" had " + a[ave+1] +" gets " + (ave+1-x));
                b[x] = a[ave + 1];
                ave++;
            } else {
                /*System.out.println("Mins ase "+x+ " " + LHS);
                System.out.println(b.length);*/

                b[x] = a[LHS];
                LHS++;
            }
        }
        for (int x = left; x <= right; x++) {
            a[x] = b[x];
        }

        //write your code here
        return numberOfInversions;
    }



    public static void main(String[] args) throws Exception {

        while (true){
            int num = (int)(Math.random()*10);
            //System.out.println("num is " + num);


            Point[] b = new Point[num];

            Point[] P = new Point[num];
            Point[] X = new Point[num];

            for (int i = 0; i < num; i++) {

                int v = (int)(Math.random()*10000);
                int c = (int)(Math.random()*10000);

                P[i] = new Point(v,c);
                X[i] = new Point(v,c);
                b[i] = new Point(v,c);


            }
            double answ = minimalDistance(P, X);

            double answ1 = Double.POSITIVE_INFINITY;

            for (int i = 0; i <= num-1; i++) {
                for (int j = i + 1; j <= num-1; j++) {
                    int a1 = (b[i].x - b[j].x) * (b[i].x - b[j].x);
                    int a2 = (b[i].y - b[j].y) * (b[i].y - b[j].y);
                    double m = Math.pow(a1 + a2, 0.5);

                    //System.out.println("min is "+ m);

                    if (m < answ1) {
                        answ1 = m;
                        //System.out.println("Ans is " + ans);
                    }
                }
            }

            if(answ!=answ1){

                for(Point p : b){
                    System.out.println(p.x+" " + p.y);
                }
                System.out.println(answ+" " +answ1);


                break;
            }else{
                System.out.println("Okay");
            }

            /*boolean check = false;
            for(int s=0; s<num;s++){
                if(test1[s]!=test2[s]){
                    System.out.println("Boom!");
                    for(int d : test){
                        System.out.println(d);
                    }
                    check=true;
                    break;
                }
                System.out.print(".");
            }
            if(check) break;*/
        }

        /*reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        *//*int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }*//*
        Point[] P = new Point[n];
        Point[] X = new Point[n];


        for (int i = 0; i < n; i++) {

            int v = nextInt();
            int c = nextInt();

            P[i] = new Point(v,c);
            X[i] = new Point(v,c);


        }

        writer.close();*/
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


/*static double minimalDistance(Point[] x, Point y[]) {


        Arrays.sort(x, new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.x > b2.x)
                    return 1;
                return -1;
            }
        });

        *//*for( Point p : x){
            System.out.println(p.x+" "+p.y);
        }
        System.out.println("-----------------------------");*//*

        Arrays.sort(y, new Comparator<Point>() {
            public int compare(Point b1, Point b2) {
                if (b1.x > b2.x)
                    return 1;
                return -1;
            }
        });



        double ans = Double.POSITIVE_INFINITY;
        return (recursionFunction(x, y, 0, x.length - 1, ans));


        //write your code here

    }

    static double recursionFunction(Point[] x, Point y[], int left, int right, double ans) {
        *//*double ans = Double.POSITIVE_INFINITY;*//*

        if (right - left <= 3) {
            for (int i = left; i <= right; i++) {
                for (int j = i + 1; j <= right; j++) {
                    int a1 = (x[i].x - x[j].x) * (x[i].x - x[j].x);
                    int a2 = (x[i].y - x[j].y) * (x[i].y - x[j].y);
                    double m = Math.pow(a1 + a2, 0.5);

                    //System.out.println("min is "+ m);

                    if (m < ans) {
                        ans = m;
                        //System.out.println("Ans is " + ans);
                    }
                }
            }

            return ans;
        }


        int mid = (left + right) / 2;

        Point[] temp = new Point[right+1];
        Point[] temp2 = new Point[(right +1)];

        sort(y, temp, left, mid);

       *//* System.out.println("-----------------------------");
        for( Point p : y){
            System.out.println(p.x+" "+p.y);
        }
        System.out.println("-----------------------------");*//*

        sort(y, temp2, mid + 1, right);

        *//*System.out.println("-----------------------------");
        for( Point p : y){
            System.out.println(p.x+" "+p.y);
        }
        System.out.println("-----------------------------");*//*

        double ans2 = recursionFunction(x, y, left, mid, ans);
        double ans3 = recursionFunction(x, y, mid + 1, right, ans);

        double ansFromRecursion;

        //System.out.println("ive herererer");

        if (ans2 < ans3) {
            ansFromRecursion = ans2;
            ans = ans2;
        } else {
            ansFromRecursion = ans3;
            ans=ans3;
        }

        //System.out.println("ansFromRecursion is " + ansFromRecursion);

        ArrayList<Point> arr = new ArrayList();

        for (int g = left; g < right; g++) {
            if (y[g].x >= (mid - ansFromRecursion) && y[g].x <= mid + ansFromRecursion) {
                arr.add(y[g]);
            }
        }


        for (int i = 0; i <arr.size(); i++) {
            for (int j = i + 1; j <arr.size()&&j<8; j++) {
                int a1 = (x[i].x - x[j].x) * (x[i].x - x[j].x);
                int a2 = (x[i].y - x[j].y) * (x[i].y - x[j].y);
                double m = Math.pow(a1 + a2, 0.5);

                if (m < ansFromRecursion) {
                    ansFromRecursion = m;
                    ans=m;
                    //System.out.println("ansFromRecursion is " + ansFromRecursion);
                }
            }
        }

        //System.out.println("im heer too");


        return ans;
    }


    private static long sort(Point[] a, Point[] b, int left, int right) {

       *//* System.out.println("Sorting: a");*//*
        *//*for(int o = 0; o<a.length;o++){
            System.out.println(a[o].x+" "+a[o].y);
        }*//*

        *//*System.out.println("from " +left+" to "+ right +" and length is "+ b.length);*//*
        *//*for(int o = 0; o<b.length;o++){
            if(b[o]!=null)
            System.out.println(b[o].x+" "+b[o].y);
        }*//*

        long numberOfInversions = 0;
        if (right <= left + 1) {
            if (right - left > 0) {
                if (a[left].y > a[right].y) {
                    numberOfInversions++;
                    Point temp = a[left];
                    a[left] = a[right];
                    a[right] = temp;
                }
            }

            return numberOfInversions;
        }

        int ave = (left + right) / 2;
        //System.out.println("lrft "+ left+" right "+right+" avh " + ave);
        numberOfInversions += sort(a, b, left, ave);
        numberOfInversions += sort(a, b, ave + 1, right);
        int LHS = left;
        int mid = ave + 1;
        //int RHS = right;
        for (int x = left; x <= right; x++) {
            if (ave >= right) {

                for (int z = x; z <= right; z++) {
                    //System.out.println(z);
                    b[z] = a[LHS];
                    LHS++;
                }
                break;
            }
            if (LHS >= mid) {
                for (int z = x; z <= right; z++) {
                    //System.out.println(z);
                    b[z] = a[x];
                    x++;
                }
                break;
            }
            if (a[LHS].y > a[ave + 1].y) {
                numberOfInversions += ave + 1 - x;
                //System.out.println((ave+1)+" had " + a[ave+1] +" gets " + (ave+1-x));
                b[x] = a[ave + 1];
                ave++;
            } else {
                *//*System.out.println("Mins ase "+x+ " " + LHS);
                System.out.println(b.length);*//*

                b[x] = a[LHS];
                LHS++;
            }
        }
        for (int x = left; x <= right; x++) {
            a[x] = b[x];
        }

        //write your code here
        return numberOfInversions;
    }*/