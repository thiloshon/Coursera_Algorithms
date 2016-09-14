package Week_04;

import java.util.Scanner;

/**
 * Created by Thiloshon on 14-Sep-16.
 */


public class LCS3Correct {

    private static long lcs3(int[] a, int[] b, int[] c) {
        //Write your code here

        long[][][] table = new long[a.length][b.length][c.length];
        for (int i = 1; i < a.length; i++) {
            for (int j = 1; j < b.length; j++) {
                for (int k = 1; k < c.length; k++) {
                    if (a[i] == b[j] && b[j] == c[k]) {
                        table[i][j][k] = table[i - 1][j - 1][k - 1] + 1;
                    } else {
                        table[i][j][k] = Math.max(table[i - 1][j][k], Math.max(table[i][j - 1][k], table[i][j][k - 1]));
                    }
                }
            }


        }
        return table[a.length-1][b.length-1][c.length-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an + 1];
        for (int i = 1; i < an + 1; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn + 1];
        for (int i = 1; i < bn + 1; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn + 1];
        for (int i = 1; i < cn + 1; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}


