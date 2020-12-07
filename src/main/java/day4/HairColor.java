package day4;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class HairColor {


    String sequence;
    boolean valid;

    public HairColor(String h){

        if(h.startsWith("#") && h.length() == 7){
            Pattern p = Pattern.compile("^[-a-z0-9._]+"); //fixed typo
            if(p.matcher(h.substring(1)).matches()){
                valid = true;
            }else{
                System.out.println("INVALID HAIR");
                System.out.println(h);
                valid = false;
            }

/*
            boolean validSeq =  StandardCharsets.US_ASCII.newEncoder().canEncode(h.substring(1));
            if(!validSeq){
                System.out.println("INVALID HAIR");
                System.out.println(h);
            }

 */

        }else{
            valid = false;
        }
    }

}
