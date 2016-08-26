package Week_01;

import java.math.BigInteger;

/**
 * Created by Thiloshon on 26-Aug-16.
 */
public class PisanoNumber {
    public static void main(String[] args) {
        System.out.println(pisanoNumber(1000));
    }

    private static long pisanoNumber(long modulo) {
        int count = 0;
        boolean condition01 = false, condition02 = false, condition03 = false;


        while (true) {
            System.out.println(count);
            //BigInteger number = fibonochiNumber(count);
            BigInteger number = fibonochiNumber(count);
            //number=number%modulo;
            number=number.divideAndRemainder(BigInteger.valueOf(modulo))[1];
            //System.out.println(number);

            if (!(condition01 && condition02 && condition03) && number.intValue() == 0 && count>2) {
                condition01 = true;
            } else if (condition01 && !(condition02) && !(condition03) && number.intValue() == 1) {
                condition02 = true;
            } else if (condition01 && condition02 && (!(condition03)) && number.intValue() == 1) {
                condition03=true;
                return count - 2;

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
