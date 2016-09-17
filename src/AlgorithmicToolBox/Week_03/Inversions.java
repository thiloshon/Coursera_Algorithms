package AlgorithmicToolBox.Week_03;
import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            if (right - left > 0) {
                if (a[left] > a[right]) {
                    //System.out.println(right+" had " + a[right] +" gets one");
                    numberOfInversions++;
                    int temp = a[left];
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
            if (a[LHS] > a[ave + 1]) {
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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length - 1));
        for(int x: a){
            System.out.print(x+" ");
        }
    }
}

