import day12.Instruction;
import org.junit.Assert;
import org.junit.Test;

import day12.Position;

public class day12Test {

    @Test
    public void testDay6() {
        Position ship = new Position(170, 38);
        Position waypoint = new Position(180, 42);
        Instruction i = new Instruction(90, 'R');

        waypoint.RotateAroundShip(i, ship);

        Assert.assertEquals( 174,waypoint.x,0.01);
        Assert.assertEquals( 28,waypoint.y,0.01);

    }
}
