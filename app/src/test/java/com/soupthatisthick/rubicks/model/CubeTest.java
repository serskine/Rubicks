package com.soupthatisthick.rubicks.model;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Owner on 6/9/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class CubeTest {

    Logger LOG = Logger.getLogger(CubeTest.class.getSimpleName());

    private static final int rint(int max) {
        return (int) (Math.random() * max);
    }

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

                final boolean expected = (clockwiseDistance==0);
                assertEquals(
                        "" + face + ": clockwise(" + clockwiseDistance + "), counterClockWise(" + counterClockwiseDistance + ")"
                                +   "\n_____ cube original _____"
                                +   "\n" + cube.describe()
                                +   "\n_________ cube 1 ________"
                                +   "\n" + cube1.describe(),
                        expected,
                        cube.equals(cube1)
                );
                assertEquals(
                        "" + face + ": clockwise(" + clockwiseDistance + "), counterClockWise(" + counterClockwiseDistance + ")"
                                +   "\n_____ cube original _____"
                                +   "\n" + cube.describe()
                                +   "\n_________ cube 2 ________"
                                +   "\n" + cube2.describe(),
                        expected,
                        cube.equals(cube2)
                );

            }
        }
    }

    @Test
    public void test_random_moves_reverse_themselves() {
        int numMoves = (int) (Math.random()*10+1);
        Cube cube1 = new Cube();
        Cube cube2 = cube1.copy();

        List<Face> faces = new ArrayList<>();
        List<Rotation> rotations = new ArrayList<>();

        for(int i=0; i<numMoves; i++) {
            Face face = Face.values()[rint(Face.values().length)];
            Rotation rotation = Rotation.values()[rint(Rotation.values().length)];

            LOG.info(" => " + face + ", " + rotation);

            faces.add(face);
            rotations.add(rotation);

            cube2 = cube2.rotate(face, rotation, 1);
        }

        for(int i=faces.size()-1; i>=0; i--) {
            Face face = faces.get(i);
            Rotation rotation = (rotations.get(i)==Rotation.CLOCKWISE) ? Rotation.COUNTER_CLOCKWISE : Rotation.CLOCKWISE;

            LOG.info(" <= " + face + ", " + rotation);
            cube2 = cube2.rotate(face, rotation, 1);
        }

        assertCubesSame(cube1, cube2);
    }

    private void assertCubesSame(Cube expected, Cube observed) {
        assertEquals("Should be the same: "
                + "\n_____ expected _____"
                + "\n" + expected.describe()
                + "\n_____ observed _____"
                + "\n" + observed.describe()
                , true, expected.equals(observed)
        );
    }
}
