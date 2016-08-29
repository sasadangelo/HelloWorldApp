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
package org.androidforfun.framework;

/**
 * This interface encapsulates communication with the graphics processor.
 * <p>
 * If supported by the backend, this interface lets you query the available display modes (graphics
 * resolution and color depth) and change it.
 *
 * @author mzechner */
public interface Graphics {
    /**
     * Enum describing the bits per pixel and depth buffer precision.
     */
    enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    /**
     * Load a bitmap from an image file (i.e. PNG file) with the specified bits per pixels.
     */
    Pixmap newPixmap(String fileName, PixmapFormat format);

    /**
     * Draw only the region of a bitmap delimited by rectangle having the top left corner in
     * (srcX, srcY) and size (srcWidth, srcHeight).
     *
     * @param pixmap bitmap to draw.
     * @param x x position where the bitmap must be drawn.
     * @param y y position where the bitmap must be drawn.
     * @param srcX x position of the top left corner of the region of the bitmap to draw.
     * @param srcY y position of the top left corner of the region of the bitmap to draw.
     * @param srcWidth width of the region of the bitmap to draw.
     * @param srcHeight height of the region of the bitmap to draw.
     */
    void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);

    /**
     * Draw only the region of a bitmap delimited by rectangle having the top left corner in
     * (srcX, srcY) and size (srcWidth, srcHeight).
     *
     * @param pixmap bitmap to draw.
     * @param x x position where the bitmap must be drawn.
     * @param y y position where the bitmap must be drawn.
     */
    void drawPixmap(Pixmap pixmap, int x, int y);
}
