/*
 *  Copyright (C) 2016 Mario Zechner
 *  This file is part of Framework for book Beginning Android Games.
 *
 *  This library is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License.
 */
package org.androidforfun.framework.impl;

import android.graphics.Bitmap;

import org.androidforfun.framework.Graphics.PixmapFormat;
import org.androidforfun.framework.Pixmap;

/*
 * This class represents a bitmap. On Android a bitmap is managed by a Bitmap class.
 * Each bitmap contains also the format on how its colors are stored (PixmapFormat).
 *
 * @author mzechner
 */
public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;
    
    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    /*
     * Gets the format of the bitmap. Possible values are: ARGB8888, ARGB4444, RGB565.
     */
    public PixmapFormat getFormat() {
        return format;
    }
}
