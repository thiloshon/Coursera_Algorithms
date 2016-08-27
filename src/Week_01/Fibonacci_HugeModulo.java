/**
 * Created by Thiloshon on 03-May-16.
 * Huge Fibonacci_Mine Number modulo m
 *
 * One Heck of a Question... Really hard...
 *
 * References:
 * https://en.wikipedia.org/wiki/Modulo_operation
 * (a + b) mod n = [(a mod n) + (b mod n)] mod n // This is a killer equation.
 * https://www.coursera.org/learn/algorithmic-toolbox/discussions/weeks/2/threads/qSIrrdmDEeWQzA7MOZsNDQ
 * https://www.coursera.org/learn/algorithmic-toolbox/discussions/weeks/2/threads/7eKhCEHZEeaPHQrkCWo3rw
 *
 *
 */

package Week_01;

import java.math.BigInteger;
import java.util.*;

public class Fibonacci_HugeModulo {


    private static BigInteger getFibonacciHuge(long n, long m) {
        long pisano = pisanoNumber(m);
        long temp = n % pisano;
        BigInteger fib = fibonochiNumber(temp);
        return fib.divideAndRemainder(BigInteger.valueOf(m))[1];


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

    private static BigInteger fibonochiNumber(long no) {
        long x;
        BigInteger y = BigInteger.valueOf(1);
        BigInteger z = BigInteger.valueOf(0);
        long n = no;
        BigInteger yt = BigInteger.valueOf(0);
        for (x = 0; x < n; x++) {
            yt = y.add(z);
            BigInteger temp = y;
            y = z;
            z = temp.add(y);
        }
        return yt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}



