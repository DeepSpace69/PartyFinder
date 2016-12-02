package main.java.tests;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Tatka on 29.11.2016.
 */
public class ForTest {
    @Test
    public void someTest(){
        Long l = 1l;
        String s = l.toString();
        String s2 = "1";
        System.out.print(s);
        Assert.assertEquals(s2, s);
    }
}
