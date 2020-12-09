package day8;

public class Task {
    String op;
    int q;

    public Task(String a, String b) {
        this.op = a.trim();
        this.q = Integer.parseInt(b.trim());
    }

    @Override
    public String toString() {
        return "Task{" +
                "op='" + op + '\'' +
                ", q=" + q +
                '}';
    }

    public void flip(){
        if(this.op.equals("nop")){
            this.op = "jmp";
        }else if (this.op.equals("jmp")){
            this.op = "nop";
        }
    }
}
