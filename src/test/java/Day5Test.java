import org.junit.Assert;
import org.junit.Test;

public class Day5Test {

    @Test
    public void testDay2Part2() {
        String c = "FBFBBFFRLR";



        Assert.assertEquals(
                day5.Solution.row(c) , 44);


    Assert.assertEquals(
            day5.Solution.col(c) , 5);
}


}


