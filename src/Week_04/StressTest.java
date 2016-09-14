package Week_04;

import java.util.Random;

/**
 * Created by Thiloshon on 13-Sep-16.
 */
public class StressTest {
    public static void main(String[] args) {

        while (true){
            int num = (int)(Math.random()*30);
            if (num<3)continue;

            RandomString r = new RandomString(num);
            String exp = (r.nextString());

            exp = exp.trim();

            long num1 = getMaximValue(exp);
            long num2 = parenthesis(exp);

            if (num1 == num2) System.out.println( " Okay " + exp );
            else {
                System.out.println(exp+" " + num1+ " " + num2);
                break;
            }

        }




    }





    //-----------------------------MY ALGO----------------------------------

    private static long getMaximValue(String exp) {
        //write your code here


        char[] charArray = exp.toCharArray();

        long[][] maxima = new long[charArray.length/2+2][charArray.length/2+2];
        long[][] minima = new long[charArray.length/2+2][charArray.length/2+2];


        /*for(int x =0; x<=charArray.length-1;x++){
            System.out.println(charArray[x]);
        }
        System.out.println("EOF");*/


        int count =0;
        //System.out.println(exp);
        for (int x =1; x<((charArray.length/2)+2); x++){
            /*System.out.print(x+" ");
            System.out.print(charArray.length+" ");
            System.out.println(((charArray.length/2)+2));*/
            maxima[x][x]=Character.getNumericValue(charArray[(2*x)-2]);
            minima[x][x]=Character.getNumericValue(charArray[(2*x)-2]);
        }

        for(int s=1; s<=charArray.length/2+1;s++){
            for(int i =1;i<charArray.length/2+2-s;i++){
                int j = i+s;
                //System.out.println("i:j = "+ i+" : " +j);
                long[] temp = minAndMax(i,j,charArray, maxima,minima);
                maxima[i][j]=(int)temp[0];
                minima[i][j]=(int)temp[1];



            }
            /*for (int x =1; x<maxima.length; x++){
                for(int y =1; y<maxima.length;y++){
                    System.out.print(minima[x][y]+" ");
                }
                System.out.println("");
            }*/

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

    //---------------------------END OF MY ALGO--------------------------------------------------


    //----------------------------WORKING ALGO----------------------------------------------------

    private static long parenthesis(String expression) {
        resultStorage = new long[100][100][100];
        long[] results = parenthesis(expression, 0, 0);
        //System.out.println(results[1]);
        return results[1];
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
    //--------------------------------------------------END OF WORKING ALGO-------------------------
}