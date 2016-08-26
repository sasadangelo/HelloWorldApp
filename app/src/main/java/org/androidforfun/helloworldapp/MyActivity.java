package org.androidforfun.helloworldapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.androidforfun.framework.FileIO;
import org.androidforfun.framework.impl.AndroidFileIO;

import java.io.IOException;
import java.io.InputStream;

public class MyActivity extends Activity {
    class AndroidFastRenderView extends View {
        Bitmap startscreenBitmap;
        Rect dst = new Rect();

        public AndroidFastRenderView(Context context) {
            super(context);
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

        protected void onDraw(Canvas canvas) {
            dst.set(0, 0, 540, 960);
            canvas.drawBitmap(startscreenBitmap, null, dst, null);
            invalidate();
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new AndroidFastRenderView(this));
    }
}
