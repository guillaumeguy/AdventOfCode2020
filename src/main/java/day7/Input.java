package day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Input {
    public ArrayList<Rules> readFile(String fileName) throws IOException {

        ArrayList<Rules> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        // We read until we hit a line
        while (line != null) {
            Rules rules = new Rules(line);
            ls.add(rules);
            line = reader.readLine();
        }
        return ls;
    }

}

