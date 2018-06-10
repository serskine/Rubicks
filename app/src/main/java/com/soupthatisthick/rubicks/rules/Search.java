package com.soupthatisthick.rubicks.rules;

import com.soupthatisthick.rubicks.model.Cube;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Owner on 6/10/2018.
 * Copyright of Stuart Marr Erskine, all rights reserved.
 */
public class Search {

    private static final Logger LOG = Logger.getLogger(Search.class.getSimpleName());

    private Queue<Path> fringe = new ArrayDeque<>();
    private Set<Integer> history = new HashSet<>();
    private Map<Integer, Cube> allCubes = new HashMap<>();

    public Path findQuickestRoute(Cube start, Cube goal) throws Exception {
        int goalHashcode = goal.hashCode();

        fringe.clear();
        history.clear();

        allCubes.put(start.hashCode(), start);
        allCubes.put(goalHashcode, goal);

        fringe.add(new Path(start, null, null));

        int numMoves = 0;

        while(!fringe.isEmpty()) {
            Path currentPath = fringe.remove();

            if (currentPath.getNumMoves() > numMoves) {
                numMoves = currentPath.getNumMoves();
                LOG.info(" - searching depth " + numMoves + " moves.");
            }

            Cube currentState = allCubes.get(currentPath.getState());   // already listed
            if (currentPath.getState() != goalHashcode) {
                if (history.add(currentPath.getState())) {
                    for (Action action : Action.values()) {
                        Cube currentCube = allCubes.get(currentState);
                        Cube childState = currentCube.rotate(action, 1);
                        allCubes.put(childState.hashCode(), childState);
                        Path childPath = new Path(childState, action, currentPath);
                        fringe.add(childPath);  // 1st in 1st out means shortest path is always shosen
                    }
                }
            } else {
                return currentPath;
            }
        }
        throw new Exception("There is no path from the start cube to the finish cube!");
    }

}
