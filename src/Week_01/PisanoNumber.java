package Week_01;

import java.math.BigInteger;

/**
 * Created by Thiloshon on 26-Aug-16.
 */
public class PisanoNumber {
    public static void main(String[] args) {
        System.out.println(pisanoNumberlong(1000000));
    }

    private static long pisanoNumber(long modulo) {
        int count = 0;
        boolean condition01 = false, condition02 = false, condition03 = false;


        //long x;
        BigInteger y = BigInteger.valueOf(1);
        BigInteger z = BigInteger.valueOf(0);

        //BigInteger yt = BigInteger.valueOf(0);

        while (true) {

            //(a + b) mod n = [(a mod n) + (b mod n)] mod n
            //(y + z) % modulo = [(y % modulo) + (z % modulo)] % modulo


            //yt = y.add(z);
            BigInteger number = ((y.divideAndRemainder(BigInteger.valueOf(modulo)))[1].add(z.divideAndRemainder(BigInteger.valueOf(modulo))[1])).divideAndRemainder(BigInteger.valueOf(modulo))[1];

            BigInteger temp = y;
            y = z;
            z = temp.add(y);

           // System.out.println(count);
            //BigInteger number = fibonochiNumber(count);
            //number = yt;
            //number=number%modulo;
           // number = number.divideAndRemainder(BigInteger.valueOf(modulo))[1]; TODO uncomment this

            //System.out.println(number);

            if (!(condition01 && condition02 && condition03) && number.intValue() == 0 && count > 2) {
                condition01 = true;
            } else if (condition01 && !(condition02) && !(condition03) && number.intValue() == 1) {
                condition02 = true;
            } else if (condition01 && condition02 && (!(condition03)) && number.intValue() == 1) {
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


    private static long pisanoNumberlong(long modulo) {
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
            long number = (y+z)%modulo;

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

}
