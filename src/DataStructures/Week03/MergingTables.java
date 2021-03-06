package DataStructures.Week03;

/**
 * Created by Thiloshon on 24-Sep-16.
 */

import java.io.*;
import java.util.Locale;
import java.util.StringTokenizer;

public class MergingTables {
    private final InputReader reader;
    private final OutputWriter writer;

    public MergingTables(InputReader reader, OutputWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public static void main(String[] args) {
        InputReader reader = new InputReader(System.in);
        OutputWriter writer = new OutputWriter(System.out);
        new MergingTables(reader, writer).runAlternate();
        writer.writer.flush();
    }

    class Table {
        Table parent;
        int rank;
        int numberOfRows;

        Table(int numberOfRows) {
            this.numberOfRows = numberOfRows;
            rank = 0;
            parent = this;
        }

        Table getParent() {
            // find super parent and compress path
            if (parent != this) {
                parent = parent.getParent();
            }
            System.out.print(parent.rank+" ");
            return parent;
        }
    }

    int maximumNumberOfRows = -1;

    void merge(Table destination, Table source) {
        Table realDestination = destination.getParent();
        Table realSource = source.getParent();
        if (realDestination == realSource) {
            return;
        }
        // merge two components here
        // use rank heuristic
        // update maximumNumberOfRows
        if (realDestination.rank > realSource.rank) {
            realSource.parent = realDestination;
            realDestination.numberOfRows = realSource.numberOfRows + realDestination.numberOfRows;
            if (realDestination.numberOfRows > maximumNumberOfRows) {
                maximumNumberOfRows = realDestination.numberOfRows;
            }
        } else {
            realDestination.parent = realSource;
            if (realDestination.rank == realSource.rank) {
                realSource.rank++;
            }
            realSource.numberOfRows = realSource.numberOfRows + realDestination.numberOfRows;
            if (realSource.numberOfRows > maximumNumberOfRows) {
                maximumNumberOfRows = realSource.numberOfRows;
            }
        }
    }

    public void run() {
        int n = reader.nextInt();
        int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = reader.nextInt();
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 0; i < m; i++) {
            int destination = reader.nextInt() - 1;
            int source = reader.nextInt() - 1;
            merge(tables[destination], tables[source]);
            writer.printf("%d\n", maximumNumberOfRows);
        }
    }

    public void runAlternate() {
        int n = 61;
        //int m = reader.nextInt();
        Table[] tables = new Table[n];
        for (int i = 0; i < n; i++) {
            int numberOfRows = 1;
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 1; i <= 30; i++) {
            int destination = i;
            int source = 2*i;
            merge(tables[destination], tables[source]);
            //writer.printf("%d\n", maximumNumberOfRows);
        }
        for (int i = 1; i <= 20; i++) {
            int destination = i;
            int source = 3*i;
            merge(tables[destination], tables[source]);
            //writer.printf("%d\n", maximumNumberOfRows);
        }
        for (int i = 1; i <= 12; i++) {
            int destination = i;
            int source = 5*i;
            merge(tables[destination], tables[source]);
            //writer.printf("%d\n", maximumNumberOfRows);
        }
        System.out.println("Following is trace back ");
        for(Table tb: tables){
            tb.getParent();
            System.out.println("");
        }
    }

    public void runAlternate2() {
        int n = 61;
        //int m = reader.nextInt();
        Table[] tables = new Table[n+1];
        for (int i = 0; i <= n; i++) {
            int numberOfRows = 1;
            tables[i] = new Table(numberOfRows);
            maximumNumberOfRows = Math.max(maximumNumberOfRows, numberOfRows);
        }
        for (int i = 1; i <= 30; i++) {
            int destination = i;
            int source = 2*1;
            merge(tables[destination], tables[source]);
            //writer.printf("%d\n", maximumNumberOfRows);
        }
        for (int i = 1; i <= 20; i++) {
            int destination = i;
            int source = 3*i;
            merge(tables[destination], tables[source]);
            //writer.printf("%d\n", maximumNumberOfRows);
        }
        for (int i = 1; i <= 12; i++) {
            int destination = i;
            int source = 5*i;
            merge(tables[destination], tables[source]);
            //writer.printf("%d\n", maximumNumberOfRows);
        }
        /*merge(tables[2], tables[10]);
        merge(tables[7], tables[5]);
        merge(tables[6], tables[1]);
        merge(tables[3], tables[4]);
        merge(tables[5], tables[11]);
        merge(tables[7], tables[8]);
        merge(tables[7], tables[3]);
        merge(tables[12], tables[2]);
        merge(tables[9], tables[6]);*/

        //System.out.println(tables[6].getParent());


        System.out.println("Following is trace back ");
        for(Table tb: tables){
            tb.getParent();
            //System.out.println("");
        }
        /*for(Table tb: tables){
            tb.getParent();
            System.out.println("");
        }*/
    }


    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class OutputWriter {
        public PrintWriter writer;

        OutputWriter(OutputStream stream) {
            writer = new PrintWriter(stream);
        }

        public void printf(String format, Object... args) {
            writer.print(String.format(Locale.ENGLISH, format, args));
        }
    }
}
