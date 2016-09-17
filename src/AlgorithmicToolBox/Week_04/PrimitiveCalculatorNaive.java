package AlgorithmicToolBox.Week_04;

/**
 * Created by Thiloshon on 10-Sep-16.
 */

import java.util.*;

public class PrimitiveCalculatorNaive {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0) {
                n /= 3;
            } else if (n % 2 == 0) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    private static ArrayList<Integer> optimal_sequence_Naive(int n) {
        if (n == 1) {
            ArrayList temp = new ArrayList<>();
            temp.add(1);
            return temp;
        }
        ArrayList<Integer> sequence = new ArrayList<Integer>(1000);
        for (int x = 0; x < 1000; x++) {
            sequence.add(0);
        }

        double answ1 = Double.POSITIVE_INFINITY;

        if (n % 3 == 0) {

            ArrayList<Integer> temp = optimal_sequence_Naive(n / 3);

            //System.out.println(n+ " in 3");


            if (temp.size() < sequence.size()) {
                //System.out.println(n+ " in 3");
                sequence.clear();
                for (Integer in : temp) {
                    //System.out.println(in);
                    sequence.add(in);
                }

            }
            //sequence.add(n);
        }
        if (n % 2 == 0) {

            /*double coins = optimal_sequence_Efficient(n / 2) + 1;
            sequence.add(n / 2);
            if (coins < answ1) {
                answ1 = coins;
            }*/
            //System.out.println(n / 2);
            ArrayList<Integer> temp = optimal_sequence_Naive(n / 2);
            //System.out.print(temp.size());
            //System.out.println(sequence.size());


            if (temp.size() < sequence.size()) {
                //System.out.println(n+" in 2");
                sequence.clear();
                for (Integer in : temp) {
                    //System.out.println(in);
                    sequence.add(in);
                }

            }
            sequence.add(n);
        }


        ArrayList<Integer> temp = optimal_sequence_Naive(n - 1);

        if (temp.size() < sequence.size() - 1) {
            //System.out.println(n + " in 1");
            sequence.clear();
            for (Integer in : temp) {
                //System.out.println(in);
                sequence.add(in);
            }

        }
        sequence.add(n);

        /*double coins = optimal_sequence_Efficient(n - 1) + 1;
        sequence.add(n - 1);
        if (coins < answ1) {
            answ1 = coins;
        }*/


        /*for (int value : sequence) {
            System.out.println(value);
        }*/
        //System.out.println(answ1);

        System.out.println(sequence.size());
        return sequence;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        /*List<Integer> sequence = optimal_sequence(n);*/
        ArrayList<Integer> ah = optimal_sequence_Naive(n);
        for (Integer x : ah) {
            System.out.print(x + " ");
        }
        /*System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }*/
    }
}

