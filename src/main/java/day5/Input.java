package day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Input {
    public ArrayList<String> readFile(String fileName) throws IOException {

        ArrayList<String> ls = new ArrayList<String>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        while (line != null) {
            ls.add(line);
            line = reader.readLine();
        }
        return ls;
    }
}
