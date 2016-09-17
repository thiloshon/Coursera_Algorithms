package AlgorithmicToolBox.Week_04;

import java.util.Scanner;

/**
 * Created by Thiloshon on 13-Sep-16.
 */
public class stack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        parenthesis(exp);
    }

    private static void parenthesis(String expression) {
        resultStorage = new long[100][100][100];
        long[] results = parenthesis(expression, 0, 0);
        System.out.println(results[1]);
    }

    private static long[][][] resultStorage;

    public static long[] parenthesis(String expression, int start, int end) {
        if (resultStorage[start][end][2] == 1)
            return resultStorage[start][end];

        try {
            long parsedLong = Long.parseLong(expression);
            return new long[] { parsedLong, parsedLong, 1 };
        } catch (NumberFormatException e) {
            //
        }

        long[] result = { Integer.MAX_VALUE, Integer.MIN_VALUE, 1 };
        for (int i = 1; i < expression.length() - 1; i++) {
            if (Character.isDigit(expression.charAt(i)))
                continue;
            long[] left = parenthesis(expression.substring(0, i), start, start + i);
            long[] right = parenthesis(expression.substring(i + 1, expression.length()), start + i + 1, end);
            for (int li = 0; li < 2; li++) {
                for (int ri = 0; ri < 2; ri++) {
                    switch (expression.charAt(i)) {
                        case '+':
                            result[0] = Math.min(result[0], left[li] + right[ri]);
                            result[1] = Math.max(result[1], left[li] + right[ri]);
                            break;
                        case '-':
                            result[0] = Math.min(result[0], left[li] - right[ri]);
                            result[1] = Math.max(result[1], left[li] - right[ri]);
                            break;
                        case '*':
                            result[0] = Math.min(result[0], left[li] * right[ri]);
                            result[1] = Math.max(result[1], left[li] * right[ri]);
                            break;
                        case '/':
                            if (right[ri] != 0)
                                result[0] = Math.min(result[0], left[li] / right[ri]);
                            if (right[ri] != 0)
                                result[1] = Math.max(result[1], left[li] / right[ri]);
                            break;
                    }
                }
            }
        }

        resultStorage[start][end] = result;
        return result;
    }
}
