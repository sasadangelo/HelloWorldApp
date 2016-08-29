/*
 *  Copyright (C) 2016 Salvatore D'Angelo
 *  This file is part of Droids project.
 *
 *  Droids is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Droids is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License.
 */
package org.androidforfun.droids.model;

/*
 This is the main model class. It is the entry point that describe the DroidsWorld world.
 */
public class DroidsWorld {
    // the possible game status values
    public enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    // the game status
    GameState state = GameState.Ready;

    // the private static instance used to implement the Singleton pattern.
    private static DroidsWorld instance = null;

    private DroidsWorld() {
    }

    public static DroidsWorld getInstance() {
        if (instance == null) {
            instance = new DroidsWorld();
        }
        return instance;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
