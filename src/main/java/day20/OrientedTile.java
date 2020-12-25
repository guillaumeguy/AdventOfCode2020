package day20;

import java.util.List;

public class OrientedTile {
    int ID;
    List<List<Integer>> pixels;

    public OrientedTile(int ID, List<List<Integer>> pixels) {
        this.ID = ID;
        this.pixels = pixels;
    }

    @Override
    public String toString() {
        return "OrientedTile{" +
                "ID=" + ID +
                ", pixels=" + pixels +
                '}';
    }
}
