package org.androidforfun.droids.view;

import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Screen;

public class GameScreen implements Screen {
    @Override
    public void update() {
        if(Settings.soundEnabled)
            if (!Assets.music.isPlaying()) {
                Assets.music.setLooping(true);
                Assets.music.play();
            }
    }

    @Override
    public void draw() {
        Gdx.graphics.drawPixmap(Assets.gamescreen, 0, 0);
    }

    @Override
    public void pause() {
        if(Settings.soundEnabled)
            if (Assets.music.isPlaying())
                Assets.music.pause();
    }

   @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}