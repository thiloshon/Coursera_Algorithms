package Week_03;

import java.util.Scanner;

/**
 * Created by Thiloshon on 05-Sep-16.
 */
public class MergeSort {

    private static void mergeSort(int[] a, int[] b, int left, int right) {

        if (right <= left + 1) {
            if (right - left > 0) {
                if (a[left] > a[right]) {
                    int temp = a[left];
                    a[left] = a[right];
                    a[right] = temp;
                }
            }

            return;
        }
        int ave = (left + right) / 2;
        mergeSort(a, b, left, ave);
        mergeSort(a, b, ave + 1, right);
        int LHS = left;
        int mid = ave + 1;
        for (int x = left; x <= right; x++) {
            if (ave >= right) {
                for (int z = x; z <= right; z++) {
                    b[z] = a[LHS];
                    LHS++;
                }
                break;
            }
            if (LHS >= mid) {
                for (int z = x; z <= right; z++) {
                    b[z] = a[x];
                    LHS++;
                }
                break;
            }
            if (a[LHS] > a[ave + 1]) {
                b[x] = a[ave + 1];
                ave++;
            } else {
                b[x] = a[LHS];
                LHS++;
            }
        }
        for (int x = left; x <= right; x++) {
            a[x] = b[x];
        }

        return;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];

        mergeSort(a, b, 0, a.length - 1);
        for (int x : a) {
            System.out.print(x + " ");
        }

    }

}