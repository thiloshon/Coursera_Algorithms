package DataStructures.Week01;

/**
 * Created by Thiloshon on 16-Sep-16.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;



class check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        int position = 0;
        for (position=0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
                opening_brackets_stack.push(new Bracket(next,position));
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
                if (opening_brackets_stack.empty()){
                    System.out.println(position+1);
                    return;
                }else{
                    Bracket br = opening_brackets_stack.pop();
                    if (br.Match(next)){
                        continue;
                    }else {
                        System.out.println(position+1);
                        return;
                    }
                }
            }
        }

        if(opening_brackets_stack.empty()){
            System.out.println("Success");
        }else{
            System.out.println(opening_brackets_stack.pop().position+1);
        }

        // Printing answer, write your code here
    }


}

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}
