package day18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public List<List<Integer>> readFile(String fileName) throws IOException {

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        List<List<Integer>> rows = new ArrayList<>();

        // We read until we hit a line
        while (line != null) {

            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                row.add(line.charAt(i) == '#' ? 1 : 0);
            }
            rows.add(row);

            line = reader.readLine();
        }

        return rows;

    }
}


