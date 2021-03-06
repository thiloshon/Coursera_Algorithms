package AlgorithmicToolBox.Week_01;

/**
 * Created by Thiloshon on 27-Aug-16.
 */
import java.math.BigInteger;
import java.util.*;

public class FibonacciSum {
    private static long getFibonacciSum(long n) {
        long total=0;

        n= n%60;

        for (int x=0; x<n; x++){

            long no = getFibonacciHuge(x, 10);

            total=(total+no)%10;
            //System.out.println(x + " " + no + " " + total);
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long c = getFibonacciSum(n+1);
        System.out.println(c);
    }




    private static long getFibonacciHuge(long n, long m) {
        long pisano = pisanoNumber(m);
        long temp = n % pisano;
        long fib = fibonochiNumber(temp);
        return fib%m;
    }


    private static long pisanoNumber(long modulo) {
        int count = 0;
        boolean condition01 = false, condition02 = false, condition03 = false;

        //long x;
        long y = 1;
        long z = 0;

        while (true) {

            //(a + b) mod n = [(a mod n) + (b mod n)] mod n
            //(y + z) % modulo = [(y % modulo) + (z % modulo)] % modulo

            y = y % modulo;
            z = z % modulo;
            long number = (y + z) % modulo;

            long temp = y;
            y = z;
            z = temp + y;

            //System.out.println(number);

            if (!(condition01 && condition02 && condition03) && number == 0 && count > 2) {
                condition01 = true;
            } else if (condition01 && !(condition02) && !(condition03) && number == 1) {
                condition02 = true;
            } else if (condition01 && condition02 && (!(condition03)) && number == 1) {
                condition03 = true;
                return count - 1;
            } else {
                condition01 = false;
                condition02 = false;
                condition03 = false;
            }
            count++;
        }
        //return 0;
    }

    private static long fibonochiNumber(long no) {
        long x;
        long y = 1;
        long z = 0;
        long n = no;
        long yt = 0;
        for (x = 0; x < n; x++) {
            yt = y+(z);
            long temp = y;
            y = z;
            z = temp+(y);
        }
        return yt;
    }



}

