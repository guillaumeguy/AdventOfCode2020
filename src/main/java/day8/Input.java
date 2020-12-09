package day8;

import day7.Rules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Input {
    public ArrayList<Task> readFile(String fileName) throws IOException {

        ArrayList<Task> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        // We read until we hit a line
        while (line != null) {
            String[] s = line.split(" ");
            Task task = new Task(s[0],s[1]);
            ls.add(task);
            line = reader.readLine();
        }
        return ls;
    }
}



