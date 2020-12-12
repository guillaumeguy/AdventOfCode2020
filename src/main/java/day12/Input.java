package day12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input {
    public ArrayList<Instruction> readFile(String fileName) throws IOException {
        ArrayList<Instruction> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        int cols = 0;
        // We read until we hit a line
        while (line != null) {

            Instruction i = new Instruction(
                    Integer.parseInt(line.substring(1)),
                    line.charAt(0)
            );
            ls.add(i);
            line = reader.readLine();
        }
        return ls;
    }
}


