package AlgorithmicToolBox.Week_04;

import java.util.Random;

/**
 * Created by Thiloshon on 13-Sep-16.
 */
public class RandomString {

    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = '*'; ch <= '+'; ++ch)
            tmp.append(ch);
        for (char ch = '-'; ch <= '-'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
        //for(char g :symbols) System.out.println(g);
    }

    private final Random random = new Random();

    private final char[] buf;

    public RandomString(int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);
        buf = new char[length];
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx){
            buf[idx] = symbols[random.nextInt(symbols.length)%10];
            idx++;
            if (idx>=buf.length-1)break;
            buf[idx] = symbols[random.nextInt(symbols.length)%3+10];

        }

        return new String(buf);
    }
}