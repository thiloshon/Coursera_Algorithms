package Week_04;

/**
 * Created by Thiloshon on 12-Sep-16.
 */
import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        //write you code here
        int result = 0;
        for (int i = 0; i < w.length; i++) {
            if (result + w[i] <= W) {
                result += w[i];
            }
        }
        return result;
    }
    static int optimalWeightEffective(int W, int[] weight) {

        int[][] value = new int[W+1][weight.length+1];

        for(int w = 1; w<=W;w++){
            for(int i=1; i<weight.length;i++){
                value[w][i]=value[w][i-1];
                if(weight[i]<=w){
                    int val = value[w-weight[i]][i-1]+weight[i];
                    if (value[w][i]<val){
                        value[w][i]=val;
                    }
                }
            }
            /*System.out.println("------------------------------------------------");
            for (int x =0; x<=W; x++){
                for(int y =0; y<weight.length;y++){
                    System.out.print(value[x][y]+" ");
                }
                System.out.println("");
            }*/

        }

        /*for (int x =0; x<W; x++){
            for(int y =0; y<w.length;y++){
                System.out.print(value[x][y]+" ");
            }
            System.out.println("");
        }*/


        int result = value[W][weight.length-1];
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n+1];
        w[0]=0;
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeightEffective(W, w));
    }
}


