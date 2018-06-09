package com.soupthatisthick.rubicks.model;

import java.util.logging.Logger;

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
    Color[] front_right = oneColor(Color.RED);
    Color[] bottom = oneColor(Color.RED);
    Color[] back_left = oneColor(Color.WHITE);
    Color[] back_right = oneColor(Color.BLUE);

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
        newCube.back_right[0] = front_left[6];
        newCube.back_right[1] = front_left[3];
        newCube.back_right[2] = front_left[0];
        newCube.back_right[3] = front_left[7];
        newCube.back_right[5] = front_left[1];
        newCube.back_right[6] = front_left[8];
        newCube.back_right[7] = front_left[5];
        newCube.back_right[8] = front_left[3];

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

    @Override
    public boolean equals(Object other) {
        if (other instanceof Cube) {
            int result = compareTo((Cube) other);
            if (result!=0) {
                LOG.info("cube1.compareTo(cube2) == " + result);
                return false;
            }
            return true;
        } else {
            return super.equals(other);
        }
    }

    private static String lineBreak() {
        return "\n";
    }

    private static String cBreak() {
        return "  ";
    }
    
    private static String et() {
        return "     ";
    }

    private static String ct(Color color) {
        switch(color) {
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

    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append(lineBreak());

        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(back_left[0])).append(ct(back_left[1])).append(ct(back_left[2])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());

        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(back_left[3])).append(ct(back_left[4])).append(ct(back_left[5])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(back_left[6])).append(ct(back_left[7])).append(ct(back_left[8])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());


        sb.append(lineBreak());

        sb.append(ct(front_left[0])).append(ct(front_left[1])).append(ct(front_left[2])).append(cBreak());
        sb.append(ct(top[0])).append(ct(top[1])).append(ct(top[2])).append(cBreak());
        sb.append(ct(back_right[0])).append(ct(back_right[1])).append(ct(back_right[2])).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(ct(front_left[3])).append(ct(front_left[4])).append(ct(front_left[5])).append(cBreak());
        sb.append(ct(top[3])).append(ct(top[4])).append(ct(top[5])).append(cBreak());
        sb.append(ct(back_right[3])).append(ct(back_right[4])).append(ct(back_right[5])).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(ct(front_left[6])).append(ct(front_left[7])).append(ct(front_left[8])).append(cBreak());
        sb.append(ct(top[6])).append(ct(top[7])).append(ct(top[8])).append(cBreak());
        sb.append(ct(back_right[6])).append(ct(back_right[7])).append(ct(back_right[8])).append(cBreak());
        sb.append(lineBreak());

        sb.append(lineBreak());

        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(front_right[0])).append(ct(front_right[1])).append(ct(front_right[2])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(front_right[3])).append(ct(front_right[4])).append(ct(front_right[5])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(front_right[6])).append(ct(front_right[7])).append(ct(front_right[8])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());

        sb.append(lineBreak());

        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(bottom[0])).append(ct(bottom[1])).append(ct(bottom[2])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(bottom[3])).append(ct(bottom[4])).append(ct(bottom[5])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());
        
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(ct(bottom[6])).append(ct(bottom[7])).append(ct(bottom[8])).append(cBreak());
        sb.append(et()).append(et()).append(et()).append(cBreak());
        sb.append(lineBreak());
        
        return sb.toString();
    }
}


