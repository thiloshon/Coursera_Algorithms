package AlgorithmicToolBox.Week_01;

/**
 * Created by Thiloshon on 04-May-16.
 */

        import java.util.*;

public class Fibonacci_LastDigit {

    private static int getFibonacciLastDigit(int n) {


        //long x;
        int y=1;
        int z=0;
        int yt=0;

        for(int x=0; x<n; x++){
            yt = y+z;
            //if (x==n-1){

            //    }
            int temp = y;
            y=z%10;
            z=(temp+y)%10;
            //System.out.println(yt%10);
        }

        return yt%10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigit(n);
        System.out.println(c);
    }
}

