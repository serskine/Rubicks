package com.soupthatisthick.rubicks.rules;

import com.soupthatisthick.rubicks.model.Face;
import com.soupthatisthick.rubicks.model.Rotation;

import static com.soupthatisthick.rubicks.model.Face.BACK_LEFT;
import static com.soupthatisthick.rubicks.model.Face.BACK_RIGHT;
import static com.soupthatisthick.rubicks.model.Face.BOTTOM;
import static com.soupthatisthick.rubicks.model.Face.FRONT_LEFT;
import static com.soupthatisthick.rubicks.model.Face.FRONT_RIGHT;
import static com.soupthatisthick.rubicks.model.Face.TOP;

/**
 * Created by Owner on 6/10/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public enum Action {
    // Clockwise moves of distance 1
    TOP_CLOCKWISE                   (TOP, Rotation.CLOCKWISE),
    BOTTOM_CLOCKWISE                (BOTTOM, Rotation.CLOCKWISE),
    FRONT_LEFT_CLOCKWISE            (FRONT_LEFT, Rotation.CLOCKWISE),
    FRONT_RIGHT_CLOCKWISE           (FRONT_RIGHT, Rotation.CLOCKWISE),
    BACK_LEFT_CLOCKWISE             (BACK_LEFT, Rotation.CLOCKWISE),
    BACK_RIGHT_CLOCKWISE            (BACK_RIGHT, Rotation.CLOCKWISE),

//    // Counter clockwise moves of distance 1
//    TOP_COUNTER_CLOCKWISE           (TOP, Rotation.COUNTER_CLOCKWISE),
//    BOTTOM_COUNTER_CLOCKWISE        (BOTTOM, Rotation.COUNTER_CLOCKWISE),
//    FRONT_LEFT_COUNTER_CLOCKWISE    (FRONT_LEFT, Rotation.COUNTER_CLOCKWISE),
//    FRONT_RIGHT_COUNTER_CLOCKWISE   (FRONT_RIGHT, Rotation.COUNTER_CLOCKWISE),
//    BACK_LEFT_COUNTER_CLOCKWISE     (BACK_LEFT, Rotation.COUNTER_CLOCKWISE),
//    BACK_RIGHT_COUNTER_CLOCKWISE    (BACK_RIGHT, Rotation.COUNTER_CLOCKWISE),
;
    final private Face face;
    final private Rotation rotation;
    final private String text;

    Action(Face face, Rotation rotation) {
        this.face = face;
        this.rotation = rotation;
        this.text = String.format("ROTATE_" + getFace() + "_1_STEP_" + getRotation());
    }

    public Face getFace() {
        return face;
    }

    public Rotation getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return text;
    }
}
