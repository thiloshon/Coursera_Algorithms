package Week_03;

import java.io.*;
import java.util.*;

public class Sorting {
    private static Random random = new Random();

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

    private static int[] partition3way(int[] a, int x, int y) {
      //write your code here

        int i =x , j = x, z=0;
        int border = a[y];

        for(z=x; z<y; z++){
            if (a[z]<border){
                int temp = a[i];
                a[i]=a[z];
                a[z]=temp;
                temp=a[j];
                a[j]=a[z];
                a[z]=temp;
                i++;
                j++;
            }else if(a[z]==border){
                int temp= a[j];
                a[j]=a[z];
                a[z]=temp;
                j++;
            }
        }

        //j++;
        int temp = a[j];
        a[j]=a[z];
        a[z]=temp;




      int m1 = i+x;
      int m2 = j+x;
      int[] m = {m1, m2};
        for(int g : m)System.out.print(g + " ");
        System.out.println(" ");
      return m;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                //System.out.println(i + " " + j);
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            //System.out.println("Returning");
            return;
        }
        /*int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;*/
        //use partition3
        //System.out.println(" " + l +" " + r);
        //int m = partition2(a, l, r);
        //for(int g : a)System.out.println(g);

        int[] ans = new int[2];

        ans = partition3(a, l, r);
        /*randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);*/
        randomizedQuickSort(a, l, ans[0]-1);
        randomizedQuickSort(a, ans[1]+1, r);
    }

    private static void randomizedQuickSortnaive(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        //System.out.println(" " + l +" " + r);
        int m = partition2(a, l, r);

        //int[] ans = partition3(a, l, r);
        randomizedQuickSort(a, l, m - 1);
        randomizedQuickSort(a, m + 1, r);
        /*randomizedQuickSort(a, l, ans[0]-1);
        randomizedQuickSort(a, ans[1]+1, r);*/
    }

    public static void main(String[] args) {
        /*FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }*/

        while (true){
            int num = (int)(Math.random()*10);
            //System.out.println("num is " + num);
            int[] test = new int[num];
            for(int c=0; c<num; c++){
                test[c]=(int)(Math.random()*10);
            }
            //for(int g: test) System.out.println(g);
            int[] test1 = new int[num];
            int[] test2 = new int[num];
            System.arraycopy( test, 0, test1, 0, test.length );
            System.arraycopy( test, 0, test2, 0, test.length );

            randomizedQuickSort(test2, 0, num-1);
            randomizedQuickSortnaive(test1, 0, num-1);

            boolean check = false;
            for(int s=0; s<num;s++){
                if(test1[s]!=test2[s]){
                    System.out.println("Boom!");
                    for(int d : test){
                        System.out.println(d);
                    }
                    check=true;
                    break;
                }
                System.out.print(".");
            }
            if(check) break;
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

