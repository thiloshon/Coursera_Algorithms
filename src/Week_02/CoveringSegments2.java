package Week_02;

/**
 * Created by Thiloshon on 04-Jul-16.
 * My Slightly different version of the starter file...But works just fine...
 */
import java.util.*;

public class CoveringSegments2 {

    private static ArrayList<Integer> optimalPoints(ArrayList<Segment> segments) {


        //write your code here
        //int[] points = new int[2 * segments.length];
        ArrayList<Integer> points = new ArrayList<>();


        while (segments.size()>0){
            int leftMostSegment = 1000;
            int leftMostSegmentIndex=0;

            for (int x=0; x<segments.size();x++){
                if(segments.get(x).end<leftMostSegment){
                    leftMostSegment=segments.get(x).end;
                    leftMostSegmentIndex=x;
                }
            }

            int maximumCoIncidingPointCount = 0;
            int maximumCoIncidingPoint =0;

            for (int y = segments.get(leftMostSegmentIndex).start; y<=segments.get(leftMostSegmentIndex).end; y++){
                int temp =0;
                for (Segment seg : segments){
                    /*if (leftMostSegment>=seg.start&&leftMostSegment<=seg.end){
                        temp++;
                    }*/
                    if (y>=seg.start&&y<=seg.end){
                        temp++;
                    }
                }

                if (temp>maximumCoIncidingPointCount){
                    maximumCoIncidingPointCount=temp;
                    maximumCoIncidingPoint=y;

                }
            }

            points.add(maximumCoIncidingPoint);

            /*for (int z=0; z<segments.size();z++){
                if (maximumCoIncidingPoint>segments.get(z).start && maximumCoIncidingPoint<segments.get(z).end){
                    segments.remove(z);
                }
            }*/

            for (int z=segments.size()-1; z>=0;z--){
                if (maximumCoIncidingPoint>=segments.get(z).start && maximumCoIncidingPoint<=segments.get(z).end){
                    segments.remove(z);
                }
            }

            /*int z=0;
            while (segments.size()>0){

                if (maximumCoIncidingPoint>segments.get(z).start && maximumCoIncidingPoint<segments.get(z).end){
                    segments.remove(z);
                }
            }*/

            //System.out.println("Hey I survived");
           // System.out.println("The segments.size is" + segments.size());

            for (Segment seg : segments){
                //System.out.println("Starting Point is" + seg.start);
            }

        }



        //

       /* for (int i = 0; i < segments.length; i++) {
            points[2 * i] = segments[i].start;
            points[2 * i + 1] = segments[i].end;
        }*/
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
        //Segment[] segments = new Segment[n];
        ArrayList<Segment> segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }
        ArrayList<Integer> points = optimalPoints(segments);
        System.out.println(points.size());
        for (Integer point : points) {
            System.out.print(point + " ");
        }

    }
}

