/**
 * Created by Thiloshon on 04-May-16.
 */

package Week_01;
import java.util.*;

public class LeastCommonMultiple {

static GreatestCommonDivisor_Efficient GCDivisor = new GreatestCommonDivisor_Efficient();

    private static long lcm(int a, int b) {
        //write your code here
        long GCD = GCDivisor.GCD(a,b);
        long lCM= (a*b)/GCD;
        long ans=(a*b);
        System.out.println(ans);
        return lCM;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lcm(a, b));
    }
}
