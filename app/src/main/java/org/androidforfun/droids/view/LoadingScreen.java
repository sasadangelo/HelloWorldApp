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

import org.androidforfun.framework.Game;
import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Graphics;
import org.androidforfun.framework.Graphics.PixmapFormat;
import org.androidforfun.framework.Screen;
import org.androidforfun.droids.model.Settings;

/*
 * This class represents the loading screen. It load in memory all the assets used by the game.
 * Usually games show a progress bar in this screen. To simplify the code and since the assets are
 * loaded very quickly I avoided this complication.
 *
 * @author Salvatore D'Angelo
 */
public class LoadingScreen implements Screen {
    private static final String LOG_TAG = "Droids.LoadingScreen";

    @Override
    public void update(float deltaTime) {
        Log.i(LOG_TAG, "update -- begin");
        Assets.logo = Gdx.graphics.newPixmap("logo.png", PixmapFormat.RGB565);

        // Screens
        Assets.startscreen = Gdx.graphics.newPixmap("startscreen.png", PixmapFormat.RGB565);
        Assets.gamescreen = Gdx.graphics.newPixmap("gamescreen.png", PixmapFormat.RGB565);
        Assets.highscoresscreen = Assets.startscreen;
        Assets.gameoverscreen = Gdx.graphics.newPixmap("gameover.png", PixmapFormat.RGB565);

        // Menus
        Assets.mainmenu = Gdx.graphics.newPixmap("mainmenu.png", PixmapFormat.RGB565);
        Assets.pausemenu = Gdx.graphics.newPixmap("pausemenu.png", PixmapFormat.RGB565);
        Assets.readymenu = Gdx.graphics.newPixmap("ready.png", PixmapFormat.ARGB4444);

        Assets.buttons = Gdx.graphics.newPixmap("buttons.png", PixmapFormat.RGB565);
        Assets.numbers = Gdx.graphics.newPixmap("numbers.png", PixmapFormat.ARGB4444);

        // Audio effects
        Assets.click = Gdx.audio.newSound("click.ogg");

        // Music
        Assets.music = Gdx.audio.newMusic("Korobeiniki.ogg");

        Settings.load(Gdx.fileIO);
        Gdx.game.setScreen(new StartScreen());
    }

    /*
     * Draw nothing.
     */
    public void draw(float deltaTime) {
        Log.i(LOG_TAG, "draw -- begin");
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