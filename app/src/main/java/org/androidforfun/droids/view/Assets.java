/*
 *  Copyright (C) 2016 Salvatore D'Angelo
 *  This file is part of Droids project.
 *  This file derives from the Mr Nom project developed by Mario Zechner for the Beginning Android
 *  Games book (chapter 6).
 *
 *  Droids is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Droids is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License.
 */
package org.androidforfun.droids.view;

import org.androidforfun.framework.Music;
import org.androidforfun.framework.Pixmap;
import org.androidforfun.framework.Sound;

/*
 * This class contains the global references to all the assets used in HelloWorldApp game.
 *
 * @author Salvatore D'Angelo
 */
public class Assets {
    // the logo asset
    public static Pixmap logo;

    // the screen used in DroidsWorld game
    public static Pixmap gamescreen;
    public static Pixmap startscreen;
    public static Pixmap highscoresscreen;
    public static Pixmap gameoverscreen;

    // the menu used in DroidsWorld game
    public static Pixmap mainmenu;
    public static Pixmap pausemenu;
    public static Pixmap readymenu;

    // buttons and numbers
    public static Pixmap buttons;
    public static Pixmap numbers;

    // sounds
    public static Sound click;

    // music
    public static Music music;
}
