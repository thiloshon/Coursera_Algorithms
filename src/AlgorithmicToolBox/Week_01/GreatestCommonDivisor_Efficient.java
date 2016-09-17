package AlgorithmicToolBox.Week_01;

import java.util.Scanner;

/**
 * Created by Thiloshon on 04-May-16.
 * <p>
 * Using Euclidean Algorithm.
 */
public class GreatestCommonDivisor_Efficient {


    public static void main(String args[]) {


        Scanner scanner = new Scanner(System.in);
        long x = scanner.nextLong();

        long y = scanner.nextLong();

        System.out.println(GCD(x, y));
    }

    public static long GCD(long a, long b) {

        long x = a;
        long y = b;
        long great = 0;
        long low = 0;
        long GCD = 1;
        long temp = 1;

        great = y;
        low = x;

        if (x > y) {
            great = x;
            low = y;
        }


        while (low > 0) {
            temp = great % low;
            if (temp == 0) {
                GCD = great;
            }

            great = low;
            low = temp;
            //System.out.println("" + great + " " + low);
        }

        return great;

    }

}
