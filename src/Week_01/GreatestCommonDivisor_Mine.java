/**
 * Created by Thiloshon on 03-May-16.
 *
 * Problem: Small Fibonacci_Mine Number
 *
 */package Week_01;
public class GreatestCommonDivisor_Mine {

    public static void main(String[] args) {

        long x=28851538;
        //long f =454554545;
        long y =1183019;

        long greatest;
        if (x>y){
            greatest=x;
        }else greatest=y;

        long answer=0;

        for (int z=1; z<greatest/2; z++){
            if (x%z==0&&y%z==0){
                answer=z;
            }
        }
        System.out.print(answer);

    }
}
