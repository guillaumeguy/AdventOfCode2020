package day20;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.copy;
import static java.util.Collections.singletonList;


public class Solution {


    public static void main(String[] args) throws IOException {
        String fileName = "data/day20.txt";

        List<List<List<Integer>>> tiles = new Input().readFile(fileName);
        List<Integer> tilesID = new Input().tileID(fileName);

        Puzzle p = solvePart1(tiles, tilesID);
        solvePart2(p);

    }


    public static void solvePart2(Puzzle p) {

        try {
            var OT = p.combineTiles();

            List<List<Integer>> combinedTiles = new ArrayList<>();

            for (int i = 0; i < OT.size(); i++) {
                var col = OT.get(i);
                var data = col.get(0).pixels;
                for (int j = 1; j < OT.size(); j++) {
                    data = Puzzle.concat(data, col.get(j).pixels, 0);
                }
                if (i == 0) {
                    combinedTiles = data;
                } else {
                    combinedTiles = Puzzle.concat(combinedTiles, data, 1);
                }
            }
            combinedTiles.forEach(System.out::println);

            List<List<Integer>> noSeamTiles = p.removeSeams(combinedTiles);

            long cnt = p.findMonsters(noSeamTiles);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Puzzle solvePart1(List<List<List<Integer>>> tiles, List<Integer> tilesID) {

        Puzzle puzzle = new Puzzle(tiles, tilesID);
        puzzle.sort();


        return puzzle;


    }


}



