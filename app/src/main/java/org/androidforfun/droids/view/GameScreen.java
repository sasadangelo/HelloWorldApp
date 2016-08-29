package org.androidforfun.droids.view;

import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Screen;

public class GameScreen implements Screen {
    @Override
    public void update() {
    }

    @Override
    public void draw() {
        Gdx.graphics.drawPixmap(Assets.gamescreen, 0, 0);
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