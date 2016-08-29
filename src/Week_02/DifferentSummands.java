package Week_02;

import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here

        //  If
        //  k <= 2l, we output just one summand k. Otherwise we output l and then solve the sub problem (k-l; l+1).


        int k=n;
        int m=1;

        while (true){
            if(k<=2*m){
                summands.add(k);
                break;
            }else{
                summands.add(m);
                k=k-m;
                m++;
            }
        }
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

