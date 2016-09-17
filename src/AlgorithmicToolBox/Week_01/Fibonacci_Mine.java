package AlgorithmicToolBox.Week_01;/**
 * Created by Thiloshon on 03-May-16.
 * <p>
 * Problem: Small Fibonacci Number
 */

import java.util.Scanner;

public class Fibonacci_Mine {

    public static void main(String[] args) {


        long x;
        long y = 1;
        long z = 0;
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        long yt = 0;

        for (x = 0; x < n; x++) {
            yt = y + z;
            long temp = y;
            y = z;
            z = temp + y;

        }
        System.out.println(yt);
    }
}
