import day6.Input;
import org.junit.Assert;
import org.junit.Test;

public class Day6Test {

    @Test
    public void testDay6() {
        Assert.assertEquals(Input.charToInt('a'), 0);
        Assert.assertEquals(day6.Input.charToInt('z'), 26 - 1);

    }
}


