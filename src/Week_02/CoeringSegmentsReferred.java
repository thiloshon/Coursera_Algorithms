package Week_02;

import java.util.*;

/**
 * Created by Thiloshon on 29-Aug-16.
 */
public class CoeringSegmentsReferred {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<Segment> segments = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments.add(new Segment(start, end));
        }
        //ArrayList<Integer> points = optimalPoints(segments);
        int[] points= optimalPoints(segments);
        System.out.println(points.length);
        for (Integer point : points) {
            System.out.print(point + " ");
        }

    }

    private static int[] optimalPoints(ArrayList<Segment> segments) {

        // sort the segments according to their right end
        Collections.sort(segments, (segment2, segment1) -> segment2.end - segment1.end);

        List<Integer> listOfPoints = new ArrayList<>();
        int i = 0, n = segments.size();
        while (i < n) {
            int right = segments.get(i).end;
            listOfPoints.add(right);
            // BZ: must fix `right` since i is increasing
            while (i < n && segments.get(i).start <= right && right <= segments.get(i).end)
                i++;
        }

        int[] points = new int[listOfPoints.size()];
        for (int j = 0; j < listOfPoints.size(); j++)
            points[j] = listOfPoints.get(j);
        return points;
    }


    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
