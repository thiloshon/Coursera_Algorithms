package Week_01;/**
 * Created by Thiloshon on 03-May-16.
 * <p>
 * Problem: Small Fibonacci Number
 */

import java.util.Scanner;

public class Fibonacci_Mine {

    public static void main(String[] args) {

        long x = 10;
        long y = 1;
        long z = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for (x = 0; x < n; x++) {
            long yt = y + z;
            //if (x==n-1){
            System.out.println(yt);
            //    }
            long temp = y;
            y = z;
            z = temp + y;

        }


        System.out.println("Hello World!");
    }
}
