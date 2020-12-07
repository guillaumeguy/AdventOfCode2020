package day2;

import javax.naming.InsufficientResourcesException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) throws IOException {
        Input input = new Input();
        int j = input.readFile();

        System.out.println(j);
    }
}


class Input {

    public int readFile() throws IOException {
        String fileName = "data/day2.txt";
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        int i = 0;
        int j = 0;
        while (line != null) {
            String[] segments = line.split(" ");
            String[] range = segments[0].split("-");
            Rule r = new Rule(Integer.parseInt(range[0]), Integer.parseInt(range[1]), segments[1].charAt(0));
            if (r.passwordRulePart1(segments[2])) {
                i++;
            }
            if(r.passwordRulePart2(segments[2])){
                j++;
            }
            line = reader.readLine();
        }
        return j;
    }
}



