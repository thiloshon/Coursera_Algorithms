package Week_03;

import java.util.*;
import java.io.*;

public class MajorityElement {
    private static int getMajorityElement(int[] a, int left, int right) {
        /*if (left == right) {
            return -1;
        }
        if (left + 1 == right) {
            return a[left];
        }*/
        //write your code here

        if (right - left ==1) {
            if(a[left]==a[right]){
                return a[left];
            }else return -1;
        }
        if (right - left ==2) {
            if(a[left]==a[right]){
                return a[left];
            }else if(a[left]==a[left+1]){
                return a[left];
            }else if(a[left+1]==a[right]){
                return a[right];
            }else return -1;
        }

        int mid = ((right-left)/2)+left;

        int RHS = getMajorityElement(a, left, mid);
        int LHS = getMajorityElement(a, mid+1, right);

        if(RHS!=-1){
            int count=0;
            for(int x = left;x<=right;x++){
                if (a[x]==RHS){
                    count++;
                }
            }
            double temp= (right-left)/2.0;
            //System.out.println(temp);
            if(count>Math.ceil(temp)){
                return RHS;
            }
        }
        if(LHS!=-1){
            int count=0;
            for(int x = left;x<=right;x++){
                if (a[x]==LHS){
                    count++;
                }
            }
            double temp= (right-left)/2.0;
            //System.out.println(temp);
            if(count>Math.ceil(temp)){
                return LHS;
            }
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
        if (getMajorityElement(a, 0, a.length-1) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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

