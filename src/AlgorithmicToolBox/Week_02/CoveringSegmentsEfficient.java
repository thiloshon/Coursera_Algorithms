package AlgorithmicToolBox.Week_02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Thiloshon on 29-Aug-16.
 */
public class CoveringSegmentsEfficient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextInt();

        ArrayList<Segment> segments = new ArrayList<>();

        for (long i = 0; i < n; i++) {
            long start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }
        ArrayList<Long> points = optimalPoints(segments);
        System.out.println(points.size());
        for (Long point : points) {
            System.out.print(point + " ");
        }

    }


    private static ArrayList<Long> optimalPoints(ArrayList<Segment> segments) {

        ArrayList<Long> points = new ArrayList<>();

        Collections.sort(segments, (segment2, segment1) -> (int)segment2.end - (int)segment1.end);

        for (Segment sg : segments) {
            //System.out.println(sg.start+" " + sg.end);
        }


        while (segments.size() > 0) {

            //System.out.println(segments.size());
            long maximumCoIncidingPointCount = 0;
            long maximumCoIncidingPoint = 0;

            for (long y = segments.get(0).start; y <= segments.get(0).end; y++) {
                int temp = 0;
                int it = 0;
                while (it < segments.size() && segments.get(it).start <= segments.get(0).end) {
                    if (y >= segments.get(it).start && y <= segments.get(it).end) {
                        temp++;
                    }
                    it++;
                }

                if (temp > maximumCoIncidingPointCount) {
                    maximumCoIncidingPointCount = temp;
                    maximumCoIncidingPoint = y;
                }
            }
            //System.out.println("maximumCoIncidingPoint"+maximumCoIncidingPoint);
            points.add(maximumCoIncidingPoint);
            long ind = segments.get(0).end;
            int it = 0;

            while (segments.size() > 0 && segments.get(it).start <= ind) {
                if (maximumCoIncidingPoint >= segments.get(0).start && maximumCoIncidingPoint <= segments.get(0).end) {
                    segments.remove(0);
                    it--;
                }
                it++;
            }

            /*for (long z = segments.size() - 1; z >= 0; z--) {
                if (maximumCoIncidingPoint >= segments.get(z).start && maximumCoIncidingPoint <= segments.get(z).end) {
                    segments.remove(z);
                }
            }*/
        }
        return points;
    }

    private static class Segment {
        long start, end;

        Segment(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}