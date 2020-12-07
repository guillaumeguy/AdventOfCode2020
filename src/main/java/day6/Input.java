package day6;

import com.sun.jdi.CharType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Input {
    public ArrayList<boolean[]> readFile(String fileName) throws IOException {

        // Each question is a char ('a', which is represented by an offseted UNICODE
        // a = 0
        // b = 1
        // ...
        ArrayList<boolean[]> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        // We read until we hit a line
        while (line != null) {
            StringBuilder s = new StringBuilder();
            while (line != null && !line.isEmpty()) {
                String[] segments = line.split(" ");
                for (String segment : segments) {
                    s.append(segment);
                }
                line = reader.readLine();
            }
            boolean[] response = parseResponse(s.toString());
            ls.add(response);
            line = reader.readLine();
        }
        return ls;
    }


    public int readFilePart2(String fileName) throws IOException {

        // Each question is a char ('a', which is represented by an offseted UNICODE
        // a = 0
        // b = 1
        // ...
        ArrayList<boolean[]> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        int cnt = 0;

        while (line != null) { // for each group
            int lineID = 0;
            HashSet<Character> s = new HashSet<Character>();
            while (line != null && !line.isEmpty()) { // for each party in the group

                if (lineID == 0) {
                    for (int i = 0; i < line.length(); i++) {
                        // Add all elements
                        s.add(line.charAt(i));
                    }
                } else {
                    HashSet<Character> other = new HashSet<Character>();
                    for (int i = 0; i < line.length(); i++) {
                        other.add(line.charAt(i));
                    }
                    s.retainAll(other);
                }
                lineID++;
                line = reader.readLine();
            }
            cnt += s.size();
            line = reader.readLine();
        }
        return cnt;
    }

    public static int charToInt(char c) {
        int a = (int) 'a';
        return (int) c - a;
    }

    private boolean[] parseResponse(String response) {
        boolean[] r = new boolean[26];
        for (int i = 0; i < response.length(); i++) {
            r[charToInt(response.charAt(i))] = true;
        }
        return r;
    }
}

