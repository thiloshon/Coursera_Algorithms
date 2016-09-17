/**
 * Created by Thiloshon on 04-May-16.
 */

package AlgorithmicToolBox.Week_01;
import java.util.*;

public class LeastCommonMultiple {



    private static long lcm(long a, long b) {
        //write your code here
        long GCD = GCD(a,b);
        long lCM= (a*b)/GCD;
        long ans=(a*b);
        //System.out.println(ans);
        return lCM;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextInt();
        long b = scanner.nextInt();

        System.out.println(lcm(a, b));
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
