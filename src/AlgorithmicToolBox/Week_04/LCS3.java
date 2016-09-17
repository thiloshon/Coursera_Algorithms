package AlgorithmicToolBox.Week_04;

/**
 * Created by Thiloshon on 13-Sep-16.
 */
import java.util.*;

public class LCS3 {

    private static long lcs3(int[] a, int[] b, int[] c) {
        //Write your code here

        long[][] table = new long[a.length][b.length];
        for(int i=1;i<a.length;i++){
            for (int j=1;j<b.length;j++){
                if(a[i]==b[j]){
                    table[i][j]=table[i-1][j-1]+1;
                }else if (table[i-1][j]>=table[i][j-1]){
                    table[i][j]=table[i-1][j];
                }else{
                    table[i][j]=table[i][j-1];
                }
            }
        }

        long[][] table2 = new long[a.length][c.length];
        for(int i=1;i<a.length;i++){
            for (int j=1;j<c.length;j++){
                if(a[i]==c[j]){
                    table2[i][j]=table2[i-1][j-1]+1;
                }else if (table2[i-1][j]>=table2[i][j-1]){
                    table2[i][j]=table2[i-1][j];
                }else{
                    table2[i][j]=table2[i][j-1];
                }
            }
        }

        long[][] table3 = new long[b.length][c.length];
        for(int i=1;i<b.length;i++){
            for (int j=1;j<c.length;j++){
                if(b[i]==c[j]){
                    table3[i][j]=table3[i-1][j-1]+1;
                }else if (table3[i-1][j]>=table3[i][j-1]){
                    table3[i][j]=table3[i-1][j];
                }else{
                    table3[i][j]=table3[i][j-1];
                }
            }
        }

        System.out.println("------------------------------------------------");
        for (int x =0; x<a.length; x++){
            for(int y =0; y<b.length;y++){
                System.out.print(table[x][y]+" ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------");
        for (int x =0; x<a.length; x++){
            for(int y =0; y<c.length;y++){
                System.out.print(table2[x][y]+" ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------");
        for (int x =0; x<b.length; x++){
            for(int y =0; y<c.length;y++){
                System.out.print(table3[x][y]+" ");
            }
            System.out.println("");
        }

        long ans1 = table[a.length-1][b.length-1];
        long ans2 = table2[a.length-1][c.length-1];
        long ans3 = table3[b.length-1][c.length-1];



        return Math.min(Math.min(ans1,ans2 ), ans3);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an+1];
        for (int i = 1; i < an+1; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn+1];
        for (int i = 1; i < bn+1; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn+1];
        for (int i = 1; i < cn+1; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

