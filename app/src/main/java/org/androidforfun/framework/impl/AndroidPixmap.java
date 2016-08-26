package org.androidforfun.framework.impl;

import android.graphics.Bitmap;

import org.androidforfun.framework.Graphics.PixmapFormat;
import org.androidforfun.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;
    
    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    @Override
    public PixmapFormat getFormat() {
        return format;
    }
}
