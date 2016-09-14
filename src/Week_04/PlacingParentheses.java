package Week_04;

/**
 * Created by Thiloshon on 12-Sep-16.
 */
import java.util.Scanner;

public class PlacingParentheses {
    private static long getMaximValue(String exp) {
        //write your code here
        exp = exp.trim();
        char[] charArray = exp.toCharArray();

        long[][] maxima = new long[charArray.length/2+2][charArray.length/2+2];
        long[][] minima = new long[charArray.length/2+2][charArray.length/2+2];


        /*for(int x =0; x<charArray.length;x++){
            System.out.println(charArray[x]);
        }*/


        int count =0;
        for (int x =1; x<charArray.length/2+2; x++){
            //System.out.println(x);
            maxima[x][x]=Character.getNumericValue(charArray[2*x-2]);
            minima[x][x]=Character.getNumericValue(charArray[2*x-2]);
        }

        for(int s=1; s<=charArray.length/2+1;s++){
            for(int i =1;i<charArray.length/2+2-s;i++){
                int j = i+s;
                //System.out.println("i:j = "+ i+" : " +j);
                long[] temp = minAndMax(i,j,charArray, maxima,minima);
                maxima[i][j]=(int)temp[0];
                minima[i][j]=(int)temp[1];


                /*System.out.println("------------------------------------------------");
                for (int x =1; x<maxima.length; x++){
                    for(int y =1; y<maxima.length;y++){
                        System.out.print(maxima[x][y]+" ");
                    }
                    System.out.println("");
                }*/
            }


        }
        /*System.out.println("------------------------------------------------");
        for (int x =0; x<maxima.length; x++){
            for(int y =0; y<maxima.length;y++){
                System.out.print(maxima[x][y]+" ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------------------");
        for (int x =0; x<maxima.length; x++){
            for(int y =0; y<maxima.length;y++){
                System.out.print(minima[x][y]+" ");
            }
            System.out.println("");
        }*/
        /*System.out.println("------------------------------------------------");
        for (int x =0; x<maxima.length; x++){
            for(int y =0; y<maxima.length;y++){
                System.out.print(maxima[x][y]+" ");
            }
            System.out.println("");
        }*/



        return maxima[1][maxima.length-1];
        //return 6;
    }
    private static long[] minAndMax(int i, int j, char[] array, long[][] maxima, long[][] minima) {
        long min = (long)Double.POSITIVE_INFINITY;
        long max = (long)Double.NEGATIVE_INFINITY;
        long[] ans =new long[2];
        for(int k = i; k<=j-1; k++){
            long a = eval(maxima[i][k],maxima[k+1][j], array[2*k-1]);
            long b = eval(maxima[i][k],minima[k+1][j], array[2*k-1]);
            long c = eval(minima[i][k],maxima[k+1][j], array[2*k-1]);
            long d = eval(minima[i][k],minima[k+1][j], array[2*k-1]);
            long temp = Math.min(min,a);
            //System.out.println("silly "+a);
            temp = Math.min(temp,b);
            temp = Math.min(temp,c);
            min = Math.min(temp,d);


            temp = Math.max(a,max);
            temp= Math.max(temp,b);
            temp = Math.max(temp,c);
            max = Math.max(temp,d);



        }
        //System.out.println("min is "+ min);
        ans[1]=(long)min;
        ans[0]=(long)max;
        return ans;

    }

    private static long eval(long a, long b, char op) {
        //System.out.print("op called " + a+" "+b+" "+ op);
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            //System.out.println(" and returns"+(a-b));
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

