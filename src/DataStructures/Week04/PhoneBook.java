package DataStructures.Week04;

/**
 * Created by Thiloshon on 28-Sep-16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private List<Contact> contacts = new ArrayList<>();
    private Contact[] conts = new Contact[10000000];

    public static void main(String[] args) {
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
        String type = in.next();
        int number = in.nextInt();
        if (type.equals("add")) {
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
        System.out.println(response);
    }

    public void processQueries() {
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i)
            processQueryEfficint(readQuery());
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private void processQuery(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            for (Contact contact : contacts)
                if (contact.number == query.number) {
                    contact.name = query.name;
                    wasFound = true;
                    break;
                }
            // otherwise, just add it
            if (!wasFound)
                contacts.add(new Contact(query.name, query.number));
        } else if (query.type.equals("del")) {
            for (Iterator<Contact> it = contacts.iterator(); it.hasNext(); )
                if (it.next().number == query.number) {
                    it.remove();
                    break;
                }
        } else {
            String response = "not found";
            for (Contact contact : contacts)
                if (contact.number == query.number) {
                    response = contact.name;
                    break;
                }
            writeResponse(response);
        }
    }

    private void processQueryEfficint(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            try{
                if (conts[query.number] != null) {
                    conts[query.number].name = query.name;
                    wasFound = true;
                }
            }catch (IndexOutOfBoundsException e){
                //System.out.println("df");
            }
            // otherwise, just add it
            if (!wasFound)
                conts[query.number] = new Contact(query.name, query.number);

        } else if (query.type.equals("del")) {
            try{
                conts[query.number]=null;
            }catch (IndexOutOfBoundsException w){

            }
        } else {
            String response = "not found";
            try{
                response = conts[query.number].name;
            }catch (IndexOutOfBoundsException   | NullPointerException r){

            }
            writeResponse(response);
        }
    }
}


  /*  private void processQueryEfficint(Query query) {
        if (query.type.equals("add")) {
            // if we already have contact with such number,
            // we should rewrite contact's name
            boolean wasFound = false;
            try{
                if (contacts.get(query.number) != null) {
                    contacts.get(query.number).name = query.name;
                    wasFound = true;
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("df");
            }
            // otherwise, just add it
            if (!wasFound)
                contacts.add(query.number, new Contact(query.name, query.number));

        } else if (query.type.equals("del")) {
            try{
                contacts.remove(query.number);
            }catch (IndexOutOfBoundsException w){

            }
        } else {
            String response = "not found";
            try{
                response = contacts.get(query.number).name;
            }catch (IndexOutOfBoundsException e){

            }
            writeResponse(response);
        }
    }*/