package day20;

import org.javatuples.Pair;
import org.junit.Assert;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {
    List<List<List<Integer>>> tiles;
    List<Integer> tilesID; // IDs (0...n ==> ID1, ... IDn)
    List<List<Tile>> puzzleTiles;
    HashMap<Long, List<Tile>> signatures;

    List<List<Long>> signatureLookup;

    List<Tile> connectedCorners;

    int width;
    int height;

    int tileHeight; // Assume square tiles
    List<TilePlus> tilePluses;


    public Puzzle(List<List<List<Integer>>> tiles, List<Integer> tilesID) {
        this.tiles = tiles;
        this.tilesID = tilesID;

        this.tileHeight = tiles.get(0).get(0).size();

        HashMap<Long, List<Tile>> signatures = new HashMap<>();

        List<List<Long>> signatureLookup = new ArrayList<>();
        List<TilePlus> tp = new ArrayList<>();

        for (int i = 0; i < tiles.size(); i++) {
            tp.add(new TilePlus(i, tiles.get(i)));
            List<Long> s = new ArrayList<Long>();
            List<List<Integer>> tile = tiles.get(i);

            List<Pair<Long, Tile>> sig = computeSignaturesForTile(tile, i);
            for (Pair<Long, Tile> o : sig) {
                if (signatures.containsKey(o.getValue0())) {
                    signatures.get(o.getValue0()).add(o.getValue1());
                } else {
                    signatures.put(o.getValue0(), new ArrayList<>(Collections.singletonList(o.getValue1())));
                }
                s.add(o.getValue0());
            }
            signatureLookup.add(s);
        }

        this.tilePluses = tp;
        this.signatures = signatures;
        this.signatureLookup = signatureLookup;

        for (TilePlus tilePlus : this.tilePluses) {
            System.out.println(tilePlus);
        }
    }

    public void sort() {

        int id = 0;
        List<Integer> corners = new ArrayList<>();
        List<Tile> LastConnectedCorners = new ArrayList<>();
        long mul = 1L;
        for (List<Long> tile : signatureLookup) {
            List<Tile> connectedCorners = new ArrayList<>();
            int externalBoundaryCnt = 0;
            for (Long si : tile) {
                if (signatures.get(si).size() == 1) { // external bound
                    externalBoundaryCnt++;
                } else {
                    int finalId = id;
                    connectedCorners.addAll(signatures.get(si).stream().filter(t -> t.ID == finalId).collect(Collectors.toList()));
                }
            }
            if (externalBoundaryCnt == 4) { // 2 external edges X 2 (signature + reverse sign)
                System.out.println(connectedCorners.toString());
                // We want to get the upper left corner
                System.out.printf("tile = %d (%d)\n", id, tilesID.get(id));
                LastConnectedCorners = connectedCorners.stream().filter(x -> !x.reversed).collect(Collectors.toList());

                corners.add(id);
                System.out.println(tilesID.get(id));
                mul *= tilesID.get(id);
            }
            id++;
        }

        List<Tile> firstColumn = buildRow(LastConnectedCorners.get(0));

        this.connectedCorners = LastConnectedCorners;

        System.out.println("------------------------");
        firstColumn.forEach(System.out::println);
        System.out.println("------------------------");

        int rotationAngle90deg = LastConnectedCorners.get(1).side - LastConnectedCorners.get(0).side;


        // Build grid
        List<List<Tile>> puzzle = new ArrayList<>();
        for (Tile ti : firstColumn) {
            List<Tile> row = buildRow(ti.rotated(signatureLookup, rotationAngle90deg));
            // Sometimes, we get the rotation wrong (bad design of algo),
            // So we rotate the other way and check that's OK
            if (row.size() != (int) Math.sqrt(tilesID.size())) {
                row = buildRow(ti.rotated(signatureLookup, -rotationAngle90deg));
                Assert.assertTrue(row.size() == (int) Math.sqrt(tilesID.size()));
            }
            puzzle.add(row);
        }

        for (List<Tile> row : puzzle) {
            System.out.println(row.stream().map(x -> tilesID.get(x.ID)).collect(Collectors.toList()).toString());
        }
        for (List<Tile> row : puzzle) {
            System.out.println(row.stream().map(x -> x.ID).collect(Collectors.toList()).toString());
        }


        /*
        Validation
         */
        for (List<Tile> row : puzzle) {
            // Within each row, the signature checks
            for (int i = 1; i < row.size(); i++) {
                Tile firstTile = row.get(i - 1);
                Tile secondTile = row.get(i);
                Long sig1 = signatureLookup.get(firstTile.ID).get(firstTile.side * 2 + (firstTile.reversed ? 1 : 0));
                Long sig2 = signatureLookup.get(secondTile.ID).get(2 * ((4 + secondTile.side - 2) % 4) + (secondTile.reversed ? 1 : 0));
                Assert.assertEquals(sig1, sig2);
            }
        }

        System.out.printf("SOL = %d\n", mul);
        this.puzzleTiles = puzzle;

        this.width = this.puzzleTiles.get(0).size();
        this.height = this.puzzleTiles.size(); // Assume square
    }


    public static List<List<Integer>> reverseRows(List<List<Integer>> arr) {
        for (int i = 0; i < arr.size() / 2; i++) {
            int j = arr.size() - 1 - i;
            List<Integer> tmp = new ArrayList<>();
            tmp.addAll(arr.get(j));
            arr.set(j, arr.get(i));
            arr.set(i, tmp);
        }
        return arr;
    }

    public List<List<Integer>> removeSeams(List<List<Integer>> arr) {
        List<List<Integer>> newArr = new ArrayList<>();

        System.out.printf("%d,%d\n", arr.size(), arr.get(0).size());

        for (int r = 0; r < arr.size(); r++) {
            List<Integer> newRow = new ArrayList<>();
            if ((r % tileHeight) % 9 != 0) { // skip row-level seams
                for (int c = 0; c < arr.get(r).size(); c++) {
                    if ((c % tileHeight) % 9 != 0) { // skip col-level seams
                        newRow.add(arr.get(r).get(c));
                    }
                }
                newArr.add(newRow);
            }
        }
        return newArr;
    }


    public static List<List<Integer>> reverseCols(List<List<Integer>> arr) {
        for (int i = arr.size() - 1; i >= 0; i--) {
            List<Integer> row = arr.get(i);
            for (int j = 0; j < row.size() / 2; j++) {
                int j1 = row.size() - 1 - j;
                Integer tmp = row.get(j1);
                row.set(j1, row.get(j));
                row.set(j, tmp);
            }
        }
        return arr;
    }


    // https://stackoverflow.com/questions/2941997/how-to-transpose-listlist
    static <T> List<List<T>> transpose(List<List<T>> table) {
        List<List<T>> ret = new ArrayList<List<T>>();
        final int N = table.get(0).size();
        for (int i = 0; i < N; i++) {
            List<T> col = new ArrayList<T>();
            for (List<T> row : table) {
                col.add(row.get(i));
            }
            ret.add(col);
        }
        return ret;
    }

    public List<List<OrientedTile>> combineTiles() throws Exception {

        int ID = connectedCorners.get(0).ID;

        int minVal = Math.min(connectedCorners.get(0).side, connectedCorners.get(1).side);
        int maxVal = Math.max(connectedCorners.get(0).side, connectedCorners.get(1).side);
        var rotneeded = (1 - minVal + 4) % 4;

        System.out.println(connectedCorners);

        if (minVal == 0 && maxVal == 3) {
            rotneeded = 2;
        }

        var pixels = tiles.get(ID);
        for (int i = 0; i < rotneeded; i++) {
            pixels = rotate(pixels);
        }
        pixels = transpose(pixels);

        long rightSig = computeSignaturesForSide(pixels, 1);

        pixels.forEach(System.out::println);
        System.out.println(rightSig);

        List<OrientedTile> firstCol = buildRowPlus(pixels, ID, 1);

        List<List<OrientedTile>> orientedTiles = firstCol.stream().map(x -> buildRowPlus(x.pixels, x.ID, 2)).collect(Collectors.toList());

        // Print each Oriented tiles
        System.out.println(orientedTiles.stream().map(x -> x.stream().map(y -> y.ID).collect(Collectors.toList())).collect(Collectors.toList()));

        return orientedTiles;
    }


    public long findMonsters(List<List<Integer>> combinedTilesWithoutSeams) {

        final String monster = """
                                  # 
                #    ##    ##    ###
                 #  #  #  #  #  #   """;

        List<Pair<Integer, Integer>> pos = new ArrayList<>();
        for (int lineID = 0; lineID < monster.split("\n").length; lineID++) {
            String line = monster.split("\n")[lineID];
            int index = line.indexOf('#');
            while (index >= 0) {
                pos.add(new Pair(lineID, index));
                index = line.indexOf('#', index + 1);
            }
        }

        pos.forEach(System.out::println);

        var roughness = combinedTilesWithoutSeams;
        // We transpose twice to make a copy of the original array
        // if time, do it properly
        roughness = transpose(transpose(combinedTilesWithoutSeams));

        long cnt = 0L;
        for (int flip = 0; flip < 2; flip++) {
            for (int rot = 0; rot < 4; rot++) {
                System.out.printf("flip=%d,rot=%d\n", flip, rot);
                for (List<Integer> row : combinedTilesWithoutSeams) {
                    System.out.println(row.stream().map(x -> x.equals(1) ? "X" : ".").collect(Collectors.toList()).toString());
                }
                for (int r = 0; r < combinedTilesWithoutSeams.size(); r++) {
                    List<Integer> row = combinedTilesWithoutSeams.get(r);
                    for (int c = 0; c < row.size(); c++) {
                        if (isMonster(r, c, pos, combinedTilesWithoutSeams)) {
                            cnt++;
                            System.out.printf("FOUND MONSTER @ (%d,%d)\n", r, c);
                            roughness = removeMonster(r, c, pos, roughness);
                        }
                    }
                }
                combinedTilesWithoutSeams = rotate(combinedTilesWithoutSeams);
                roughness = rotate(roughness);
            }
            combinedTilesWithoutSeams = reverseRows(combinedTilesWithoutSeams);
            roughness = reverseRows(roughness);
        }

        int cntRoughness = 0;
        for (List<Integer> integers : roughness) {
            cntRoughness += integers.stream().reduce(0, Integer::sum);
        }

        System.out.printf(" roughness = %d", cntRoughness);

        return cnt;
    }


    public static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b, int axis) {
        List<List<Integer>> arr = new ArrayList<>();


        if (axis == 0) {
            // n columns should be the same
            assert (a.get(0).size() == b.get(0).size());
            int j = 0;
            for (List<Integer> integers : a) {
                arr.add(new ArrayList<>());
                arr.get(j).addAll(integers);
                j++;
            }
            arr.addAll(b);

        } else {
            // n rows should be the same
            assert (a.size() == b.size());

            int j = 0;
            for (List<Integer> integers : a) {
                arr.add(new ArrayList<>());
                arr.get(j).addAll(integers);
                j++;
            }

            for (int i = 0; i < a.size(); i++) {
                arr.get(i).addAll(b.get(i));
            }
        }
        return arr;
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

    public static Long computeSignaturesForSide(List<List<Integer>> tile, int side) {
        return switch (side) {
            case 0 -> signature(tile.get(0));
            case 1 -> signature(tile.stream().map(x -> x.get(x.size() - 1)).collect(Collectors.toList()));
            case 2 -> signature(tile.get(tile.size() - 1));
            case 3 -> signature(tile.stream().map(x -> x.get(0)).collect(Collectors.toList()));
            default -> throw new IllegalStateException("Unexpected value: " + side);
        };

    }

    public static List<Pair<Long, Tile>> computeSignaturesForTile(List<List<Integer>> tile, int i) {

        ArrayList<Pair<Long, Tile>> acc = new ArrayList<>();

        // First row
        acc.add(new Pair(signature(tile.get(0)), new Tile(i, 0, false, signature(tile.get(0)))));
        // Reverse first row
        acc.add(new Pair(reverseSignature(tile.get(0)), new Tile(i, 0, true, reverseSignature(tile.get(0)))));

        // Right side
        List<Integer> right = tile.stream().map(x -> x.get(x.size() - 1)).collect(Collectors.toList());
        acc.add(new Pair(signature(right), new Tile(i, 1, false, signature(right))));
        acc.add(new Pair(reverseSignature(right), new Tile(i, 1, true, reverseSignature(right))));

        // Last row
        acc.add(new Pair(signature(tile.get(tile.size() - 1)), new Tile(i, 2, false, signature(tile.get(tile.size() - 1)))));
        // Reverse last row
        acc.add(new Pair(reverseSignature(tile.get(tile.size() - 1)), new Tile(i, 2, true, reverseSignature(tile.get(tile.size() - 1)))));

        // Left row
        List<Integer> left = tile.stream().map(x -> x.get(0)).collect(Collectors.toList());
        acc.add(new Pair(signature(left), new Tile(i, 3, false, signature(left))));
        // Reverse left row
        acc.add(new Pair(reverseSignature(left), new Tile(i, 3, true, reverseSignature(left))));

        return acc;
    }

    public static long signature(List<Integer> l) {
        long si = 0L;
        long mul = 1L;
        for (Integer i : l) {
            si += mul * i;
            mul *= 2;
        }
        return si;
    }

    public static long reverseSignature(List<Integer> l) {
        long si = 0L;
        long mul = 1L;
        for (int i = l.size() - 1; i >= 0; i--) {
            si += mul * l.get(i);
            mul *= 2;
        }
        return si;
    }

    // direction = 2 ==> downward
    // direction 1 ==> right-to-left
    public List<OrientedTile> buildRowPlus(List<List<Integer>> startingPoint, int ID, int direction) {
        List<OrientedTile> firstColumn = new ArrayList<>();
        OrientedTile t = new OrientedTile(ID, startingPoint);
        firstColumn.add(t);
        int id = ID;
        Optional<OrientedTile> next = findOppositeNeightbor2(startingPoint, direction, id);
        while (next.isPresent()) {
            firstColumn.add(next.get());
            System.out.printf("t = %s, next = %s\n", t.toString(), next.get().toString());
            t = next.get();
            next = findOppositeNeightbor2(t.pixels, direction, t.ID);
        }
        return firstColumn;
    }


    public List<Tile> buildRow(Tile startingPoint) {
        List<Tile> firstColumn = new ArrayList<Tile>();
        Tile t = startingPoint;
        firstColumn.add(t);
        Optional<Tile> next = findOppositeNeightbor(t);
        while (next.isPresent()) {
            firstColumn.add(next.get());
            System.out.printf("t = %s, next = %s\n", t.toString(), next.get().toString());
            t = next.get();
            next = findOppositeNeightbor(t);
        }
        return firstColumn;
    }

    /*
    Find the matching line. returns the other side of the tile

    |-------|-------|
    | tile 1|tile 2 |
    |-------|-------|

    Side 1 of tile 1 will return side 1 of tile 2
     */
    public Optional<Tile> findOppositeNeightbor(Tile t) {
        List<Tile> otherTiles = signatures.get(t.signature);
        Optional<Tile> matchingTile = otherTiles.stream().filter(tile -> tile.ID != t.ID).findFirst();
        //System.out.printf("Opposite tile of %s is %s\n", t.toString(), matchingTile);

        if (matchingTile.isPresent()) {
            Tile otherSideTile = matchingTile.get().rotated(signatureLookup, 2);
            System.out.printf("Rotated by 2, now becomes %s\n", otherSideTile);
            return Optional.of(otherSideTile);
        } else {
            return Optional.empty();
        }
    }


    public Optional<OrientedTile> findOppositeNeightbor2(List<List<Integer>> pixels, int direction, int notID) {
        long sig = computeSignaturesForSide(pixels, direction);
        List<Integer> otherTiles = signatures.get(sig).stream()
                .map(x -> x.ID)
                .filter(x -> x != notID)
                .collect(Collectors.toList());

        Assert.assertTrue(otherTiles.size() <= 1);
        System.out.printf("otherTiles.size() = %d with sig=%d\n", otherTiles.size(), sig);

        if (otherTiles.size() == 1) {
            int newID = otherTiles.get(0);
            pixels = tilePluses.get(newID).tileLookup.get(new Pair(sig, (direction + 2) % 4));
            System.out.printf("Opposite of %d is %d\n", notID, newID);
            return Optional.of(new OrientedTile(newID, pixels));
        } else {
            return Optional.empty();
        }
    }


    public static List<List<Integer>> removeMonster(int r, int c, List<Pair<Integer, Integer>> pos, List<List<Integer>> roughness) {
        for (Pair<Integer, Integer> offset : pos) {
            roughness.get(r + offset.getValue0()).set(c + offset.getValue1(), 0);
        }
        return roughness;
    }

    public static boolean isMonster(int r, int c, List<Pair<Integer, Integer>> pos, List<List<Integer>> combinedTilesWithoutSeams) {
        int offsetRow = pos.stream().map(Pair::getValue0).max(Integer::compare).get();
        int offsetCol = pos.stream().map(Pair::getValue1).max(Integer::compare).get();

        if (r + offsetRow >= combinedTilesWithoutSeams.size()) {
            return false;
        }
        if (c + offsetCol >= combinedTilesWithoutSeams.get(0).size()) {
            return false;
        }

        for (Pair<Integer, Integer> offset : pos) {

            boolean cond = combinedTilesWithoutSeams.get(r + offset.getValue0()).get(c + offset.getValue1()).equals(1);
            //System.out.printf(" %d,%d is %s\n",r + offset.getValue0(),c + offset.getValue1(),cond);
            if (!cond) {
                return false;
            }
        }
        return true;
    }


}
