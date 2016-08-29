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

import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Gdx;

import org.androidforfun.framework.Input;
import org.androidforfun.framework.Screen;

import java.util.List;

/*
 * This class represents the start screen. It contains the logo and the main menu with three
 * options:
 *     Play
 *     Highscores
 *     Quit
 *
 *  It also has a button to activate/deactivate sound.
 *
 * @author Salvatore D'Angelo
 */
public class StartScreen implements Screen {
    /*
     * Check the user input and if one the the folloing things could occurs:
     *     - Play the game
     *     - See Highscores
     *     - Quit game
     *     - Activate/deactivate sound
     */
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = Gdx.input.getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                // activate/deactivate sound
                if(inBounds(event, 32, 374, 51, 51)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                }
                // play the game
                if(inBounds(event, 64, 220, 192, 42) ) {
                    Gdx.game.setScreen(new GameScreen());
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                // see highscores.
                if(inBounds(event, 64, 220 + 42, 192, 42) ) {
                    Gdx.game.setScreen(new HighscoreScreen());
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                // quit the game.
                if(inBounds(event, 64, 220 + 84, 192, 42) ) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    System.exit(1);
                    return;
                }
            }
        }
    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    /*
     * Draw the start screen.
     */
    public void draw(float deltaTime) {
        // draw the background
        Gdx.graphics.drawPixmap(Assets.startscreen, 0, 0);
        // draw the logo
        Gdx.graphics.drawPixmap(Assets.logo, 32, 20);
        // draw the main menu
        Gdx.graphics.drawPixmap(Assets.mainmenu, 64, 220);
        // draw the sound button depending on sound status.
        if(Settings.soundEnabled)
            Gdx.graphics.drawPixmap(Assets.buttons, 32, 370, 0, 0, 51, 51);
        else
            Gdx.graphics.drawPixmap(Assets.buttons, 32, 370, 50, 0, 51, 51);
    }

    /*
     * The screen is paused.
     */
    public void pause() {
        Settings.save(Gdx.fileIO);
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
