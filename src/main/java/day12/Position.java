package day12;


import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.toDegrees;

public class Position {
    public double x;
    public double y;
    double heading;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.heading = PI / 2; // East
    }

    public int manhattan() {
        return abs((int) this.x) + abs((int) this.y);
    }

    public double dist(Position p) {
        return sqrt(pow((this.x - p.x), 2) + pow(this.y - p.y, 2));
    }


    public void RotateAroundShip(Instruction i, Position ship) {

        assert (Arrays.asList('L', 'R').contains(i.direction));

        double deltaX = this.x - ship.x;
        double deltaY = this.y - ship.y;
        double d = this.dist(ship);

        double angle = i.val * (i.direction == 'L' ? -1 : 1);

        double currentHeading = atan2(deltaX, deltaY); // flipped due to maritime compass angle;
        double rotAngle = toRadians(angle) + currentHeading;

        this.x = ship.x + sin(rotAngle) * d;
        this.y = ship.y + cos(rotAngle) * d;

    }

    public void MoveForward(Instruction i, Position waypoint) {

        double deltaX = waypoint.x - this.x;
        double deltaY = waypoint.y - this.y;

        this.x += deltaX * i.val;
        this.y += deltaY * i.val;

        // the waypoint moves with the ship.
        waypoint.x = this.x + deltaX;
        waypoint.y = this.y + deltaY;
    }

    // Move waypoint in cardinal direction
    public void MoveWaypoint(Instruction i, Position ship) {

        double deltaX = this.x - ship.x;
        double deltaY = this.y - ship.y;

        switch (i.direction) {
            case 'N', 'S' -> {
                this.y = ship.y + (i.direction == 'S' ? -1 : 1) * i.val + deltaY;
                this.x = ship.x + deltaX;
            }
            case 'E', 'W' -> {
                this.x = ship.x + (i.direction == 'W' ? -1 : 1) * i.val + deltaX;
                this.y = ship.y + deltaY;
            }
        }
    }


    public void Move(Instruction i) throws Exception {
        switch (i.direction) {
            case 'F' -> {
                this.x += sin(heading) * i.val;
                this.y += cos(heading) * i.val;
            }
            case 'N', 'S' -> {
                this.y += (i.direction == 'S' ? -1 : 1) * i.val;
            }

            case 'E', 'W' -> {
                this.x += (i.direction == 'W' ? -1 : 1) * i.val;
            }

            // change of direction
            case 'L', 'R' -> {
                double var = (i.direction == 'L' ? -1 : 1) * toRadians(i.val);

                this.heading += var;
                this.heading = this.heading % (2 * PI);
            }

            default -> {
                throw new IllegalArgumentException(String.valueOf(i.direction));
            }
        }
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", heading (deg)= " + toDegrees(heading) +
                '}' + "\n";
    }
}