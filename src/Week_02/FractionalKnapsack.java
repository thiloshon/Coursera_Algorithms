package Week_02;

import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        //write your code here



        while (capacity>0){
            int num = findMax(values, weights);
            //System.out.println("num is "+ num);
            if (capacity>weights[num]){
               // System.out.print("capacity changed from "+ capacity+ " to ");
                capacity=capacity-weights[num];
               // System.out.println(capacity);
               // System.out.print("value changed from "+ value+ " to ");
                value=value+values[num];
               // System.out.println(value);


            }else {
                value=value+ (values[num]/weights[num])*capacity;
                capacity=0;
            }
            values[num]=0;
           // System.out.println("");
           // System.out.println("");
          //  System.out.println("");System.out.println("");System.out.println("");


        }

        /*while ((weights[num] > maxIndex||n==arr[x]){
            ans++;
            n=n-arr[x];
        }*/

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
    public static int findMax(int[] values, int[] weights) {
        int maxIndex = 0;
        int value=0;

        for (int i = 0; i < values.length; i++) {
          //  System.out.println(values[i]/ weights[i]);
          //  System.out.println(values[i] + " "+ weights[i]+ " "+ maxIndex);
            if (values[i] / weights[i] > value) {
           //     System.out.print( "max changed from "+ maxIndex + " to ");
                value=values[i] / weights[i];

                maxIndex = i;
            //    System.out.println(maxIndex);
            }
        }


        return maxIndex;
    }
} 
