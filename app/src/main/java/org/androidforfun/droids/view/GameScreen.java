/*
 *  Copyright (C) 2016 Salvatore D'Angelo
 *  This file is part of Droids project.
 *  This file derives from the Mr Nom project developed by Mario Zechner for the Beginning Android
 *  Games book (chapter 6).
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
package org.androidforfun.droids.view;

import android.util.Log;

import org.androidforfun.droids.model.DroidsWorld;
import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Game;
import org.androidforfun.framework.Input.TouchEvent;
import org.androidforfun.framework.Screen;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/*
 * This class represents the game screen. The processing and rendering depends on the game state managed
 * by State pattern. The update and draw method are delegated to:
 *    GamePause.update, GamePause.draw
 *    GameReady.update, GameReady.draw
 *    GameRunning.update, GameRunning.draw
 *    GameOver.update, GameOver.draw
 *
 * depending on the status of the game.
 *
 * @author Salvatore D'Angelo
 */
public class GameScreen implements Screen {
    private static final String LOG_TAG = "Droids.GameScreen";
    Map<DroidsWorld.GameState, GameState> states = new EnumMap<>(DroidsWorld.GameState.class);

    public GameScreen() {
        Log.i(LOG_TAG, "constructor -- begin");

        states.put(DroidsWorld.GameState.Paused, new GamePaused());
        states.put(DroidsWorld.GameState.Ready, new GameReady());
        states.put(DroidsWorld.GameState.Running, new GameRunning());
        states.put(DroidsWorld.GameState.GameOver, new GameOver());
    }

    /*
     * The update method is delegated to:
     *    GamePause.update
     *    GameReady.update
     *    GameRunning.update
     *    GameOver.update
     *
     * depending on the status of the game.
     */
    public void update(float deltaTime) {
        Log.i(LOG_TAG, "update -- begin");
        List<TouchEvent> touchEvents = Gdx.input.getTouchEvents();
        states.get(DroidsWorld.getInstance().getState()).update(touchEvents, 0);
    }

    /*
     * The draw method is delegated to:
     *    GamePause.draw
     *    GameReady.draw
     *    GameRunning.draw
     *    GameOver.draw
     *
     * depending on the status of the game.
     */
    public void draw(float deltaTime) {
        Log.i(LOG_TAG, "draw -- begin");
        Gdx.graphics.drawPixmap(Assets.gamescreen, 0, 0);
        states.get(DroidsWorld.getInstance().getState()).draw();
    }

    /*
     * The screen is paused.
     */
    public void pause() {
        Log.i(LOG_TAG, "pause -- begin");
        if(DroidsWorld.getInstance().getState() == DroidsWorld.GameState.Running)
            DroidsWorld.getInstance().setState(DroidsWorld.GameState.Paused);

        if(DroidsWorld.getInstance().getState() == DroidsWorld.GameState.GameOver) {
            Settings.save(Gdx.fileIO);
        }
    }

    /*
     * The abstract class representing a generic State. Used to implement the State pattern.
     */
    abstract class GameState {
        abstract void update(List<TouchEvent> touchEvents, float deltaTime);
        abstract void draw();
    }

    /*
     * This class represents the game screen in running state. It will be responsible to update and
     * draw when the game is running.
     *
     * @author Salvatore D'Angelo
     */
    class GameRunning extends GameState {
        /*
         * Update the game when it is in running state. The method catch the user input and,
         * depending on it will move, rotate or accelerate the falling shape. It can also pause the
         * game and check for game over.
         */
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            Log.i(LOG_TAG, "GameRunning.update -- begin");
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(event.x >= 5 && event.x < 55 && event.y >= 20 && event.y < 70) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        DroidsWorld.getInstance().setState(DroidsWorld.GameState.Paused);
                        return;
                    }
                }
            }

            if(Settings.soundEnabled)
                if (!Assets.music.isPlaying()) {
                    Assets.music.setLooping(true);
                    Assets.music.play();
                }
        }

        /*
         * Draw the game in running state.
         */
        void draw() {
            Log.i(LOG_TAG, "GameRunning.draw -- begin");
            Gdx.graphics.drawPixmap(Assets.buttons, 5, 20, 50, 100, 51, 51); // pause button
            Gdx.graphics.drawPixmap(Assets.buttons, 30, 425, 50, 50, 51, 51);  // left button
            Gdx.graphics.drawPixmap(Assets.buttons, 240, 425, 0, 50, 51, 51); // right button
            Gdx.graphics.drawPixmap(Assets.buttons, 100, 425, 50, 150, 51, 51); // rotate button
            Gdx.graphics.drawPixmap(Assets.buttons, 170, 425, 0, 150, 51, 51); // down button
        }
    }

    /*
     * This class represents the game screen in pause state. It will be responsible to update and
     * draw when the game is paused.
     *
     * @author Salvatore D'Angelo
     */
    class GamePaused extends GameState {
        /*
         * Update the game when it is in paused state. The method catch the user input and
         * depending on it will resume the game or return to the start screen.
         */
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            Log.i(LOG_TAG, "GamePaused.update -- begin");

            // Check if user asked to resume the game or come back to the start screen.
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(event.x > 80 && event.x <= 240) {
                        if(event.y > 100 && event.y <= 148) {
                            if(Settings.soundEnabled)
                                Assets.click.play(1);
                            DroidsWorld.getInstance().setState(DroidsWorld.GameState.Running);
                            return;
                        }
                        if(event.y > 148 && event.y < 196) {
                            if(Settings.soundEnabled)
                                Assets.click.play(1);
                            Gdx.game.setScreen(new StartScreen());
                            return;
                        }
                    }
                }
            }
            if(Settings.soundEnabled)
                if (Assets.music.isPlaying())
                    Assets.music.pause();
        }

        /*
         * Draw the game in paused state.
         */
        void draw() {
            Log.i(LOG_TAG, "GamePaused.draw -- begin");
            Gdx.graphics.drawPixmap(Assets.pausemenu, 100, 100);
            Gdx.graphics.drawPixmap(Assets.buttons, 5, 20, 50, 100, 51, 51); // pause button
            Gdx.graphics.drawPixmap(Assets.buttons, 30, 425, 50, 50, 51, 51);  // left button
            Gdx.graphics.drawPixmap(Assets.buttons, 240, 425, 0, 50, 51, 51); // right button
            Gdx.graphics.drawPixmap(Assets.buttons, 100, 425, 50, 150, 51, 51); // rotate button
            Gdx.graphics.drawPixmap(Assets.buttons, 170, 425, 0, 150, 51, 51); // down button
        }
    }

    /*
     * This class represents the game screen in ready state. It will be responsible to update and
     * draw when the game is ready.
     *
     * @author Salvatore D'Angelo
     */
    class GameReady extends GameState {
        /*
         * Update the game when it is in ready state. The method catch the user input and
         * resume the game.
         */
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            Log.i(LOG_TAG, "GameReady.update -- begin");
            if(touchEvents.size() > 0)
                DroidsWorld.getInstance().setState(DroidsWorld.GameState.Running);
        }

        /*
         * Draw the game in ready state.
         */
        void draw() {
            Log.i(LOG_TAG, "GameReady.draw -- begin");
            Gdx.graphics.drawPixmap(Assets.readymenu, 65, 100);
            Gdx.graphics.drawPixmap(Assets.buttons, 30, 425, 50, 50, 51, 51); // left button
            Gdx.graphics.drawPixmap(Assets.buttons, 240, 425, 0, 50, 51, 51); // right button
            Gdx.graphics.drawPixmap(Assets.buttons, 100, 425, 50, 150, 51, 51); // rotate button
            Gdx.graphics.drawPixmap(Assets.buttons, 170, 425, 0, 150, 51, 51); // down button
        }
    }

    /*
     * This class represents the game screen when it is over. It will be responsible to update and
     * draw when the game is over.
     *
     * @author Salvatore D'Angelo
     */
    class GameOver extends GameState {
        /*
         * Update the game when it is over. The method catch the user input and return to the
         * start screen.
         */
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            Log.i(LOG_TAG, "GameOver.update -- begin");
            // check if the x button is pressed.
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(event.x >= 128 && event.x <= 192 && event.y >= 200 && event.y <= 264) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        Gdx.game.setScreen(new StartScreen());
                        return;
                    }
                }
            }
            // pause the music if it is playing.
            if(Settings.soundEnabled)
                if (Assets.music.isPlaying())
                    Assets.music.stop();
        }

        /*
         * Draw the game when it is over.
         */
        void draw() {
            Log.i(LOG_TAG, "GameOver.draw -- begin");
            Gdx.graphics.drawPixmap(Assets.gameoverscreen, 0, 0);
            Gdx.graphics.drawPixmap(Assets.buttons, 128, 200, 0, 100, 51, 51);
        }
    }

    /*
     * The screen is resumed.
     */
    public void resume() {
    }

    /*
     * The screen is disposed.
     */
    public void dispose() {
    }
}