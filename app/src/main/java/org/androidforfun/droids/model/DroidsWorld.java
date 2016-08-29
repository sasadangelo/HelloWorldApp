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
