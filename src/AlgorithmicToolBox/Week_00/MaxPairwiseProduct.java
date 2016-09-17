package AlgorithmicToolBox.Week_00;

/**
 * Created by Thiloshon on 24-Aug-16.
 */
import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static int getMaxPairwiseProduct(int[] numbers) {
        int result = 0;
        int n = numbers.length;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (numbers[i] * numbers[j] > result) {
                    result = numbers[i] * numbers[j];
                }
            }
        }
        return result;
    }

    static long getMaxPairwiseProductFast(int[] numbers) {
        long result = 0;
        int index1 =0;
        long number1=0;
        long number2= 0;
        for (int x=0; x<numbers.length; x++){
            if(numbers[x]>number1){
                number1=numbers[x];
                index1=x;
            }

            //System.out.println("in one" + index1+" "+ number1 + " " + number2);
        }

        for (int x =0; x<numbers.length; x++){
            if (x!=index1 && numbers[x]>number2){
                number2=numbers[x];
            }
            //System.out.println("in two" + index1+" "+ number1 + " " + number2);
        }
        result =number1*number2;

        return result;


    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProductFast(numbers));
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