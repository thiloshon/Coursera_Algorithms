package AlgorithmicToolBox.Week_03;

import java.util.Scanner;

public class PointsAndSegments {

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        //write your code here

        randomizedQuickSort(starts, 0, starts.length-1, ends);

        /*for(int x = 0; x<starts.length;x++){
            System.out.println(starts[x] +" " + ends[x]);
        }*/

        for (int i = 0; i < points.length; i++) {

            for (int j = 0; starts[j] <= points[i]; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
                if(j==starts.length-1)break;
            }
        }

        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }


    private static int[] partition3(int[] a, int x, int y, int[] b) {
        //write your code here

        int i=x, j=x, z=x, border=a[y];

        for(z=x;z<y;z++){
            //System.out.println(z);
            if (a[z]<border){
                int temp=a[z];    int temp2 = b[z];
                a[z]=a[j];       b[z]=b[j];
                a[j]=a[i];        b[j]=b[i];
                a[i]=temp;        b[i]=temp2;
                //System.out.print("j changes from " + j + " to " );
                i++;
                j++;
                //System.out.println(j);
            }else if(a[z]==border){
                if (z==j){
                    j++;
                }else{
                    int temp= a[z];   int temp2 = b[z];
                    a[z]=a[j];         b[z]=b[j];
                    a[j]=temp;         b[j]=temp2;
                    j++;
                }

            }
        }

        int temp=a[j];    int temp2 = b[j];
        a[j]=a[y];        b[j]=b[y];
        a[y]=temp;        b[y]=temp2;


        //System.out.println(i + " " + j);
        int m1 = i;
        int m2 = j;
        int[] m = {m1, m2};
        return m;
    }

    private static void randomizedQuickSort(int[] a, int x, int y, int[] b) {
        if (x >= y) {
            return;
        }

        int m[] = partition3(a, x, y, b);
        randomizedQuickSort(a, x, m[0] - 1, b);
        randomizedQuickSort(a, m [1], y, b);

    }
}

