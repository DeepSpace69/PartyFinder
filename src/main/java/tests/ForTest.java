package main.java.tests;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tatka on 29.11.2016.
 */
public class ForTest {
    @Test
    public void someTest() {
        Set<String> set = new HashSet<>();
        set.add("first");
        set.add(null);
        System.out.println(set.size());
        for (String element : set) {
            System.out.println(element);
        }
        System.out.println(set.contains(null));

    }
}
