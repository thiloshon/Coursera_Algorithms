package AlgorithmicToolBox.Week_01;

import java.util.Scanner;

/**
 * Created by Thiloshon on 27-Aug-16.
 */
public class PartialSum {
    private static long getFibonacciPartialSum(long from, long to) {
        long total=0;

        from = from%60;


        //n= n%60;

        for (long x=from; x<=60; x++){

            long no = getFibonacciHuge(x, 10);

            total=(total+no)%10;
            //System.out.println(x + " " + no + " " + total);
        }

        to =to%60;

        for (long x=0; x<=to; x++){

            long no = getFibonacciHuge(x, 10);

            total=(total+no)%10;
            //System.out.println(x + " " + no + " " + total);
        }

        return total;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
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


