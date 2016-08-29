/*
 *  Copyright (C) 2016 Mario Zechner
 *  This file is part of Framework for book Beginning Android Games.
 *  Modified by Salvatore D'Angelo
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

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import org.androidforfun.framework.Graphics;
import org.androidforfun.framework.Pixmap;

/*
 * This class implements the Graphics subsystem for Android. The framebuffer (that in Android is
 * basically a bitmap) will be managed by a Android Canvas object.
 *
 * @author mzechner
 * @author Salvatore D'Angelo
 */
public class AndroidGraphics implements Graphics {
    AndroidFileIO fileIO;
    Bitmap frameBuffer;
    Canvas canvas;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    /*
     * Initializes the Graphics subsystem.
     */
    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        fileIO= new AndroidFileIO(assets);
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
    }

    /*
     * Loads a bitmap from filesystem and encapsulate it in a Pixmap object. In Android a bitmap is
     * managed by Android Bitmap class.
     */
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = fileIO.readAsset(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if (bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        return new AndroidPixmap(bitmap, format);
    }

    /*
     * Draws portion of a bitmap on frame buffer in (x, y) position. The portion of the bitmap
     * is delimited by rectangle defined by top left corner (srcX, srcY) and size (srcWidth,
     * srcHeight).
     */
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
            int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect,
                null);
    }

    /*
     * Draws bitmap on frame buffer in (x, y) position.
     */
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, x, y, null);
    }
}
