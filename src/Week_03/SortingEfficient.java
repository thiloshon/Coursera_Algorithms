package Week_03;

/**
 * Created by Thiloshon on 01-Sep-16.
 */
import java.io.*;
import java.util.*;

public class SortingEfficient {

    private static int[] partition3(int[] a, int x, int y) {
        //write your code here

        int i=x, j=x, z=x, border=a[y];

        for(z=x;z<y;z++){
            //System.out.println(z);
            if (a[z]<border){
                int temp=a[z];
                a[z]=a[j];
                a[j]=a[i];
                a[i]=temp;
                //System.out.print("j changes from " + j + " to " );
                i++;
                j++;
                //System.out.println(j);
            }else if(a[z]==border){
                if (z==j){
                    j++;
                }else{
                    int temp= a[z];
                    a[z]=a[j];
                    a[j]=temp;
                    j++;
                }

            }
        }

        int temp=a[j];
        a[j]=a[y];
        a[y]=temp;


        //System.out.println(i + " " + j);
        int m1 = i;
        int m2 = j;
        int[] m = {m1, m2};
        return m;
    }

    private static void randomizedQuickSort(int[] a, int x, int y) {
        if (x >= y) {
            return;
        }

        int m[] = partition3(a, x, y);
        randomizedQuickSort(a, x, m[0] - 1);
        randomizedQuickSort(a, m [1], y);

    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}


