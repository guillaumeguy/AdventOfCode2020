package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


// 1  = full
// 0  = empty
// -1 = aisle
public class SeatMap {
    int cols;
    int rows;
    ArrayList<int[]> data;
    int changes;

    public SeatMap(ArrayList<int[]> d) {
        this.data = d;
        this.rows = d.size();
        this.cols = d.get(0).length;
        this.changes = 0;
    }

    @Override
    public String toString() {
        return "SeatMap{" +
                "cols=" + cols +
                ", rows=" + rows +
                ", data=" + data.stream().map(x -> Arrays.toString(x)).collect(Collectors.joining(",")) +
                ",\n changes=" + changes +
                '}';
    }

    public boolean empty(Position p) {
        return data.get(p.row)[p.col] == 0;
    }

    public boolean occupied(Position p) {
        return data.get(p.row)[p.col] == 1;
    }

    public boolean aisle(Position p) {
        return data.get(p.row)[p.col] == -1;
    }

    public int status(Position p) {
        return data.get(p.row)[p.col];
    }

    public int occupiedCnt() {
        int cnt = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (occupied(new Position(row, col))) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int newSeatValuePart1(int row, int col) throws Exception {
        Position newPos = new Position(row, col);

        // Aisle stay aisle
        if (aisle(newPos)) {
            return -1;
        }

        ArrayList<Position> neighbors = newPos.Neighbors(this.rows, this.cols);

        // empty with no occupied seat nearby ==> occupied
        if (empty(newPos)) {
            if (neighbors.stream().filter(this::occupied).count() == 0) {
                return 1; // occupied
            } else {
                return 0; // stay empty
            }
        }

        // If >=4 neighbors are = 1 (or Aisle), turn to 0
        if (occupied(newPos)) {
            if (neighbors.stream().filter(this::occupied).count() >= 4) {
                return 0;
            } else {
                return status(newPos); // no change
            }
        }

        System.out.println(data.toString());
        System.out.println(row);
        System.out.println(col);
        throw new Exception("unhandled");
    }

    public int newSeatValuePart2(int row, int col) throws Exception {
        Position newPos = new Position(row, col);

        // Aisle stays aisle
        if (aisle(newPos)) {
            return -1;
        }

        ArrayList<Integer> neighbors = newPos.NeighborsAdvanced(this.data
                , this.rows
                , this.cols);

        // empty with no occupied seat nearby ==> occupied
        if (empty(newPos)) {
            if (neighbors.stream().filter(x -> x == 1).count() == 0) {
                return 1; // occupied
            } else {
                return 0; // stay empty
            }
        }

        // If >=4 neighbors are = 1 (or Aisle), turn to 0
        if (occupied(newPos)) {
            if (neighbors.stream().filter(x -> x == 1).count() >= 5) {
                return 0;
            } else {
                return status(newPos); // no change
            }
        }

        System.out.println(data.toString());
        System.out.println(row);
        System.out.println(col);
        throw new Exception("unhandled");
    }

    public void iterateState() {
        changes = 0;
        ArrayList<int[]> newData = new ArrayList<int[]>();

        for (int row = 0; row < rows; row++) {
            int[] newRow = new int[cols];
            for (int col = 0; col < cols; col++) {
                try {
                    int newSeatVal = newSeatValuePart2(row, col);
                    if (newSeatVal != this.data.get(row)[col]) {
                        changes++;
                    }
                    newRow[col] = newSeatVal;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            newData.add(newRow);
        }
        this.data = newData;
    }

}

class Position {
    int row;
    int col;

    Position(int r, int c) {
        this.row = r;
        this.col = c;
    }

    /*
    Identify neighbors (part 2)
     */
    ArrayList<Integer> NeighborsAdvanced(ArrayList<int[]> data, int rows, int cols) {
        ArrayList<Integer> neigh = new ArrayList<>();

        int[] moves = new int[]{-1, 0, 1};

        for (int up : moves) { // up or down
            for (int right : moves) { // left or right
                if (up != 0 || right != 0) {
                    // (0,0) would mean no movement
                    neigh.add(NeighborsAdvancedHelper(data, rows, cols, up, right));
                }
            }
        }
        return neigh;
    }

    /*
    Iterate until it reaches a limit
    up = {-1,0,1}
    righ = {-1,0,1}
     */
    int NeighborsAdvancedHelper(ArrayList<int[]> data, int rows, int cols, int up, int right) {
        int r = this.row + up;
        int c = this.col + right;

        // a step is up AND right. We iterate until we hit the boundary box
        // or hit a seat (empty or occupied)
        while ((r >= 0) && (r < rows) && (c >= 0) && (c < cols)) {
            if (data.get(r)[c] != -1) {
                return data.get(r)[c];
            }
            r += up;
            c += right;
        }
        // Getting off the map. Unoccupied by definition
        return 0;

    }

    ArrayList<Position> Neighbors(int rows, int cols) {
        ArrayList<Position> pos = new ArrayList<>();

        if (this.row != 0) {
            pos.add(new Position(row - 1, col));

            // diag
            if (this.col != 0) {
                pos.add(new Position(row - 1, col - 1));
            }
            if (this.col != cols - 1) {
                pos.add(new Position(row - 1, col + 1));
            }
        }
        if (this.row != rows - 1) {
            pos.add(new Position(row + 1, col));

            // diag
            if (this.col != 0) {
                pos.add(new Position(row + 1, col - 1));
            }
            if (this.col != cols - 1) {
                pos.add(new Position(row + 1, col + 1));
            }
        }
        if (this.col != 0) {
            pos.add(new Position(row, col - 1));
        }
        if (this.col != cols - 1) {
            pos.add(new Position(row, col + 1));
        }


        //System.out.print("Neighbors: ");
        //System.out.println(pos.toString());

        return pos;


    }

}