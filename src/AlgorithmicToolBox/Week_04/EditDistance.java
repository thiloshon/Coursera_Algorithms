package AlgorithmicToolBox.Week_04;

/**
 * Created by Thiloshon on 12-Sep-16.
 */
import java.util.*;

class EditDistance {
    public static int EditDistance(String s, String t) {
        //write your code here
        char[] charArray = s.toCharArray();
        char[] arr1 = new char[charArray.length+1];
        //System.out.println(arr1.length);
        System.arraycopy(charArray, 0, arr1, 1, charArray.length);
        char[] charArray1 = t.toCharArray();
        char[] arr2 = new char[charArray1.length+1];
        //System.out.println(arr2.length);
        System.arraycopy(charArray1, 0, arr2, 1, charArray1.length);

        int[][] DArray = new int[charArray1.length+1][charArray.length+1];

        for(int h =0; h<=charArray1.length;h++){
           DArray[h][0]=h;
        }
        for(int g =0; g<=charArray.length;g++){
            DArray[0][g]=g;
        }

        for(int i =1; i<arr2.length; i++){
            for(int j = 1; j<arr1.length;j++){
                int insertion = DArray[i][j-1]+1;
                int deletion = DArray[i-1][j]+1;
                int match = DArray[i-1][j-1];
                int mismatch = DArray[i-1][j-1]+1;
                //System.out.println("i : j = " +i+" : " + j);
                if(arr1[j]==arr2[i]){

                    int min1= Math.min(insertion, deletion);
                    DArray[i][j]=Math.min(min1, match);
                }else{
                    int min1= Math.min(insertion, deletion);
                    DArray[i][j]=Math.min(min1, mismatch);
                }
            }

        }
        return DArray[arr2.length-1][arr1.length-1];
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}

