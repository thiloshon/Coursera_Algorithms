package DataStructures.Week04;

/**
 * Created by Thiloshon on 28-Sep-16.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String> elems;
    private ArrayList<String>[] hashList;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }

    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int) hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        switch (query.type) {
            case "add":
                if (!elems.contains(query.s))
                    elems.add(0, query.s);
                break;
            case "del":
                if (elems.contains(query.s))
                    elems.remove(query.s);
                break;
            case "find":
                writeSearchResult(elems.contains(query.s));
                break;
            case "check":
                for (String cur : elems)
                    if (hashFunc(cur) == query.ind)
                        out.print(cur + " ");
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        elems = new ArrayList<>();
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        hashList = new ArrayList[bucketCount];
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQueryEfficient(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    private void processQueryEfficient(Query query) {
        switch (query.type) {
            case "add":
                int tempIndex = hashFunc(query.s);
                if (!(hashList[tempIndex] == null)) {
                    boolean check = false;
                    for (String str : hashList[tempIndex]) {
                        if (str.equals(query.s)) {
                            check = true;
                            break;
                        }
                    }
                    if (!check) {
                        hashList[tempIndex].add(0, query.s);
                    }
                } else {
                    hashList[tempIndex] = new ArrayList<>();
                    hashList[tempIndex].add(query.s);
                }
                break;
            case "del":
                tempIndex = hashFunc(query.s);
                if (!(hashList[tempIndex] == null)) {
                    if (hashList[tempIndex].contains(query.s))
                        hashList[tempIndex].remove(query.s);
                }
                break;
            case "find":
                tempIndex = hashFunc(query.s);
                //if (!(hashList[tempIndex] == null)) {
                try {
                    writeSearchResult(hashList[tempIndex].contains(query.s));
                } catch (NullPointerException r) {
                    writeSearchResult(false);
                }
                break;
            case "check":
                try {
                    for (String cur : hashList[query.ind]) {
                        out.print(cur + " ");
                    }
                } catch (NullPointerException r) {
                }
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }
}
