package com.soupthatisthick.rubicks.model;

import com.soupthatisthick.rubicks.rules.Action;
import com.soupthatisthick.rubicks.rules.Path;
import com.soupthatisthick.rubicks.rules.Search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.soupthatisthick.rubicks.model.Color.BLUE;
import static com.soupthatisthick.rubicks.model.Color.GREEN;
import static com.soupthatisthick.rubicks.model.Color.ORANGE;
import static com.soupthatisthick.rubicks.model.Color.RED;
import static com.soupthatisthick.rubicks.model.Color.WHITE;
import static com.soupthatisthick.rubicks.model.Color.YELLOW;
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

        LOG.info(cube.description());
    }

    @Test
    public void copy_is_same() {
        Cube cube1 = new Cube();
        Cube cube2 = cube1.copy();

        assertEquals("Should be the same: ", true, cube1.isEquiv(cube1));
        assertEquals("Should be the same: ", true, cube1.isEquiv(cube2));
        assertEquals("Should be the same: ", true, cube2.isEquiv(cube1));
        assertEquals("Should be the same: ", true, cube2.isEquiv(cube2));
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
                        +   "\n" + cube.description()
                        +   "\n_________ cube 1 ________"
                        +   "\n" + cube1.description()
                        +   "\n_________ cube 2 ________"
                        +   "\n" + cube2.description(),
                        true,
                        cube1.isEquiv(cube2)
                );

                final boolean expected = (clockwiseDistance==0);
                assertEquals(
                        "" + face + ": clockwise(" + clockwiseDistance + "), counterClockWise(" + counterClockwiseDistance + ")"
                                +   "\n_____ cube original _____"
                                +   "\n" + cube.description()
                                +   "\n_________ cube 1 ________"
                                +   "\n" + cube1.description(),
                        expected,
                        cube.isEquiv(cube1)
                );
                assertEquals(
                        "" + face + ": clockwise(" + clockwiseDistance + "), counterClockWise(" + counterClockwiseDistance + ")"
                                +   "\n_____ cube original _____"
                                +   "\n" + cube.description()
                                +   "\n_________ cube 2 ________"
                                +   "\n" + cube2.description(),
                        expected,
                        cube.isEquiv(cube2)
                );

            }
        }
    }

    @Test
    public void test_moves_reverse_themselves() {
        int numMoves = (int) (Math.random()*10+1);
        Cube cube1 = new Cube();
        Cube cube2 = cube1.copy();

        assertCubesSame("After copy: ", cube1, cube2);

        for(int distance=-4; distance <=4; distance++) {
            for (Face face : Face.values()) {
                for (Rotation rotation : Rotation.values()) {
                    Rotation reverseRotation = (rotation == Rotation.CLOCKWISE) ? Rotation.COUNTER_CLOCKWISE : Rotation.CLOCKWISE;
                    cube2 = cube1.copy();
                    cube2 = cube1.rotate(face, rotation, distance);
                    cube2 = cube2.rotate(face, reverseRotation, distance);
                    assertCubesSame(
                            "Face(" + face
                                    + "), Rotation(" + rotation + ", " + reverseRotation
                                    + "), Distance(" + distance + ")",
                            cube1, cube2);

                }
            }
        }
    }

    @Test
    public void solve_simple() throws Exception {
        Cube start = new Cube();
        Cube goal = start.rotate(Action.TOP_CLOCKWISE, 1);

        Search search = new Search();
        Path path = search.findQuickestRoute(start, goal);

        LOG.info(path.toString());
    }

    @Test
    public void solve_random() throws Exception {
        Cube start = new Cube();
        Cube goal = start.copy();

        List<Action> order = new ArrayList<>();
        for(int i=0; i<7; i++) {
            Action action = Action.values()[rint(Action.values().length)];
            order.add(action);
            goal = goal.rotate(action, 1);
        }

        Search search = new Search();
        Path path = search.findQuickestRoute(start, goal);

        LOG.info(path.toString());
    }

    @Test
    public void solve() throws Exception {
        Cube start = new Cube(
            new Color[] {BLUE, BLUE, ORANGE, ORANGE, ORANGE, GREEN, WHITE, BLUE, ORANGE},
            new Color[] {BLUE, BLUE, ORANGE, WHITE, GREEN, GREEN, GREEN, ORANGE, BLUE},
            new Color[] {RED, ORANGE, YELLOW, YELLOW, YELLOW, WHITE, YELLOW, RED, RED},
            new Color[] {RED, BLUE, GREEN, RED, RED, GREEN, RED, YELLOW, ORANGE},
            new Color[] {YELLOW, RED, GREEN, YELLOW, WHITE, YELLOW, WHITE, WHITE, GREEN},
            new Color[] {WHITE, GREEN, YELLOW, WHITE, BLUE, RED, BLUE, ORANGE, WHITE}
        );
        Cube goal = new Cube();

        Search search = new Search();
        Path path = search.findQuickestRoute(start, goal);

        LOG.info(path.toString());
    }

    private void assertCubesSame(final String externalMessage, Cube expected, Cube observed) {
        final String message = "Should be the same: \n" + externalMessage + "\n"
            + appendLines(expected.description(), observed.description(), "    ");
        assertEquals(message, true, expected.isEquiv(observed));
    }

    private String appendLines(String desc1, String desc2, String between) {
        String[] tokens1 = desc1.split("\n");
        String[] tokens2 = desc2.split("\n");

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Math.max(tokens1.length, tokens2.length); i++) {
            if (i < tokens1.length) {
                sb.append(tokens1[i]).append(between);
            }
            if (i < tokens2.length) {
                sb.append(tokens2[i]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
