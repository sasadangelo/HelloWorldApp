package org.androidforfun.droids.view;

import android.util.Log;

import org.androidforfun.framework.Gdx;
import org.androidforfun.helloworldapp.MyActivity;
import org.androidforfun.framework.Graphics;
import org.androidforfun.framework.Graphics.PixmapFormat;
import org.androidforfun.framework.Screen;
import org.androidforfun.droids.model.Settings;

public class LoadingScreen implements Screen {
    @Override
    public void update() {
        Assets.startscreen = Gdx.graphics.newPixmap("startscreen.png", PixmapFormat.RGB565);
        Settings.load(Gdx.fileIO);
        Gdx.game.setScreen(new StartScreen());
    }

    @Override
    public void draw() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}