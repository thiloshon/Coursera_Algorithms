package Week_03;

import java.util.Scanner;

/**
 * Created by Thiloshon on 05-Sep-16.
 */
public class PointsAndSegmentsEffective {
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

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];

        //write your code here
        int length =2*(starts.length)+points.length;
        int[][] masterArray = new int[length][2];
        int x=0;

        for(x=0; x<starts.length;x++){
            masterArray[x][0]=starts[x];
            masterArray[x][1]=-3;
        }
        /*for(int[] seg: masterArray){
            System.out.println(seg[0] +" "+ seg[1]);
        }*/

        int temp = starts.length;
        for(x=0; x<points.length;x++){
            masterArray[temp][0]=points[x];
            masterArray[temp][1]=x;
            temp++;
        }


        for( x=0; x<ends.length;x++){
            masterArray[temp][0]=ends[x];
            masterArray[temp][1]=-1;
            temp++;
        }


        int[][] finalArray = new int[masterArray.length][2];

        getNumberOfInversions(masterArray, finalArray, 0, masterArray.length-1);

        int count=0;
        for(int cont =0; cont<length; cont++){
            if (masterArray[cont][1]==-3){
                count++;
            }else if (masterArray[cont][1]==-1){
                count--;
            }else {
                //System.out.println(masterArray[cont][1]);
                cnt[masterArray[cont][1]]=count;
            }
        }

        return cnt;
    }

    private static long getNumberOfInversions(int[][] a, int[][] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            if (right - left > 0) {
                if (a[left][0] > a[right][0]) {
                    //System.out.println(right+" had " + a[right] +" gets one");
                    numberOfInversions++;
                    int[] temp = a[left];
                    a[left] = a[right];
                    a[right] = temp;
                }
            }

            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave + 1, right);
        int LHS = left;
        int mid=ave+1;
        //int RHS = right;
        for (int x = left; x <= right ; x++) {
            if (ave >= right) {

                for (int z = x; z <= right; z++) {
                    //System.out.println(z);
                    b[z] = a[LHS];
                    LHS++;
                }
                break;
            }
            if(LHS>=mid){
                for (int z = x; z <= right; z++) {
                    //System.out.println(z);
                    b[z] = a[x];
                    x++;
                }
                break;
            }
            if (a[LHS][0] > a[ave + 1][0]) {
                numberOfInversions+=ave+1-x;
                //System.out.println((ave+1)+" had " + a[ave+1] +" gets " + (ave+1-x));
                b[x] = a[ave + 1];
                ave++;
            } else {
                b[x] = a[LHS];
                LHS++;
            }
        }
        for(int x = left;x<=right;x++){
            a[x]=b[x];
        }

        //write your code here
        return numberOfInversions;
    }

    
}
