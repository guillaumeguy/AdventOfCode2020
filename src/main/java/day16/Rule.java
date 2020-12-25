package day16;

public class Rule {
    int from,to;
    public Rule(int a, int b){
        this.from = a ;
        this.to = b;
    }

    public Rule( String[] a) {
        this(Integer.parseInt(a[0]),Integer.parseInt(a[1]));
    }

    public boolean valid(int a){
        return a >= this.from && a <= this.to;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
