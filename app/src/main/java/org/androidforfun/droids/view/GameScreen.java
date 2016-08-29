package org.androidforfun.droids.view;

import org.androidforfun.droids.model.DroidsWorld;
import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.Gdx;
import org.androidforfun.framework.Game;
import org.androidforfun.framework.Input.TouchEvent;
import org.androidforfun.framework.Screen;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
public class GameScreen implements Screen {
    Map<DroidsWorld.GameState, GameState> states = new EnumMap<>(DroidsWorld.GameState.class);

    public GameScreen() {
        states.put(DroidsWorld.GameState.Paused, new GamePaused());
        states.put(DroidsWorld.GameState.Ready, new GameReady());
        states.put(DroidsWorld.GameState.Running, new GameRunning());
        states.put(DroidsWorld.GameState.GameOver, new GameOver());
    }

    @Override
    public void update() {
        List<TouchEvent> touchEvents = Gdx.input.getTouchEvents();
        states.get(DroidsWorld.getInstance().getState()).update(touchEvents, 0);
    }

    @Override
    public void draw() {
        Gdx.graphics.drawPixmap(Assets.gamescreen, 0, 0);
        states.get(DroidsWorld.getInstance().getState()).draw();
    }

    @Override
    public void pause() {
        if(DroidsWorld.getInstance().getState() == DroidsWorld.GameState.Running)
            DroidsWorld.getInstance().setState(DroidsWorld.GameState.Paused);

        if(DroidsWorld.getInstance().getState() == DroidsWorld.GameState.GameOver) {
            Settings.save(Gdx.fileIO);
        }
    }

    abstract class GameState {
        abstract void update(List<TouchEvent> touchEvents, float deltaTime);
        abstract void draw();
    }

    class GameRunning extends GameState {
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(event.x >= 5 && event.x < 55 && event.y >= 20 && event.y < 70) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        DroidsWorld.getInstance().setState(DroidsWorld.GameState.Paused);
                        return;
                    }
                }
            }

            if(Settings.soundEnabled)
                if (!Assets.music.isPlaying()) {
                    Assets.music.setLooping(true);
                    Assets.music.play();
                }
        }

        void draw() {
            Gdx.graphics.drawPixmap(Assets.buttons, 5, 20, 50, 100, 51, 51); // pause button
            Gdx.graphics.drawPixmap(Assets.buttons, 30, 425, 50, 50, 51, 51);  // left button
            Gdx.graphics.drawPixmap(Assets.buttons, 240, 425, 0, 50, 51, 51); // right button
            Gdx.graphics.drawPixmap(Assets.buttons, 100, 425, 50, 150, 51, 51); // rotate button
            Gdx.graphics.drawPixmap(Assets.buttons, 170, 425, 0, 150, 51, 51); // down button
        }
    }

    class GamePaused extends GameState {
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(event.x > 80 && event.x <= 240) {
                        if(event.y > 100 && event.y <= 148) {
                            if(Settings.soundEnabled)
                                Assets.click.play(1);
                            DroidsWorld.getInstance().setState(DroidsWorld.GameState.Running);
                            return;
                        }
                        if(event.y > 148 && event.y < 196) {
                            if(Settings.soundEnabled)
                                Assets.click.play(1);
                            Gdx.game.setScreen(new StartScreen());
                            return;
                        }
                    }
                }
            }
            if(Settings.soundEnabled)
                if (Assets.music.isPlaying())
                    Assets.music.pause();
        }

        void draw() {
            Gdx.graphics.drawPixmap(Assets.pausemenu, 100, 100);
            Gdx.graphics.drawPixmap(Assets.buttons, 5, 20, 50, 100, 51, 51); // pause button
            Gdx.graphics.drawPixmap(Assets.buttons, 30, 425, 50, 50, 51, 51);  // left button
            Gdx.graphics.drawPixmap(Assets.buttons, 240, 425, 0, 50, 51, 51); // right button
            Gdx.graphics.drawPixmap(Assets.buttons, 100, 425, 50, 150, 51, 51); // rotate button
            Gdx.graphics.drawPixmap(Assets.buttons, 170, 425, 0, 150, 51, 51); // down button
        }
    }

    class GameReady extends GameState {
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            if(touchEvents.size() > 0)
                DroidsWorld.getInstance().setState(DroidsWorld.GameState.Running);
        }

        void draw() {
            Gdx.graphics.drawPixmap(Assets.readymenu, 65, 100);
            Gdx.graphics.drawPixmap(Assets.buttons, 30, 425, 50, 50, 51, 51); // left button
            Gdx.graphics.drawPixmap(Assets.buttons, 240, 425, 0, 50, 51, 51); // right button
            Gdx.graphics.drawPixmap(Assets.buttons, 100, 425, 50, 150, 51, 51); // rotate button
            Gdx.graphics.drawPixmap(Assets.buttons, 170, 425, 0, 150, 51, 51); // down button
        }
    }

    class GameOver extends GameState {
        void update(List<TouchEvent> touchEvents, float deltaTime) {
            int len = touchEvents.size();
            for(int i = 0; i < len; i++) {
                TouchEvent event = touchEvents.get(i);
                if(event.type == TouchEvent.TOUCH_UP) {
                    if(event.x >= 128 && event.x <= 192 && event.y >= 200 && event.y <= 264) {
                        if(Settings.soundEnabled)
                            Assets.click.play(1);
                        Gdx.game.setScreen(new StartScreen());
                        return;
                    }
                }
            }
            if(Settings.soundEnabled)
                if (Assets.music.isPlaying())
                    Assets.music.stop();
        }

        void draw() {
            Gdx.graphics.drawPixmap(Assets.gameoverscreen, 0, 0);
            Gdx.graphics.drawPixmap(Assets.buttons, 128, 200, 0, 100, 51, 51);
        }
    }

   @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}