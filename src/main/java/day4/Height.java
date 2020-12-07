package day4;

public class Height {
    int heightCm;
    boolean valid;

    public Height(String h) {
        if (h.endsWith("cm")) {
            heightCm = Integer.parseInt(h.substring(0, h.length() - 2));
            valid = heightCm >= 150 && heightCm <= 193;
        } else if (h.endsWith("in")) {
            int inches = Integer.parseInt(h.substring(0, h.length() - 2));
            heightCm = (int) 2.54 * inches;
            valid = inches >= 59 && inches <= 76;
        } else {

            valid = false;
        }
    }
}
