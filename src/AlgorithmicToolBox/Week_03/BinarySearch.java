package AlgorithmicToolBox.Week_03;

import java.io.*;
import java.util.*;

public class BinarySearch {

    static int binarySearch(int[] a, int x) {
        int left = 0, right = a.length;
        //write your code here

        return -1;
    }

    static int binarySearch(int[] array, int key, int low, int high) {

        //write your code here


        if (low>high){
            //return low-1;
            return -1;
        }else {
            int mid = low+ (int)Math.floor((high-low)/2);
            if (key==array[mid]){
                return mid;
            }else if (key<array[mid]){
                return binarySearch(array, key, low, mid-1);

            }else if (key>array[mid]){
                return binarySearch(array, key, mid +1, high);
            }
        }

        //This return statement wont work in any cases
        return -1;
    }


    static int linearSearch(int[] a, int x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
          b[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            //replace with the call to binarySearch when implemented
            System.out.print(binarySearch(a, b[i] , 0, a.length-1) + " ");
            //System.out.print(linearSearch(a, b[i]) + " ");
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
