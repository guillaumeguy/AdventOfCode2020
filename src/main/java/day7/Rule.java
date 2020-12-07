package day7;

public class Rule {
    String Color;
    int cnt;

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public void setColor(String color) {
        Color = color;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "Color='" + Color + '\'' +
                ", cnt=" + cnt +
                '}';
    }
}
