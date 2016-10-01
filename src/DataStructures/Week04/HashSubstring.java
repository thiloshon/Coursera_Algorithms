package DataStructures.Week04;

/**
 * Created by Thiloshon on 30-Sep-16.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static long prime = 1000000007;
    private static long multiplier = 1;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrencesEfficient(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }

    static class Data {
        String pattern;
        String text;

        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i + m <= n; ++i) {
            boolean equal = true;
            for (int j = 0; j < m; ++j) {
                if (s.charAt(j) != t.charAt(i + j)) {
                    equal = false;
                    break;
                }
            }
            if (equal)
                occurrences.add(i);
        }
        return occurrences;
    }


    private static List<Integer> getOccurrencesEfficient(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        List<Integer> occurrences = new ArrayList<Integer>();
        long pHash = hashFunc(s);
        long[] H = preComputeHashes(t, s.length());

        for (int i = 0; i <= t.length() - s.length(); i++) {
            if (pHash != H[i]) {
                continue;
            }
            if (t.regionMatches(i, s, 0, m)) {
                occurrences.add(i);
            }
        }

        return occurrences;
    }


    private static long[] preComputeHashes(String t, long P) {
        long[] H = new long[t.length() - (int) P + 1];

        String S = t.substring(t.length() - (int) P, t.length());
        H[t.length() - (int) P] = hashFunc(S);
        long y = 1;
        for (int h = 1; h <= P; h++) {
            y = (y * multiplier) % prime;
        }
        for (int h = t.length() - (int) P - 1; h >= 0; h--) {
            H[h] = ((multiplier * H[h + 1]) + t.charAt(h) - (y * t.charAt(h + (int) P)) % prime) % prime;
        }
        return H;
    }

    private static long hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = ((hash * multiplier) % prime + s.charAt(i)) % prime;
        return hash;
    }
}

