package Week_02;

/**
 * Created by Thiloshon on 29-Aug-16.
 */

import java.util.*;

public class LargestNumber {
    private static String largestNumber(ArrayList a) {


        Collections.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer int1, Integer int2) {
                System.out.println("Accessing " + int1 + " and "+ int2);
                int int1First = Integer.parseInt(Integer.toString(int1).substring(0, 1));
                int int2First = Integer.parseInt(Integer.toString(int2).substring(0, 1));
                if (int1First > int2First) {
                    //System.out.println("in 1, " + int2First +" "+ int1First);
                    return -1;

                } else if (int1First == int2First) {
                    int int1second;
                    try {
                        int1second = Integer.parseInt(Integer.toString(int1).substring(1, 2));
                    } catch (StringIndexOutOfBoundsException e) {
                        int1second = Integer.parseInt(Integer.toString(int1).substring(0, 1));
                    }

                    int int2second;
                    try {
                        int2second = Integer.parseInt(Integer.toString(int2).substring(1, 2));
                    } catch (StringIndexOutOfBoundsException e) {
                        int2second = Integer.parseInt(Integer.toString(int2).substring(0, 1));
                    }

                    if (int1second > int2second) {
                        //System.out.println("in 2" + int2second +" "+ int1second);
                        return -1;
                    } else if (int1second == int2second) {
                        int int1Third;
                        try {
                            int1Third = Integer.parseInt(Integer.toString(int1).substring(2, 3));
                        } catch (StringIndexOutOfBoundsException e) {
                            try {
                                int1Third = Integer.parseInt(Integer.toString(int1).substring(1, 2));
                            } catch (StringIndexOutOfBoundsException e1) {
                                int1Third = Integer.parseInt(Integer.toString(int1).substring(0, 1));
                            }
                        }

                        int int2Third;
                        try {
                            int2Third = Integer.parseInt(Integer.toString(int2).substring(2, 3));
                        } catch (StringIndexOutOfBoundsException e) {
                            try {
                                int2Third = Integer.parseInt(Integer.toString(int2).substring(1, 2));
                            } catch (StringIndexOutOfBoundsException e2) {
                                int2Third = Integer.parseInt(Integer.toString(int2).substring(0, 1));
                            }
                        }
                        if (int1Third > int2Third) {
                            return -1;
                        } else {
                            if (int1 == 1000) {
                                return -1;
                            } else if (int2 == 1000) {
                                return 1;
                            }
                            return 0;
                        }
                    }
                }
                return 1;
            }
        });

        String result = "";
        for (int i = 0; i < a.size(); i++) {
            result+=" ";
            result += a.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.nextInt());
        }
        System.out.println(largestNumber(a));
    }
}


