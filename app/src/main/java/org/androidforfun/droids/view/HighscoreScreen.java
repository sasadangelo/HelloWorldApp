package org.androidforfun.droids.view;

import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Game;
import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Graphics;
import org.androidforfun.framework.Input.TouchEvent;
import org.androidforfun.framework.Screen;

import java.util.List;

public class HighscoreScreen implements Screen {
    String lines[] = new String[5];

    public HighscoreScreen() {
        for (int i = 0; i < 5; i++) {
            lines[i] = "" + (i + 1) + ". " + Settings.highscores[i];
        }
    }

    @Override
    public void update() {
        List<TouchEvent> touchEvents = Gdx.input.getTouchEvents();
        //Gdx.input.getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x >= 32 &&  event.x < 82 && event.y >= 370 && event.y < 430) {
                    Gdx.game.setScreen(new StartScreen());
                    return;
                }
            }
        }
    }

    @Override
    public void draw() {
        Gdx.graphics.drawPixmap(Assets.highscoresscreen, 0, 0);

        int y = 100;
        for (int i = 0; i < 5; i++) {
            drawText(Gdx.graphics, lines[i], 20, y);
            y += 50;
        }
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
    
            int srcX = 0;
            int srcWidth = 0;
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
