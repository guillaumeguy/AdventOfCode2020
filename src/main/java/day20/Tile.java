package day20;

import java.util.List;

public class Tile {
    int ID;
    int side; // 0 = up, 1= right (90deg), ...
    boolean reversed;
    long signature;

    public Tile(int ID, int side, boolean reversed, long signature) {
        this.ID = ID;
        this.side = side;
        this.reversed = reversed;
        this.signature = signature;
    }

    /*
    by = increment of 90deg
     */
    public Tile rotated(List<List<Long>> signatureLookup, int by) {
        int newSide = (4 + this.side + by) % 4;
        int newPos = newSide * 2 + (this.reversed ? 1 : 0);
        Tile t = new Tile(this.ID, newSide, this.reversed, 0L);
        System.out.printf(" %d,  %d , %d \n", t.ID, newSide, newPos);
        Long sig = signatureLookup.get(t.ID).get(newPos);
        t.signature = sig;
        return t;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "ID=" + ID +
                ", side=" + side +
                ", reversed=" + reversed +
                '}';
    }
}
