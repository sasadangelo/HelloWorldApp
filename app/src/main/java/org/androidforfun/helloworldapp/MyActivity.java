package org.androidforfun.helloworldapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidforfun.framework.FileIO;
import org.androidforfun.framework.impl.AndroidFileIO;

import java.io.IOException;
import java.io.InputStream;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        InputStream in = null;
        String fileName = "startscreen.png";
        TextView textView = (TextView) this.findViewById(R.id.TextView);
        try {
            FileIO fileIO = new AndroidFileIO(getAssets());
            in = fileIO.readAsset("startscreen.png");
            Bitmap bitmap = BitmapFactory.decodeStream(in);

            if (bitmap == null) {
                textView.setText("Couldn't load bitmap from asset '" + fileName + "'");
            } else {
                textView.setText("Bitmap from asset '" + fileName + "' successfully loaded.");
            }
        } catch (IOException e) {
            textView.setText("Couldn't load bitmap from asset '" + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
