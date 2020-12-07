package day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Input {
    public ArrayList<boolean[]> readFile(String fileName) throws IOException {

        ArrayList<boolean[]> ls = new ArrayList<boolean[]>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();


        while (line != null) {
            boolean[] row =  new boolean[line.length()];

            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) == '.'){
                    row[i] = false; // tree
                }else{
                    row[i] = true;
                }
            }
            ls.add(row);
            line = reader.readLine();
        }
        return ls;
    }
}
