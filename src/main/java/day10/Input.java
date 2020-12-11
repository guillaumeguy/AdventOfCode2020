package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input {
    public ArrayList<Long> readFile(String fileName) throws IOException {
        ArrayList<Long> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        // We read until we hit a line
        while (line != null) {
            ls.add(Long.parseLong(line));
            line = reader.readLine();
        }
        return ls;
    }
}



