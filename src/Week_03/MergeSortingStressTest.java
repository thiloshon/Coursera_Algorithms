package Week_03;

import java.util.Scanner;

/**
 * Created by Thiloshon on 03-Sep-16.
 */
public class MergeSortingStressTest {
    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            if (right - left > 0) {
                if (a[left] > a[right]) {
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
                    LHS++;
                }
                break;
            }
            if (a[LHS] > a[ave + 1]) {
                numberOfInversions+=ave+1-x;
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
        /*Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(naive(a));*/
        //System.out.println(getNumberOfInversions(a, b, 0, a.length - 1));
        /*for(int x: a){
            System.out.print(x+" ");
        }*/


        while (true){
            int num = (int)(Math.random()*10);
            //System.out.println("num is " + num);
            int[] test = new int[num];
            for(int c=0; c<num; c++){
                test[c]=(int)(Math.random()*10);
            }
            int[] b = new int[num];
            //for(int g: test) System.out.println(g);
            int[] test1 = new int[num];
            int[] test2 = new int[num];
            System.arraycopy( test, 0, test1, 0, test.length );
            System.arraycopy( test, 0, test2, 0, test.length );
            long sd = getNumberOfInversions(test1, b, 0, num-1);
            long sd2 = naive(test2);

            if (sd!=sd2){
                //System.out.println("eff " + );
                for(int x: test){
                    System.out.print(x+" ");
                }
                break;
            }else{
                System.out.println("Okay");
            }

            /*boolean check = false;
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
            if(check) break;*/
        }
    }

    private static long naive(int[] a){
        long ans=0;
        for(int x =0; x<a.length;x++){
            for(int y=x; y>=0; y--){
                if (a[y]>a[x]){
                    ans++;
                }
            }
        }
        return ans;
    }
}

