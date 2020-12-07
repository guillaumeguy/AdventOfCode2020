package day2;

import java.util.HashMap;

public class Rule {
    int from, to;
    char c;

    public Rule(int from1, int to1, char c1) {
        c = c1;
        from = from1;
        to = to1;
    }

    public boolean passwordRulePart1(String s) {

        HashMap<Character, Integer> tracker = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            Character e = s.charAt(i);
            Integer val = tracker.getOrDefault(e, 0);
            tracker.put(e, val + 1);
        }
        return (tracker.getOrDefault(c, 0) >= from) && (tracker.getOrDefault(c, 0) <= to);
    }

    public boolean passwordRulePart2(String s) {
        char t1 = s.charAt(to - 1);
        char t2 = s.charAt(from - 1);

        return (t1 == c) ^ (t2 == c);
    }
}
