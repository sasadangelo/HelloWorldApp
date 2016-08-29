package org.androidforfun.droids.view;

import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Graphics.PixmapFormat;
import org.androidforfun.framework.Screen;
import org.androidforfun.droids.model.Settings;

public class LoadingScreen implements Screen {
    @Override
    public void update() {
        Assets.logo = Gdx.graphics.newPixmap("logo.png", PixmapFormat.RGB565);

        Assets.startscreen = Gdx.graphics.newPixmap("startscreen.png", PixmapFormat.RGB565);
        Assets.gamescreen = Gdx.graphics.newPixmap("gamescreen.png", PixmapFormat.RGB565);
        Assets.highscoresscreen = Assets.startscreen;

        Assets.mainmenu = Gdx.graphics.newPixmap("mainmenu.png", PixmapFormat.RGB565);

        Assets.buttons = Gdx.graphics.newPixmap("buttons.png", PixmapFormat.RGB565);
        Assets.numbers = Gdx.graphics.newPixmap("numbers.png", PixmapFormat.ARGB4444);

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