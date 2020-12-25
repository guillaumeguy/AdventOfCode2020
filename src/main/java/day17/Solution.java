package day17;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {


    public static void main(String[] args) throws IOException {
        String fileName = "data/day17.txt";

        List<List<Integer>> input = new Input().readFile(fileName);

        solvePart1(Arrays.asList(input));
        solvePart2(Arrays.asList(Arrays.asList(input)));
    }


    public static void solvePart1(List<List<List<Integer>>> matrix) {
        int maxX = matrix.get(0).size();
        int maxY = matrix.get(0).get(0).size();
        int maxZ = 1;


        List<Long> results = new ArrayList<>();
        results.add(activeCnt(matrix));

        System.out.printf("cnt = %d\n", activeCnt(matrix));

        for (int round = 0; round < 6; round++) {
            List<List<List<Integer>>> newMatrix = new ArrayList<List<List<Integer>>>();

            for (int z = -1; z <= maxZ; z++) {
                //System.out.printf("%d %d %d\n",maxX,maxY,maxZ);
                List<List<Integer>> newPlane = new ArrayList<>();
                for (int x = -1; x <= maxX; x++) {
                    List<Integer> newRow = new ArrayList<>();
                    for (int y = -1; y <= maxY; y++) {
                        int val = ActiveNeighbors3D(matrix, x, y, z);
                        if (isActive(matrix, x, y, z)) {
                            newRow.add((val == 2) || (val == 3) ? 1 : 0);
                        } else {
                            // not active
                            newRow.add(val == 3 ? 1 : 0);
                        }
                    }
                    newPlane.add(newRow);
                }
                newMatrix.add(newPlane);
            }
            maxX += 2;
            maxY += 2;
            maxZ += 2;
            //printMatrix(newMatrix);
            results.add(activeCnt(newMatrix));
            System.out.printf("(round %d), cnt = %d\n", round + 1, activeCnt(newMatrix));
            // re-assign and restart
            matrix = newMatrix;

        }

        System.out.println(results.toString());

    }

    public static void solvePart2(List<List<List<List<Integer>>>> matrix) {
        int maxX = matrix.get(0).get(0).size();
        int maxY = matrix.get(0).get(0).get(0).size();
        int maxZ = 1;
        int maxW = 1;

        List<Long> results = new ArrayList<>();
        long act = matrix.stream().map(Solution::activeCnt).reduce(0L, Long::sum);
        results.add(act);

        System.out.printf("cnt = %d\n", act);

        for (int round = 0; round < 6; round++) {
            List<List<List<List<Integer>>>> newMatrix = new ArrayList<List<List<List<Integer>>>>();

            for (int w = -1; w <= maxW; w++) {
                List<List<List<Integer>>> new3DPlane = new ArrayList<>();
                for (int z = -1; z <= maxZ; z++) {
                    //System.out.printf("%d %d %d\n",maxX,maxY,maxZ);
                    List<List<Integer>> new2DPlane = new ArrayList<>();
                    for (int x = -1; x <= maxX; x++) {
                        List<Integer> newRow = new ArrayList<>();
                        for (int y = -1; y <= maxY; y++) {
                            int val = ActiveNeighbors4D(matrix, x, y, z, w);
                            if (isActive4D(matrix, x, y, z, w)) {
                                newRow.add((val == 2) || (val == 3) ? 1 : 0);
                            } else {
                                // not active
                                newRow.add(val == 3 ? 1 : 0);
                            }
                        }
                        new2DPlane.add(newRow);
                    }
                    new3DPlane.add(new2DPlane);
                }
                newMatrix.add(new3DPlane);
            }
            maxX += 2;
            maxY += 2;
            maxZ += 2;
            maxW += 2;
            //printMatrix(newMatrix);
            act = newMatrix.stream().map(Solution::activeCnt).reduce(0L, Long::sum);
            results.add(act);
            System.out.printf("(round %d), cnt = %d\n", round + 1, act);
            // re-assign and restart
            matrix = newMatrix;

        }

        System.out.println(results.toString());

    }


    public static boolean isActive(List<List<List<Integer>>> matrix, int X, int Y, int Z) {
        if ((Z >= 0) && (Z < matrix.size())) {
            if ((X >= 0) && (X < matrix.get(Z).size())) {
                if ((Y >= 0) && (Y < matrix.get(Z).get(X).size())) {
                    return matrix.get(Z).get(X).get(Y) == 1;
                }
            }
        }
        return false;
    }

    public static boolean isActive4D(List<List<List<List<Integer>>>> matrix, int X, int Y, int Z, int W) {
        if ((W >= 0) && (W < matrix.size())) {
            return isActive(matrix.get(W), X, Y, Z);
        }
        return false;
    }


    public static void printMatrix(List<List<List<Integer>>> matrix) {

        int z = 0;
        for (List<List<Integer>> lists : matrix) {
            System.out.printf("-------------z=%d-------------\n\n", z);
            for (List<Integer> list : lists) {
                for (Integer i : list) {
                    System.out.printf("%d ", i);
                }
                System.out.print("\n");
            }
            z++;
        }

    }

    public static long activeCnt(List<List<List<Integer>>> matrix) {
        long cnt = 0;
        for (List<List<Integer>> lists : matrix) {
            for (List<Integer> list : lists) {
                for (Integer i : list) {
                    cnt += i;
                }
            }
        }
        return cnt;
    }

    public static int ActiveNeighbors3D(List<List<List<Integer>>> matrix, int X, int Y, int Z) {

        int cnt = 0;
        for (int dZ = Math.max(0, Z - 1); dZ < Math.min(Z + 2, matrix.size()); dZ++) {
            List<List<Integer>> rows = matrix.get(dZ);
            for (int dX = Math.max(0, X - 1); dX < Math.min(X + 2, rows.size()); dX++) {
                List<Integer> row = rows.get(dX);
                for (int dY = Math.max(0, Y - 1); dY < Math.min(Y + 2, row.size()); dY++) {
                    if ((dX != X) || (dY != Y) || (dZ != Z)) { // this is the same (we only want the surrounding 26 cubes)
                        cnt += row.get(dY);
                    }
                }
            }
        }
        return cnt;
    }


    public static int ActiveNeighbors4D(List<List<List<List<Integer>>>> matrix, int X, int Y, int Z, int W) {

        int cnt = 0;
        for (int dW = Math.max(0, W - 1); dW < Math.min(W + 2, matrix.size()); dW++) {
            List<List<List<Integer>>> new3DPlane = matrix.get(dW);
            for (int dZ = Math.max(0, Z - 1); dZ < Math.min(Z + 2, new3DPlane.size()); dZ++) {
                List<List<Integer>> rows = new3DPlane.get(dZ);
                for (int dX = Math.max(0, X - 1); dX < Math.min(X + 2, rows.size()); dX++) {
                    List<Integer> row = rows.get(dX);
                    for (int dY = Math.max(0, Y - 1); dY < Math.min(Y + 2, row.size()); dY++) {
                        if ((dX != X) || (dY != Y) || (dZ != Z) || (dW != W)) { // this is the same (we only want the surrounding 26 cubes)
                            cnt += row.get(dY);
                        }
                    }
                }
            }
        }
        return cnt;
    }


}



