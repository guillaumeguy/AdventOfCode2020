package day12;

public class Instruction {
    int val;
    char direction;

    public Instruction(int d,char dir){
        this.val = d ;
        this.direction = dir;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "val=" + val +
                ", direction=" + direction +
                '}';
    }
}
