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

import org.androidforfun.framework.Gdx;
import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Game;
import org.androidforfun.framework.Graphics;
import org.androidforfun.framework.Input.TouchEvent;
import org.androidforfun.framework.Screen;

import java.util.List;

/*
 * This class represents the highscores screen. The screen show the top five scores achieved by the
 * user.
 *
 * @author Salvatore D'Angelo
 */
public class HighscoreScreen implements Screen {
    String lines[] = new String[5];

    /*
     * Initialize the screen with the following scores: 100, 80, 50, 30, 10.
     */
    public HighscoreScreen() {
        for (int i = 0; i < 5; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
    }

    /*
     * Check the user input and if he press the back button go back to the start screen.
     */
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = Gdx.input.getTouchEvents();
        //Gdx.input.getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 32 &&  event.x < 82 && event.y >= 370 && event.y < 430) {
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    Gdx.game.setScreen(new StartScreen());
                    return;
                }
            }
        }
    }

    /*
     * Draw the highscores screen.
     */
    public void draw(float deltaTime) {
        // draw the background.
        Gdx.graphics.drawPixmap(Assets.highscoresscreen, 0, 0);
        // draw the 5 scores.
        int y = 100;
        for (int i = 0; i < 5; i++) {
            drawText(Gdx.graphics, lines[i], 20, y);
            y += 50;
        }
        // draw the back button.
        Gdx.graphics.drawPixmap(Assets.buttons, 32, 370, 50, 50, 51, 51);
    }

    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
    
            if (character == ' ') {
                x += 20;
                continue;
            }
    
            int srcX;
            int srcWidth;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
    
            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    /*
     * The screen is paused.
     */
    public void pause() {

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
