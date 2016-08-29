package org.androidforfun.droids.view;

import org.androidforfun.framework.Gdx;
import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Input;
import org.androidforfun.framework.Screen;

import java.util.List;

public class StartScreen implements Screen {
    private boolean soundEnabled;
    @Override
    public void update() {
        List<Input.TouchEvent> touchEvents = Gdx.input.getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(inBounds(event, 32, 374, 51, 51)) {
                    soundEnabled = !soundEnabled;
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
    @Override
    public void draw() {
        Gdx.graphics.drawPixmap(Assets.startscreen, 0, 0);
        if(soundEnabled)
            Gdx.graphics.drawPixmap(Assets.buttons, 32, 370, 0, 0, 51, 51);
        else
            Gdx.graphics.drawPixmap(Assets.buttons, 32, 370, 50, 0, 51, 51);
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
