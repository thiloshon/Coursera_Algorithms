package Week_04;

/**
 * Created by Thiloshon on 11-Sep-16.
 */

import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[][] arr = new int[n + 1][2];

        arr[0][0] = 0;
        arr[0][1] = 0;
        arr[1][0] = 1;
        arr[1][1] = 1;

        for (int x = 2; x <= n; x++) {
            int count = 1000;
            int track = 0;

            if (x > 2 && x % 3 == 0) {
                int temp = x / 3;
                count = arr[temp][1] + 1;
                track = 3;
            }
            if (x % 2 == 0) {
                int temp = x / 2;
                int tempcount = arr[temp][1] + 1;
                if (tempcount < count) {
                    count = tempcount;
                    track = 2;
                }
            }
            int tempcount = arr[x - 1][1] + 1;
            if (tempcount < count) {
                count = tempcount;
                track = 1;
            }

            arr[x][1] = count;
            arr[x][0] = track;
        }

        int backTrack = n;
        while (backTrack > 0) {
            sequence.add(backTrack);
            int function = arr[backTrack][0];
            switch (function) {
                case 3:
                    backTrack = backTrack / 3;
                    break;
                case 2:
                    backTrack = backTrack / 2;
                    break;
                case 1:
                    backTrack = backTrack - 1;
            }
        }

        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}


