package day14;

import java.util.List;

public class Instructions {
    String mask;
    List<Integer> addresses;
    List<Integer> values;

    public Instructions(String a, List<Integer> b, List<Integer> c) {
        this.mask = a;
        this.addresses = b;
        this.values = c;
    }

    @Override
    public String toString() {
        return "Instructions{" +
                "mask='" + mask + '\'' +
                ", addresses=" + addresses +
                ", values=" + values +
                '}';
    }
}
