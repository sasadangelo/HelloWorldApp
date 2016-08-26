package org.androidforfun.framework;

public interface Graphics {
    enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    Pixmap newPixmap(String fileName, PixmapFormat format);
    void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight);
    void drawPixmap(Pixmap pixmap, int x, int y);
}
