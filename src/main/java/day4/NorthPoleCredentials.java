package day4;

import java.util.*;

public class NorthPoleCredentials {
    int byr, iyr, eyr, cid;
    String hgt; // height
    String hcl, ecl;
    String pid;

    public NorthPoleCredentials(
            String individualByr
            , String individualIyr
            , String individualEyr
            , String individualCid
            , String individualhgt
            , String individualhcl
            , String individualecl
            , String individualpid

    ) {
        byr = Integer.parseInt(individualByr);
        iyr = Integer.parseInt(individualIyr);
        eyr = Integer.parseInt(individualEyr);
        cid = Integer.parseInt(individualCid);
        hgt = individualhgt;
        hcl = individualhcl;
        ecl = individualecl;
        pid = individualpid;
    }

    public boolean isValid() {
        Height h = new Height(hgt);
        HairColor hc = new HairColor(hcl);
        HashSet<String> validEyeColors = new HashSet<String>(Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth"));
        boolean[] rules = new boolean[]{
                byr >= 1920 && byr <= 2002,
                iyr >= 2010 && iyr <= 2020,
                eyr >= 2020 && eyr <= 2030,
                h.valid,
                hc.valid,
                validEyeColors.contains(ecl),
                pid.length() == 9 && parseableLong(pid),
        };

        boolean validPassport = true;
        int i = 0;
        for (boolean rule : rules) {
            validPassport = rule && validPassport;
            i++;
        }
        if (!validPassport) {
            System.out.println(this.toString());
        }
        return validPassport;
    }

    boolean parseableLong(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}


