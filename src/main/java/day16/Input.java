package day16;

import day14.Instructions;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Input {
    public Triplet<List<List<Rule>>, List<Integer>, List<List<Integer>>> readFile(String fileName) throws IOException {

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        List<List<Rule>> rules = new ArrayList<>();
        List<List<Integer>> tickets = new ArrayList<>();

        List<Integer> myTicket = new ArrayList<>();

        int step = 0;
        // We read until we hit a line
        while (line != null) {

            if (line.trim().equals("")) {
                step++;
                line = reader.readLine();
            }

            if (step == 0) {

                String[] s = line.split(":")[1].split(" ");
                System.out.println(Arrays.toString(s));
                Rule r1 = new Rule(s[1].split("-"));
                Rule r2 = new Rule(s[3].split("-"));

                rules.add(Arrays.asList(r1, r2));


            } else if (step == 1) {
                // your ticket

                List<String> s = Arrays.asList(line.split(","));

                myTicket = s.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());

            } else {
                // other tickets
                List<String> s = Arrays.asList(line.split(","));

                List<Integer> s1 = s.stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
                ;
                tickets.add(s1);

            }
            line = reader.readLine();
        }

        return new Triplet<>(rules, myTicket, tickets);

    }
}


