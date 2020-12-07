import day2.Rule;
import org.junit.Assert;
import org.junit.Test;

public class Day2Test {

    @Test
    public void testDay2Part2() {
        Rule c = new Rule(1,2,'c');
        Assert.assertEquals(c.passwordRulePart2("ccc"),false);
        Assert.assertEquals(c.passwordRulePart2("cdc"),true);

        Rule d = new Rule(3,6,'s');
        Assert.assertEquals(d.passwordRulePart2("ssdsssss"),true);

    }

}


