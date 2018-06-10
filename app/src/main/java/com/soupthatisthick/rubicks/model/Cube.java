package com.soupthatisthick.rubicks.model;

import com.soupthatisthick.rubicks.rules.Action;

import java.util.logging.Logger;

import static com.soupthatisthick.rubicks.model.Color.BLUE;
import static com.soupthatisthick.rubicks.model.Color.GREEN;
import static com.soupthatisthick.rubicks.model.Color.ORANGE;
import static com.soupthatisthick.rubicks.model.Color.RED;
import static com.soupthatisthick.rubicks.model.Color.WHITE;
import static com.soupthatisthick.rubicks.model.Color.YELLOW;
import static com.soupthatisthick.rubicks.model.Cube.Text.colorText;
import static com.soupthatisthick.rubicks.model.Cube.Text.columnBreak;
import static com.soupthatisthick.rubicks.model.Cube.Text.emptyText;
import static com.soupthatisthick.rubicks.model.Cube.Text.lineBreak;

/**
 * Created by Owner on 6/9/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class Cube {
    private static final Logger LOG = Logger.getLogger(Cube.class.getSimpleName());

    private static Color[] oneColor(final Color color) {
        return new Color[] {
            color,
            color,
            color,
            color,
            color,
            color,
            color,
            color,
            color
        };
    }
    private static Color[] copy(final Color[] other) {
        return new Color[] {
            other[0],
            other[1],
            other[2],
            other[3],
            other[4],
            other[5],
            other[6],
            other[7],
            other[8]
        };
    }

    Color[] orangeFace = oneColor(ORANGE);
    Color[] greenFace = oneColor(Color.GREEN);
    Color[] yellowFace = oneColor(YELLOW);
    Color[] redFace = oneColor(RED);
    Color[] whiteFace = oneColor(WHITE);
    Color[] blueFace = oneColor(BLUE);

    public Cube() {
        orangeFace = oneColor(ORANGE);
        greenFace = oneColor(Color.GREEN);
        yellowFace = oneColor(YELLOW);
        redFace = oneColor(RED);
        whiteFace = oneColor(WHITE);
        blueFace = oneColor(BLUE);
    }

    public Cube(
        Color[] orangeFace,
        Color[] greenFace,
        Color[] yellowFace,
        Color[] redFace,
        Color[] whiteFace,
        Color[] blueFace
    ) {
        if (orangeFace.length != 9)
            throw new RuntimeException("Top must have 9 tiles but has " + orangeFace.length +" instead.");
        if (greenFace.length != 9)
            throw new RuntimeException("Front left must have 9 tiles but has " + greenFace.length +" instead.");
        if (yellowFace.length != 9)
            throw new RuntimeException("Front Right must have 9 tiles but has " + yellowFace.length +" instead.");
        if (redFace.length != 9)
            throw new RuntimeException("Bottom must have 9 tiles but has " + redFace.length +" instead.");
        if (whiteFace.length != 9)
            throw new RuntimeException("Back left must have 9 tiles but has " + whiteFace.length +" instead.");
        if (blueFace.length != 9)
            throw new RuntimeException("Back right must have 9 tiles but has " + blueFace.length +" instead.");

        if (orangeFace[4] != ORANGE)
            throw new RuntimeException("The " + ORANGE + " face center tile is not " + ORANGE + ".");
        if (greenFace[4] != GREEN)
            throw new RuntimeException("The " + GREEN + " face center tile is not " + GREEN + ".");
        if (yellowFace[4] != YELLOW)
            throw new RuntimeException("The " + YELLOW + " face center tile is not " + YELLOW + ".");
        if (redFace[4] != RED)
            throw new RuntimeException("The " + RED + " face center tile is not " + RED + ".");
        if (whiteFace[4] != WHITE)
            throw new RuntimeException("The " + WHITE + " face center tile is not " + WHITE + ".");
        if (blueFace[4] != BLUE)
            throw new RuntimeException("The " + BLUE + " face center tile is not " + BLUE + ".");

        this.orangeFace = orangeFace;
        this.greenFace = greenFace;
        this.yellowFace = yellowFace;
        this.redFace = redFace;
        this.whiteFace = whiteFace;
        this.blueFace = blueFace;
    }


    final Cube copy() {
        Cube newCube = new Cube();
        newCube.whiteFace = copy(whiteFace);
        newCube.blueFace = copy(blueFace);
        newCube.orangeFace = copy(orangeFace);
        newCube.redFace = copy(redFace);
        newCube.greenFace = copy(greenFace);
        newCube.yellowFace = copy(yellowFace);
        return newCube;
    }

    private Cube rotateTopClockwise() {
        Cube newCube = copy();

        // Change the orangeFace face colors
        newCube.orangeFace[0] = orangeFace[6];
        newCube.orangeFace[1] = orangeFace[3];
        newCube.orangeFace[2] = orangeFace[0];
        newCube.orangeFace[3] = orangeFace[7];
        newCube.orangeFace[5] = orangeFace[1];
        newCube.orangeFace[6] = orangeFace[8];
        newCube.orangeFace[7] = orangeFace[5];
        newCube.orangeFace[8] = orangeFace[3];

        // Front left
        newCube.greenFace[2] = yellowFace[0];
        newCube.greenFace[5] = yellowFace[1];
        newCube.greenFace[8] = yellowFace[2];

        // Front right
        newCube.yellowFace[0] = blueFace[6];
        newCube.yellowFace[1] = blueFace[3];
        newCube.yellowFace[2] = blueFace[0];

        // Back left
        newCube.whiteFace[6] = greenFace[8];
        newCube.whiteFace[7] = greenFace[5];
        newCube.whiteFace[8] = greenFace[2];

        // Back right
        newCube.blueFace[0] = whiteFace[6];
        newCube.blueFace[3] = whiteFace[7];
        newCube.blueFace[6] = whiteFace[8];

        return newCube;
    }

    private Cube rotateFrontLeftClockwise() {
        Cube newCube = copy();

        // Change the face colors
        newCube.greenFace[0] = greenFace[6];
        newCube.greenFace[1] = greenFace[3];
        newCube.greenFace[2] = greenFace[0];
        newCube.greenFace[3] = greenFace[7];
        newCube.greenFace[5] = greenFace[1];
        newCube.greenFace[6] = greenFace[8];
        newCube.greenFace[7] = greenFace[5];
        newCube.greenFace[8] = greenFace[3];


        // Front right
        newCube.yellowFace[0] = orangeFace[0];
        newCube.yellowFace[3] = orangeFace[3];
        newCube.yellowFace[6] = orangeFace[6];

        // Top
        newCube.orangeFace[0] = whiteFace[0];
        newCube.orangeFace[3] = whiteFace[3];
        newCube.orangeFace[6] = whiteFace[6];

        // Back left
        newCube.whiteFace[0] = redFace[0];
        newCube.whiteFace[3] = redFace[3];
        newCube.whiteFace[6] = redFace[6];

        // Bottom
        newCube.redFace[0] = yellowFace[0];
        newCube.redFace[3] = yellowFace[3];
        newCube.redFace[6] = yellowFace[6];

        return newCube;
    }

    private Cube rotateBackRightClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.blueFace[0] = blueFace[6];
        newCube.blueFace[1] = blueFace[3];
        newCube.blueFace[2] = blueFace[0];
        newCube.blueFace[3] = blueFace[7];
        newCube.blueFace[5] = blueFace[1];
        newCube.blueFace[6] = blueFace[8];
        newCube.blueFace[7] = blueFace[5];
        newCube.blueFace[8] = blueFace[3];

        // Top
        newCube.orangeFace[2] = yellowFace[2];
        newCube.orangeFace[5] = yellowFace[5];
        newCube.orangeFace[8] = yellowFace[8];

        // Front Right
        newCube.yellowFace[2] = redFace[2];
        newCube.yellowFace[5] = redFace[5];
        newCube.yellowFace[8] = redFace[8];

        // Bottom
        newCube.redFace[2] = whiteFace[2];
        newCube.redFace[5] = whiteFace[5];
        newCube.redFace[8] = whiteFace[8];

        // Back Left
        newCube.whiteFace[2] = orangeFace[2];
        newCube.whiteFace[5] = orangeFace[5];
        newCube.whiteFace[8] = orangeFace[8];

        return newCube;

    }

    private Cube rotateFrontRightClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.yellowFace[0] = yellowFace[6];
        newCube.yellowFace[1] = yellowFace[3];
        newCube.yellowFace[2] = yellowFace[0];
        newCube.yellowFace[3] = yellowFace[7];
        newCube.yellowFace[5] = yellowFace[1];
        newCube.yellowFace[6] = yellowFace[8];
        newCube.yellowFace[7] = yellowFace[5];
        newCube.yellowFace[8] = yellowFace[3];

        // Top
        newCube.orangeFace[6] = greenFace[6];
        newCube.orangeFace[7] = greenFace[7];
        newCube.orangeFace[8] = greenFace[8];

        // Back Right
        newCube.blueFace[6] = orangeFace[6];
        newCube.blueFace[7] = orangeFace[7];
        newCube.blueFace[8] = orangeFace[8];

        // Bottom
        newCube.redFace[2] = blueFace[6];
        newCube.redFace[1] = blueFace[7];
        newCube.redFace[0] = blueFace[8];

        // Front Left
        newCube.greenFace[8] = redFace[0];
        newCube.greenFace[7] = redFace[1];
        newCube.greenFace[6] = redFace[2];

        return newCube;
    }

    private Cube rotateBackLeftClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.whiteFace[0] = whiteFace[6];
        newCube.whiteFace[1] = whiteFace[3];
        newCube.whiteFace[2] = whiteFace[0];
        newCube.whiteFace[3] = whiteFace[7];
        newCube.whiteFace[5] = whiteFace[1];
        newCube.whiteFace[6] = whiteFace[8];
        newCube.whiteFace[7] = whiteFace[5];
        newCube.whiteFace[8] = whiteFace[3];

        // Top
        newCube.orangeFace[0] = blueFace[0];
        newCube.orangeFace[1] = blueFace[1];
        newCube.orangeFace[2] = blueFace[2];

        // Back Right
        newCube.blueFace[0] = redFace[8];
        newCube.blueFace[1] = redFace[7];
        newCube.blueFace[2] = redFace[6];

        // Bottom
        newCube.redFace[8] = greenFace[0];
        newCube.redFace[7] = greenFace[1];
        newCube.redFace[6] = greenFace[2];

        // Front Left
        newCube.greenFace[0] = orangeFace[0];
        newCube.greenFace[1] = orangeFace[1];
        newCube.greenFace[2] = orangeFace[2];

        return newCube;
    }

    private Cube rotateBottomClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.redFace[0] = redFace[6];
        newCube.redFace[1] = redFace[3];
        newCube.redFace[2] = redFace[0];
        newCube.redFace[3] = redFace[7];
        newCube.redFace[5] = redFace[1];
        newCube.redFace[6] = redFace[8];
        newCube.redFace[7] = redFace[5];
        newCube.redFace[8] = redFace[3];

        // Top
        newCube.yellowFace[6] = greenFace[0];
        newCube.yellowFace[7] = greenFace[3];
        newCube.yellowFace[8] = greenFace[6];

        // Back Right
        newCube.greenFace[0] = whiteFace[3];
        newCube.greenFace[3] = whiteFace[2];
        newCube.greenFace[6] = whiteFace[1];

        // Bottom
        newCube.whiteFace[3] = blueFace[8];
        newCube.whiteFace[2] = blueFace[5];
        newCube.whiteFace[1] = blueFace[3];

        // Front Left
        newCube.blueFace[8] = yellowFace[6];
        newCube.blueFace[5] = yellowFace[7];
        newCube.blueFace[3] = yellowFace[8];

        return newCube;
    }

    private static final int rotationRange(int num) {
        while(num<0) {
            num+=4;
        }
        return num % 4;
    }

    /**
     * This will rotate the specified face clockwise in the specified number of directions
     * @param face is the face we wish to rotate
     * @param num is the number of faces the rotation moves
     * @return a copy of the new {@link Cube}. This is non-destructive
     */
    private Cube rotateClockwise(Face face, int num) {
        final int numRotations = rotationRange(num);
        if (numRotations==0) {
            return copy();
        }
        switch(face) {
            case TOP:
                return rotateTopClockwise().rotateClockwise(face, numRotations-1);
            case FRONT_LEFT:
                return rotateFrontLeftClockwise().rotateClockwise(face, numRotations-1);
            case FRONT_RIGHT:
                return rotateFrontRightClockwise().rotateClockwise(face, numRotations-1);
            case BACK_LEFT:
                return rotateBackLeftClockwise().rotateClockwise(face, numRotations-1);
            case BACK_RIGHT:
                return rotateBackRightClockwise().rotateClockwise(face, numRotations-1);
            case BOTTOM:
                return rotateBottomClockwise().rotateClockwise(face, numRotations-1);
            default:
                throw new RuntimeException("Forgot to consider " + face + " for rotation direction.");
        }
    }


    /**
     * This is the method to use to perform an action on a cube
     * @param face is the {@link Face} indicating what we want to rotate
     * @param rotation is the {@link Rotation} direction we want to rotate the face as if we were facing it
     * @param rotationDistance is the number of faces we will move the rotation by.
     * @return a new {@link Cube}. This method is non-destructive
     */
    public Cube rotate(Face face, Rotation rotation, int rotationDistance) {
        int distance = rotationRange(rotationDistance);
        switch(rotation) {
            case CLOCKWISE:
                return rotateClockwise(face, rotationDistance);
            case COUNTER_CLOCKWISE:
                return rotate(face, Rotation.CLOCKWISE, 4-rotationDistance);
            default:
                throw new RuntimeException("We don't know how to handle rotation direction " + rotation + ".");
        }
    }

    public Cube rotate(Action action, int numTimes) {
        return rotate(action.getFace(), action.getRotation(), numTimes);
    }

    private static int compareFaces(Color[] face1, Color[] face2) {
        for(int i=0; i<8; i++) {
            if (face1[i] != face2[i]) return i;
        }
        return -1;
    }


    public int compareTo(Cube other) {
        int topDiff = compareFaces(orangeFace, other.orangeFace);
        int bottomDiff = compareFaces(redFace, other.redFace);
        int frontLeftDiff = compareFaces(greenFace, other.greenFace);
        int frontRightDiff = compareFaces(yellowFace, other.yellowFace);
        int backLeftDiff = compareFaces(whiteFace, other.whiteFace);
        int backRightDiff = compareFaces(blueFace, other.blueFace);

        return    100000*(topDiff+1)
                + 10000*(bottomDiff+1)
                + 1000*(frontLeftDiff+1)
                + 100*(frontRightDiff+1)
                + 10*(backLeftDiff+1)
                + 1*(backRightDiff+1);
    }

    public boolean isEquiv(Object other) {
        if (other instanceof Cube) {
            int result = compareTo((Cube) other);
            if (result!=0) {
                return false;
            }
            return true;
        } else {
            return super.equals(other);
        }
    }

    public boolean equals(Object other) {
        return (hashCode() == other.hashCode());
    }

    static class Text {
        static String lineBreak() {
            return "\n";
        }

        static String columnBreak() {
            return "  ";
        }

        static String emptyText() {
            return "     ";
        }

        static String colorText(Color color) {
            switch (color) {
                case ORANGE:
                    return "[ o ]";
                case YELLOW:
                    return "[ y ]";
                case BLUE:
                    return "[ b ]";
                case WHITE:
                    return "[ w ]";
                case GREEN:
                    return "[ g ]";
                case RED:
                    return "[ r ]";
                default:
                    throw new RuntimeException("Failed to assign text to color " + color + ".");
            }
        }
    }

    public String description() {
        StringBuilder sb = new StringBuilder();

        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(whiteFace[0])).append(colorText(whiteFace[1])).append(colorText(whiteFace[2])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(whiteFace[3])).append(colorText(whiteFace[4])).append(colorText(whiteFace[5])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(whiteFace[6])).append(colorText(whiteFace[7])).append(colorText(whiteFace[8])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());


        sb.append(lineBreak());

        sb.append(colorText(greenFace[0])).append(colorText(greenFace[1])).append(colorText(greenFace[2])).append(columnBreak());
        sb.append(colorText(orangeFace[0])).append(colorText(orangeFace[1])).append(colorText(orangeFace[2])).append(columnBreak());
        sb.append(colorText(blueFace[0])).append(colorText(blueFace[1])).append(colorText(blueFace[2])).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(colorText(greenFace[3])).append(colorText(greenFace[4])).append(colorText(greenFace[5])).append(columnBreak());
        sb.append(colorText(orangeFace[3])).append(colorText(orangeFace[4])).append(colorText(orangeFace[5])).append(columnBreak());
        sb.append(colorText(blueFace[3])).append(colorText(blueFace[4])).append(colorText(blueFace[5])).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(colorText(greenFace[6])).append(colorText(greenFace[7])).append(colorText(greenFace[8])).append(columnBreak());
        sb.append(colorText(orangeFace[6])).append(colorText(orangeFace[7])).append(colorText(orangeFace[8])).append(columnBreak());
        sb.append(colorText(blueFace[6])).append(colorText(blueFace[7])).append(colorText(blueFace[8])).append(columnBreak());
        sb.append(lineBreak());

        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(yellowFace[0])).append(colorText(yellowFace[1])).append(colorText(yellowFace[2])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(yellowFace[3])).append(colorText(yellowFace[4])).append(colorText(yellowFace[5])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(yellowFace[6])).append(colorText(yellowFace[7])).append(colorText(yellowFace[8])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());

        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(redFace[0])).append(colorText(redFace[1])).append(colorText(redFace[2])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(redFace[3])).append(colorText(redFace[4])).append(colorText(redFace[5])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(redFace[6])).append(colorText(redFace[7])).append(colorText(redFace[8])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return description().hashCode();
    }
}


