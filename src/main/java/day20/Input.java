package day20;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public List<Integer> tileID(String fileName) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        // We read until we hit a line
        List<Integer> tile = new ArrayList<>();


        while (line != null) {
            if (line.startsWith("Tile")) {
                String numberOnly = line.replaceAll("[^0-9]", "");
                tile.add(Integer.parseInt(numberOnly));
            }
            line = reader.readLine();
        }
        return tile;

    }

    public List<List<List<Integer>>> readFile(String fileName) throws IOException {

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        List<List<List<Integer>>> tiles = new ArrayList<>();

        // We read until we hit a line
        List<List<Integer>> tile = new ArrayList<>();


        while (line != null) {

            if (line.startsWith("Tile")) {
                line = reader.readLine();
            }

            if (line.equals("")) {
                tiles.add(tile);
                reader.readLine(); // Empty line
                line = reader.readLine(); // Tile ...
                tile = new ArrayList<>(); // reset
            }

            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                row.add(line.charAt(i) == '#' ? 1 : 0);
            }
            tile.add(row);

            line = reader.readLine();
        }

        tiles.add(tile);

        return tiles;

    }
}


