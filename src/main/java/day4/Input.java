package day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Input {
    public ArrayList<NorthPoleCredentials> readFile(String fileName) throws IOException {

        ArrayList<NorthPoleCredentials> ls = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        // We read until we hit a line
        while (line != null) {
            ArrayList<String> segmentsPassport = new ArrayList<>();
            while( line != null && ! line.isEmpty()){
                String[] segments = line.split(" ");
                segmentsPassport.addAll(Arrays.asList(segments));
                line = reader.readLine();
            }
            NorthPoleCredentials credentials = parseCredentials(segmentsPassport);
            if(credentials != null){
                ls.add(credentials);
            }
            line = reader.readLine();
        }
        return ls;
    }

    private NorthPoleCredentials parseCredentials(ArrayList<String> fragments){

        HashMap<String,String> cred = new HashMap<>();

        for (String fragment : fragments) {
            String[] keyValue = fragment.split(":");
            cred.put(keyValue[0],keyValue[1]);
        }


        // OK
        if ((cred.size() == 8) || (cred.size() == 7 && !cred.containsKey("cid"))){
            return new NorthPoleCredentials(
                    cred.get("byr"),
                    cred.get("iyr"),
                    cred.get("eyr"),
                    cred.getOrDefault("cid","-1"),
                    cred.get("hgt"),
                    cred.get("hcl"),
                    cred.get("ecl"),
                    cred.get("pid")
            );
        }else{
            System.out.println("---invalid---");
            System.out.println(fragments.toString());
            return null;
        }
    }
}

