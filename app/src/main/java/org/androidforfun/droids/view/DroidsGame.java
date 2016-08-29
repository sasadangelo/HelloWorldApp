package org.androidforfun.droids.view;

import org.androidforfun.framework.Screen;
import org.androidforfun.framework.impl.AndroidGame;

public class DroidsGame extends AndroidGame {
    @Override
    public Screen getStartScreen() {
        return new LoadingScreen();
    }
}
