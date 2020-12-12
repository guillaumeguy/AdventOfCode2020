package day12;

import java.util.ArrayList;
import java.util.Arrays;


public class Solution {

    public static void main(String[] args) throws Exception {
        String fileName = "data/day12.txt";

        ArrayList<Instruction> values = new Input().readFile(fileName);

        solvePart1(values);
        solvePart2(values);
    }


    public static void solvePart1(ArrayList<Instruction> v) throws Exception {

        Position pos = new Position(0, 0);

        for (Instruction i : v) {
            pos.Move(i);
        }

        System.out.println(pos.toString());
        System.out.println(pos.manhattan());
    }

    public static void solvePart2(ArrayList<Instruction> v) throws Exception {

        Position ship = new Position(0, 0);
        Position waypoint = new Position(10, 1);

        for (Instruction i : v) {
            if (Arrays.asList('W', 'E', 'S', 'N').contains(i.direction)) {
                waypoint.MoveWaypoint(i, ship);
            } else if (i.direction == 'F') {
                ship.MoveForward(i, waypoint);
            } else {
                waypoint.RotateAroundShip(i, ship);
            }
        }

        System.out.println(ship.toString());
        System.out.println(ship.manhattan());
    }
}
