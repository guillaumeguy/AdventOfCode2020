package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Input {

    public ArrayList<Integer> readFile(String fileName) throws IOException {

        ArrayList<Integer> ls = new ArrayList<Integer>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        while (line != null) {
            int i = Integer.parseInt(line);
            ls.add(i);
            line = reader.readLine();
        }
        return ls;
    }
}
