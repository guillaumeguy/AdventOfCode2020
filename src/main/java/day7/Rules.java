package day7;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rules {
    String Color;
    ArrayList<Rule> Contains;

    public Rules(String text) {

        String[] ruleSplit = text.split(" bags contain");

        // First color = first part
        Color = ruleSplit[0].trim();

        // Second part is harder

        ArrayList<Rule> rules = new ArrayList<>();

        //System.out.println(ruleSplit[1]);
        String[] secondTerm = ruleSplit[1].split(" bag(s)?(,)?(.)?");


        for (String term : secondTerm) {
            if(term.contains("no other")){
                continue;
            }
            //System.out.println(term);
            String BagCnt = term.replaceAll("[^0-9]", "").trim(); // returns 123
            int i = Integer.parseInt(BagCnt,10); // extract bag count
            String BagColor = term.replaceAll("[0-9]", "").toLowerCase().trim(); // returns 123
            Rule r = new Rule();
            r.setCnt(i);
            r.setColor(BagColor);
            rules.add(r);
        }
        Contains = rules;
    }

    @Override
    public String toString() {
        return "Rules{" +
                "Color='" + Color + '\'' +
                ", Contains=" + Contains +
                '}';
    }
}
