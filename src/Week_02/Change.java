package Week_02;

import java.util.Scanner;

public class Change {
    private static int getChange(int n) {
        //write your code here
        int[] arr = {10, 5, 1};
        int ans = 0;
        for (int x = 0; x < 3; x++) {
            while ((n > arr[x]) || n == arr[x]) {
                ans++;
                n = n - arr[x];
            }
        }
        n = ans;
        return n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

    }
}

