package day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input {
    public ArrayList<int[]> readFile(String fileName) throws IOException {
        ArrayList<int[]> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        int cols = 0;
        // We read until we hit a line
        while (line != null) {
            if (cols == 0) {
                cols = line.length();
            }
            int[] r = new int[cols];

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == 'L') {
                    r[i] = 0;
                } else if (line.charAt(i) == '.') {
                    r[i] = -1;
                } else {
                    r[i] = 1;
                }
            }
            ls.add(r);
            line = reader.readLine();
        }
        return ls;
    }
}


