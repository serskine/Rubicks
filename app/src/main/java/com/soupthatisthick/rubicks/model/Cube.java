package com.soupthatisthick.rubicks.model;

import com.soupthatisthick.rubicks.rules.Action;

import java.util.logging.Logger;

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

    Color[] top = oneColor(Color.ORANGE);
    Color[] front_left = oneColor(Color.GREEN);
    Color[] front_right = oneColor(Color.YELLOW);
    Color[] bottom = oneColor(Color.RED);
    Color[] back_left = oneColor(Color.WHITE);
    Color[] back_right = oneColor(Color.BLUE);

    public Cube() {
        top = oneColor(Color.ORANGE);
        front_left = oneColor(Color.GREEN);
        front_right = oneColor(Color.YELLOW);
        bottom = oneColor(Color.RED);
        back_left = oneColor(Color.WHITE);
        back_right = oneColor(Color.BLUE);
    }

    public Cube(
        Color[] top,
        Color[] front_left,
        Color[] front_right,
        Color[] bottom,
        Color[] back_left,
        Color[] back_right
    ) {
        if (top.length != 9)            throw new RuntimeException("Top must have 9 tiles but has " + top.length +" instead.");
        if (front_left.length != 9)     throw new RuntimeException("Front left must have 9 tiles but has " + front_left.length +" instead.");
        if (front_right.length != 9)    throw new RuntimeException("Front Right must have 9 tiles but has " + front_right.length +" instead.");
        if (bottom.length != 9)         throw new RuntimeException("Bottom must have 9 tiles but has " + bottom.length +" instead.");
        if (back_left.length != 9)      throw new RuntimeException("Back left must have 9 tiles but has " + back_left.length +" instead.");
        if (back_right.length != 9)     throw new RuntimeException("Back right must have 9 tiles but has " + back_right.length +" instead.");

        this.top = top;
        this.front_left = front_left;
        this.front_right = front_right;
        this.bottom = bottom;
        this.back_left = back_left;
        this.back_right = back_right;
    }


    final Cube copy() {
        Cube newCube = new Cube();
        newCube.back_left = copy(back_left);
        newCube.back_right = copy(back_right);
        newCube.top = copy(top);
        newCube.bottom = copy(bottom);
        newCube.front_left = copy(front_left);
        newCube.front_right = copy(front_right);
        return newCube;
    }

    private Cube rotateTopClockwise() {
        Cube newCube = copy();

        // Change the top face colors
        newCube.top[0] = top[6];
        newCube.top[1] = top[3];
        newCube.top[2] = top[0];
        newCube.top[3] = top[7];
        newCube.top[5] = top[1];
        newCube.top[6] = top[8];
        newCube.top[7] = top[5];
        newCube.top[8] = top[3];

        // Front left
        newCube.front_left[2] = front_right[0];
        newCube.front_left[5] = front_right[1];
        newCube.front_left[8] = front_right[2];

        // Front right
        newCube.front_right[0] = back_right[6];
        newCube.front_right[1] = back_right[3];
        newCube.front_right[2] = back_right[0];

        // Back left
        newCube.back_left[6] = front_left[8];
        newCube.back_left[7] = front_left[5];
        newCube.back_left[8] = front_left[2];

        // Back right
        newCube.back_right[0] = back_left[6];
        newCube.back_right[3] = back_left[7];
        newCube.back_right[6] = back_left[8];

        return newCube;
    }

    private Cube rotateFrontLeftClockwise() {
        Cube newCube = copy();

        // Change the face colors
        newCube.front_left[0] = front_left[6];
        newCube.front_left[1] = front_left[3];
        newCube.front_left[2] = front_left[0];
        newCube.front_left[3] = front_left[7];
        newCube.front_left[5] = front_left[1];
        newCube.front_left[6] = front_left[8];
        newCube.front_left[7] = front_left[5];
        newCube.front_left[8] = front_left[3];


        // Front right
        newCube.front_right[0] = top[0];
        newCube.front_right[3] = top[3];
        newCube.front_right[6] = top[6];

        // Top
        newCube.top[0] = back_left[0];
        newCube.top[3] = back_left[3];
        newCube.top[6] = back_left[6];

        // Back left
        newCube.back_left[0] = bottom[0];
        newCube.back_left[3] = bottom[3];
        newCube.back_left[6] = bottom[6];

        // Bottom
        newCube.bottom[0] = front_right[0];
        newCube.bottom[3] = front_right[3];
        newCube.bottom[6] = front_right[6];

        return newCube;
    }

    private Cube rotateBackRightClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.back_right[0] = back_right[6];
        newCube.back_right[1] = back_right[3];
        newCube.back_right[2] = back_right[0];
        newCube.back_right[3] = back_right[7];
        newCube.back_right[5] = back_right[1];
        newCube.back_right[6] = back_right[8];
        newCube.back_right[7] = back_right[5];
        newCube.back_right[8] = back_right[3];

        // Top
        newCube.top[2] = front_right[2];
        newCube.top[5] = front_right[5];
        newCube.top[8] = front_right[8];

        // Front Right
        newCube.front_right[2] = bottom[2];
        newCube.front_right[5] = bottom[5];
        newCube.front_right[8] = bottom[8];

        // Bottom
        newCube.bottom[2] = back_left[2];
        newCube.bottom[5] = back_left[5];
        newCube.bottom[8] = back_left[8];

        // Back Left
        newCube.back_left[2] = top[2];
        newCube.back_left[5] = top[5];
        newCube.back_left[8] = top[8];

        return newCube;

    }

    private Cube rotateFrontRightClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.front_right[0] = front_right[6];
        newCube.front_right[1] = front_right[3];
        newCube.front_right[2] = front_right[0];
        newCube.front_right[3] = front_right[7];
        newCube.front_right[5] = front_right[1];
        newCube.front_right[6] = front_right[8];
        newCube.front_right[7] = front_right[5];
        newCube.front_right[8] = front_right[3];

        // Top
        newCube.top[6] = front_left[6];
        newCube.top[7] = front_left[7];
        newCube.top[8] = front_left[8];

        // Back Right
        newCube.back_right[6] = top[6];
        newCube.back_right[7] = top[7];
        newCube.back_right[8] = top[8];

        // Bottom
        newCube.bottom[2] = back_right[6];
        newCube.bottom[1] = back_right[7];
        newCube.bottom[0] = back_right[8];

        // Front Left
        newCube.front_left[8] = bottom[0];
        newCube.front_left[7] = bottom[1];
        newCube.front_left[6] = bottom[2];

        return newCube;
    }

    private Cube rotateBackLeftClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.back_left[0] = back_left[6];
        newCube.back_left[1] = back_left[3];
        newCube.back_left[2] = back_left[0];
        newCube.back_left[3] = back_left[7];
        newCube.back_left[5] = back_left[1];
        newCube.back_left[6] = back_left[8];
        newCube.back_left[7] = back_left[5];
        newCube.back_left[8] = back_left[3];

        // Top
        newCube.top[0] = back_right[0];
        newCube.top[1] = back_right[1];
        newCube.top[2] = back_right[2];

        // Back Right
        newCube.back_right[0] = bottom[8];
        newCube.back_right[1] = bottom[7];
        newCube.back_right[2] = bottom[6];

        // Bottom
        newCube.bottom[8] = front_left[0];
        newCube.bottom[7] = front_left[1];
        newCube.bottom[6] = front_left[2];

        // Front Left
        newCube.front_left[0] = top[0];
        newCube.front_left[1] = top[1];
        newCube.front_left[2] = top[2];

        return newCube;
    }

    private Cube rotateBottomClockwise() {

        Cube newCube = copy();

        // Change the face colors
        newCube.bottom[0] = bottom[6];
        newCube.bottom[1] = bottom[3];
        newCube.bottom[2] = bottom[0];
        newCube.bottom[3] = bottom[7];
        newCube.bottom[5] = bottom[1];
        newCube.bottom[6] = bottom[8];
        newCube.bottom[7] = bottom[5];
        newCube.bottom[8] = bottom[3];

        // Top
        newCube.front_right[6] = front_left[0];
        newCube.front_right[7] = front_left[3];
        newCube.front_right[8] = front_left[6];

        // Back Right
        newCube.front_left[0] = back_left[3];
        newCube.front_left[3] = back_left[2];
        newCube.front_left[6] = back_left[1];

        // Bottom
        newCube.back_left[3] = back_right[8];
        newCube.back_left[2] = back_right[5];
        newCube.back_left[1] = back_right[3];

        // Front Left
        newCube.back_right[8] = front_right[6];
        newCube.back_right[5] = front_right[7];
        newCube.back_right[3] = front_right[8];

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
        int topDiff = compareFaces(top, other.top);
        int bottomDiff = compareFaces(bottom, other.bottom);
        int frontLeftDiff = compareFaces(front_left, other.front_left);
        int frontRightDiff = compareFaces(front_right, other.front_right);
        int backLeftDiff = compareFaces(back_left, other.back_left);
        int backRightDiff = compareFaces(back_right, other.back_right);

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
        sb.append(colorText(back_left[0])).append(colorText(back_left[1])).append(colorText(back_left[2])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(back_left[3])).append(colorText(back_left[4])).append(colorText(back_left[5])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(back_left[6])).append(colorText(back_left[7])).append(colorText(back_left[8])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());


        sb.append(lineBreak());

        sb.append(colorText(front_left[0])).append(colorText(front_left[1])).append(colorText(front_left[2])).append(columnBreak());
        sb.append(colorText(top[0])).append(colorText(top[1])).append(colorText(top[2])).append(columnBreak());
        sb.append(colorText(back_right[0])).append(colorText(back_right[1])).append(colorText(back_right[2])).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(colorText(front_left[3])).append(colorText(front_left[4])).append(colorText(front_left[5])).append(columnBreak());
        sb.append(colorText(top[3])).append(colorText(top[4])).append(colorText(top[5])).append(columnBreak());
        sb.append(colorText(back_right[3])).append(colorText(back_right[4])).append(colorText(back_right[5])).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(colorText(front_left[6])).append(colorText(front_left[7])).append(colorText(front_left[8])).append(columnBreak());
        sb.append(colorText(top[6])).append(colorText(top[7])).append(colorText(top[8])).append(columnBreak());
        sb.append(colorText(back_right[6])).append(colorText(back_right[7])).append(colorText(back_right[8])).append(columnBreak());
        sb.append(lineBreak());

        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(front_right[0])).append(colorText(front_right[1])).append(colorText(front_right[2])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(front_right[3])).append(colorText(front_right[4])).append(colorText(front_right[5])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(front_right[6])).append(colorText(front_right[7])).append(colorText(front_right[8])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());

        sb.append(lineBreak());

        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(bottom[0])).append(colorText(bottom[1])).append(colorText(bottom[2])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(bottom[3])).append(colorText(bottom[4])).append(colorText(bottom[5])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(colorText(bottom[6])).append(colorText(bottom[7])).append(colorText(bottom[8])).append(columnBreak());
        sb.append(emptyText()).append(emptyText()).append(emptyText()).append(columnBreak());
        sb.append(lineBreak());
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return description().hashCode();
    }
}


