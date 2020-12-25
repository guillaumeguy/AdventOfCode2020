package day14;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {
    public List<Instructions> readFile(String fileName) throws IOException {

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        List<Instructions> ins = new ArrayList<>();

        boolean first = true;
        String mask = "";
        ArrayList<Integer> ls1 = new ArrayList<>();
        ArrayList<Integer> ls2 = new ArrayList<>();

        // We read until we hit a line
        while (line != null) {

            if (line.length() > 30) {

                // Save last mask
                if (!first) {
                    ins.add(new Instructions(mask, ls1, ls2));
                }

                // Reset variables
                ls1 = new ArrayList<>();
                ls2 = new ArrayList<>();

                // parse Mask
                String[] masks = line.split(" ");
                mask = masks[masks.length - 1];
                first = false;
            } else {
                // address / value

                String[] i = line.split(" ");

                ls1.add(
                        Integer.parseInt(i[0].substring(i[0].indexOf('[') + 1, i[0].indexOf(']')))
                );

                ls2.add(Integer.parseInt(i[i.length - 1]));

            }
            line = reader.readLine();
        }
        ins.add(new Instructions(mask, ls1, ls2));
        return ins;
    }
}


