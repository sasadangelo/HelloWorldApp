package org.androidforfun.droids.view;

import org.androidforfun.framework.Gdx;
import org.androidforfun.helloworldapp.MyActivity;
import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Graphics;
import org.androidforfun.framework.Screen;

public class StartScreen implements Screen {
    @Override
    public void update() {
    }

    @Override
    public void draw() {
        Gdx.graphics.drawPixmap(Assets.startscreen, 0, 0);
    }

    @Override
    public void pause() {
        Settings.save(Gdx.fileIO);
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
