package org.androidforfun.helloworldapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import org.androidforfun.framework.FileIO;
import org.androidforfun.framework.impl.AndroidFileIO;

import java.io.IOException;
import java.io.InputStream;

public class MyActivity extends Activity {
    AndroidFastRenderView renderView;

    public class AndroidFastRenderView extends SurfaceView implements Runnable {
        Bitmap startscreenBitmap;
        Rect dst = new Rect();
        Thread renderThread = null;
        SurfaceHolder holder;
        volatile boolean running = false;

        public AndroidFastRenderView(Context game, Bitmap framebuffer) {
            super(game);
            this.holder = getHolder();

            InputStream inputStream = null;
            try {
                FileIO fileIO = new AndroidFileIO(getAssets());
                inputStream = fileIO.readAsset("startscreen.png");
                startscreenBitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
            } finally {
                if (inputStream != null) try { inputStream.close(); } catch (IOException e) {}
            }
        }

        public void resume() {
            running = true;
            renderThread = new Thread(this);
            renderThread.start();
        }

        public void run() {
            Rect dstRect = new Rect();
            while(running) {
                if(!holder.getSurface().isValid())
                    continue;

                Canvas canvas = holder.lockCanvas();
                canvas.getClipBounds(dstRect);
                canvas.drawBitmap(startscreenBitmap, null, dstRect, null);
                holder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause() {
            running = false;
            while(true) {
                try {
                    renderThread.join();
                    break;
                } catch (InterruptedException e) {
                    // retry
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape ? 480 : 320;
        int frameBufferHeight = isLandscape ? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,
                frameBufferHeight, Bitmap.Config.RGB_565);

        renderView = new AndroidFastRenderView(this, frameBuffer);

        setContentView(renderView);
    }

    @Override
    public void onResume() {
        super.onResume();
        renderView.resume();
    }

    public void onPause() {
        super.onPause();
        renderView.pause();
    }
}
