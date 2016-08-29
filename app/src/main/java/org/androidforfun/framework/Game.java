package org.androidforfun.framework;

public interface Game {
    Input getInput();
    FileIO getFileIO();
    Graphics getGraphics();
    void setScreen(Screen screen);
    Screen getCurrentScreen();
    Screen getStartScreen();
}