package com.soupthatisthick.rubicks.model;

import android.util.Log;

import org.junit.Test;

import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 6/9/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class CubeTest {

    Logger LOG = Logger.getLogger(CubeTest.class.getSimpleName());

    @Test
    public void describe() {
        Cube cube = new Cube();

        LOG.info(cube.describe());
    }

    @Test
    public void copy_is_same() {
        Cube cube1 = new Cube();
        Cube cube2 = cube1.copy();

        assertEquals("Should be the same: ", true, cube1.equals(cube1));
        assertEquals("Should be the same: ", true, cube1.equals(cube2));
        assertEquals("Should be the same: ", true, cube2.equals(cube1));
        assertEquals("Should be the same: ", true, cube2.equals(cube2));
    }

    @Test
    public void test_rotations() {
        Cube cube = new Cube();

        for(int clockwiseDistance=0; clockwiseDistance<4; clockwiseDistance++) {
            for (Face face : Face.values()) {
                int counterClockwiseDistance = (4-clockwiseDistance);
                Cube cube1 = cube.rotate(face, Rotation.CLOCKWISE, clockwiseDistance);
                Cube cube2 = cube.rotate(face, Rotation.COUNTER_CLOCKWISE, counterClockwiseDistance);

                assertEquals(
                        "" + face + ": clockwise(" + clockwiseDistance + "), counterClockWise(" + counterClockwiseDistance + ")"
                        +   "\n_____ cube original _____"
                        +   "\n" + cube.describe()
                        +   "\n_________ cube 1 ________"
                        +   "\n" + cube1.describe()
                        +   "\n_________ cube 2 ________"
                        +   "\n" + cube2.describe(),
                        true,
                        cube1.equals(cube2)
                );
            }
        }
    }

}
