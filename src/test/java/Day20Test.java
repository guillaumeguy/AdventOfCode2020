import day12.Instruction;
import day12.Position;
import day20.Puzzle;
import org.javatuples.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day20Test {


    @Test
    public void testRotateDay20() {
        List<List<Integer>> a = new ArrayList<>();
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> expected2 = new ArrayList<>();
        List<List<Integer>> expected3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            a.add(new ArrayList());
            expected.add(new ArrayList<>());
            expected2.add(new ArrayList<>());
            expected3.add(new ArrayList<>());
            expected3.add(new ArrayList<>());
        }


        a.get(0).addAll(Arrays.asList(0, 1, 2));
        a.get(1).addAll(Arrays.asList(3, 4, 5));
        a.get(2).addAll(Arrays.asList(6, 7, 8));


        expected.get(0).addAll(Arrays.asList(6, 3, 0));
        expected.get(1).addAll(Arrays.asList(7, 4, 1));
        expected.get(2).addAll(Arrays.asList(8, 5, 2));

        expected2.get(0).addAll(Arrays.asList(0, 1, 2, 0, 1, 2));
        expected2.get(1).addAll(Arrays.asList(3, 4, 5, 3, 4, 5));
        expected2.get(2).addAll(Arrays.asList(6, 7, 8, 6, 7, 8));

        for (int i = 0; i < 2; i++) {
            expected3.get(i * 3 + 0).addAll(Arrays.asList(0, 1, 2));
            expected3.get(i * 3 + 1).addAll(Arrays.asList(3, 4, 5));
            expected3.get(i * 3 + 2).addAll(Arrays.asList(6, 7, 8));

        }
        List<List<Integer>> rotA = Puzzle.rotate(a);


        sameMatrix(rotA, expected);
        List<List<Integer>> concatA = Puzzle.concat(a, a, 1);
        sameMatrix(concatA, expected2);

        concatA = Puzzle.concat(a, a, 0);

        sameMatrix(concatA, expected3);
    }


    @Test
    public void testRotateDay20Rows() {
        List<List<Integer>> a = new ArrayList<>();
        List<List<Integer>> expected = new ArrayList<>();
        List<List<Integer>> expected2 = new ArrayList<>();
        List<List<Integer>> expected3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            a.add(new ArrayList());
            expected.add(new ArrayList<>());
            expected2.add(new ArrayList<>());
            expected3.add(new ArrayList<>());
            expected3.add(new ArrayList<>());
        }


        a.get(0).addAll(Arrays.asList(0, 1, 2));
        a.get(1).addAll(Arrays.asList(3, 4, 5));
        a.get(2).addAll(Arrays.asList(6, 7, 8));


        List<List<Integer>> b = Puzzle.reverseRows(a);

        expected.get(0).addAll(Arrays.asList(6, 7, 8));
        expected.get(1).addAll(Arrays.asList(3, 4, 5));
        expected.get(2).addAll(Arrays.asList(0, 1, 2));

        b.forEach(System.out::println);

        sameMatrix(b, expected);


    }

    @Test
    public void testFindMonster() {
        Integer[][] arr = new Integer[][]{
                new Integer[]{0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0},
                new Integer[]{1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1},
                new Integer[]{0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0},
                new Integer[]{1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1},
                new Integer[]{0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0},
                new Integer[]{0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1},
                new Integer[]{1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
                new Integer[]{0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1},
                new Integer[]{1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0},
                new Integer[]{1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1},
                new Integer[]{0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1},
                new Integer[]{0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0},
                new Integer[]{0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1},
                new Integer[]{1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1},
                new Integer[]{0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0},
                new Integer[]{1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1},
                new Integer[]{1, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0},
                new Integer[]{0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0},
                new Integer[]{0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                new Integer[]{1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                new Integer[]{1, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0},
                new Integer[]{1, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1},
                new Integer[]{1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1},
                new Integer[]{1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1},
        };

        List<List<Integer>> data = Arrays.asList(arr).stream().map(x -> Arrays.asList(x)).collect(Collectors.toList());

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



        boolean found = Puzzle.isMonster(2, 2, pos, data);
        System.out.println(found);
        Assert.assertTrue(true);

    }

    void sameMatrix(List<List<Integer>> a, List<List<Integer>> b) {
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(0).size(); j++) {
                Assert.assertTrue(a.get(i).get(j).equals(b.get(i).get(j)));
            }
        }
    }


}