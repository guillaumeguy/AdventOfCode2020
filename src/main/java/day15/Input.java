package day15;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public List<Integer> readFile(String fileName) throws IOException {

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        List<Integer> ins = new ArrayList<>();
        // We read until we hit a line
        while (line != null) {
            ins.add(Integer.parseInt(line));
            line = reader.readLine();
        }
        return ins;
    }
}


