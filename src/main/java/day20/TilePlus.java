package day20;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TilePlus {
    @Override
    public String toString() {
        return "TilePlus{" +
                "ID=" + ID +
                ", signatures=" + signatures +
                ", pixels=" + pixels +
                ", tileLookup=" + tileLookup +
                '}';
    }

    int ID;
    // Side -> Signature
    List<HashMap<Integer, Long>> signatures;
    List<List<List<Integer>>> pixels;

    HashMap<Pair<Long,Integer>,List<List<Integer>>> tileLookup;

    public TilePlus(int ID, List<List<Integer>> pixels) {
        this.ID = ID;

        var sig = new ArrayList<HashMap<Integer, Long>>();
        var pixelsAll = new ArrayList<List<List<Integer>>>();

        HashMap<Pair<Long,Integer>,List<List<Integer>>> b = new HashMap<>();

        for (int reverse = 0; reverse <2; reverse++) {
            for (int i = 0; i < 4; i++) {
                HashMap<Integer, Long> a = new HashMap<>();

                for (int j = 0; j < 4; j++) {
                    var s = computeSignaturesForSide(pixels, j);
                    a.put(j, s);

                    b.put(
                            new Pair(s,j),pixels
                    );
                }
                sig.add(a);
                pixelsAll.add(pixels);
                pixels = rotate(pixels);
            }
            pixels = reverseRows(pixels);
        }
        this.signatures = sig;
        this.pixels = pixelsAll;
        this.tileLookup = b;
    }

    private static Long computeSignaturesForSide(List<List<Integer>> tile, int side) {
        return switch (side) {
            case 0 -> signature(tile.get(0));
            case 1 -> signature(tile.stream().map(x -> x.get(x.size() - 1)).collect(Collectors.toList()));
            case 2 -> signature(tile.get(tile.size() - 1));
            case 3 -> signature(tile.stream().map(x -> x.get(0)).collect(Collectors.toList()));
            default -> throw new IllegalStateException("Unexpected value: " + side);
        };
    }

    private static long signature(List<Integer> l) {
        long si = 0L;
        long mul = 1L;
        for (Integer i : l) {
            si += mul * i;
            mul *= 2;
        }
        return si;
    }

    public static List<List<Integer>> rotate(List<List<Integer>> arr) {
        List<List<Integer>> newArr = new ArrayList<>(arr.get(0).size());

        for (int i = arr.get(0).size() - 1; i >= 0; i--) {
            newArr.add(new ArrayList<>(arr.size()));
        }

        for (int r = 0; r < arr.size(); r++) {
            for (int c = 0; c < arr.get(0).size(); c++) {
                newArr.get(r).add(0);
            }
        }

        for (int r = 0; r < arr.size(); r++) {
            for (int c = 0; c < arr.get(0).size(); c++) {
                newArr.get(r).set(c, arr.get(arr.size() - c - 1).get(r));
            }
        }
        return newArr;
    }

    static List<List<Integer>> reverseRows(List<List<Integer>> arr) {
        for (int i = 0; i < arr.size() / 2; i++) {
            int j = arr.size() - 1 - i;
            List<Integer> tmp = new ArrayList<>();
            tmp.addAll(arr.get(j));
            arr.set(j, arr.get(i));
            arr.set(i, tmp);
        }
        return arr;
    }

}
