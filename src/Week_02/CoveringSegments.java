package Week_02;
import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {


        while (segments.length>0){
            int minY=100, index=0;
            for (int x=0; x<segments.length;x++){
                if (segments[x].end<minY){
                    index=x;
                }
            }

            ArrayList<Integer> arr = new ArrayList<>();
            for (int x=0; x<segments.length;x++){
                if (segments[x].start<=segments[index].end){
                    int max =0, maxWhile=0;

                    for (int z=segments[index].start; z<segments[index].end;z++){
                        for (Segment seg : segments){
                            if (z>=seg.start&&z<=seg.end){
                                max++;
                            }
                        }
                        //if (max>)
                    }




                    arr.add(x);
                }
            }

            for (int value: arr){

            }

        }
        //write your code here

for (int y =0; y<segments.length; y++){
            int max=0, value=0;
            for (int x=segments[y].start; x<segments[y].end; x++){

            }
        }

        int[] points = new int[2 * segments.length];
        for (int i = 0; i < segments.length; i++) {
            points[2 * i] = segments[i].start;
            points[2 * i + 1] = segments[i].end;
        }
        return points;


    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
