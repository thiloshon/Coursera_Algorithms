package Week_02;

import java.util.Scanner;

public class FractionalKnapsack {
    private static double getOptimalValue(double capacity, double[] values, double[] weights) {
        double value = 0;

        while (capacity > 0) {
            int num = findMax(values, weights);

            if (capacity > weights[num]) {
                capacity = capacity - weights[num];
                value = value + values[num];

            } else {

                //System.out.println((double)(values[num] / weights[num]));
                value = value + ((values[num] / weights[num]) * capacity);
                capacity = 0;
            }
            values[num] = 0;
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
        double[] values = new double[n];
        double[] weights = new double[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    public static int findMax(double[] values, double[] weights) {
        int maxIndex = 0;
        double value = 0;

        for (int i = 0; i < values.length; i++) {

            if (values[i] / weights[i] > value) {
                value = values[i] / weights[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
} 
