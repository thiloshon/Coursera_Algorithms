package Week_01;

/**
 * Created by Thiloshon on 04-May-16.
 * <p>
 * Using Euclidean Algorithm.
 */
public class GreatestCommonDivisor_Efficient {


    public static void main(String args[]) {
        long x = 28851538;
        long y = 1183019;
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
            System.out.println("" + great + " " + low);
        }
    }

    public static long GCD(int a, int b){

        long x =a;
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
            System.out.println("" + great + " " + low);
        }

        return  great;

    }

}
