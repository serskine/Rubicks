package com.soupthatisthick.rubicks.rules;

import com.soupthatisthick.rubicks.model.Cube;

/**
 * Created by Owner on 6/10/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class Path {
    private final int state;
    private final Action prevAction;
    private final Path prevPath;
    private final int numMoves;

    public Path(final Cube state, final Action prevAction, final Path prevPath) {
        this.state = state.hashCode();
        this.prevAction = prevAction;
        this.prevPath = prevPath;
        this.numMoves = (prevPath==null) ? 0 : prevPath.getNumMoves() + 1;
    }

    public int getState() {
        return state;
    }

    public Action getPrevAction() {
        return prevAction;
    }

    public Path getPrevPath() {
        return prevPath;
    }

    @Override
    public String toString() {
        if (getPrevPath()==null) {
            return "start";
        } else {
            return getPrevPath().toString() + "\n -> " + getPrevAction().toString();
        }
    }

    public int getNumMoves() {
        return numMoves;
    }
}
