package Week_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Thiloshon on 29-Aug-16.
 */
public class LargestNumberEfficient {
    private static String largestNumber(ArrayList a) {


        Collections.sort(a, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String temp1 = str1+str2;
                String temp2 = str2+str1;
                return Integer.parseInt(temp2)-Integer.parseInt(temp1);
            }
        });

        String result = "";
        for (int i = 0; i < a.size(); i++) {

            result += a.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<String> a = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.next());
        }
        System.out.println(largestNumber(a));
    }
}


