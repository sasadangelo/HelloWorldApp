package org.androidforfun.helloworldapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidforfun.droids.model.Settings;
import org.androidforfun.framework.FileIO;
import org.androidforfun.framework.impl.AndroidFileIO;
public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        FileIO fileIO = new AndroidFileIO();
        Settings.addScore(5000);
        Settings.addScore(4000);
        Settings.addScore(3000);
        Settings.addScore(2000);
        Settings.addScore(1000);
        Settings.save(fileIO);
        Settings.addScore(9999);
        Settings.addScore(8888);
        Settings.addScore(7777);
        Settings.addScore(6666);
        Settings.addScore(6666);
        Settings.load(fileIO);
        StringBuffer s = new StringBuffer();
        for (int i: Settings.highscores) {
            s.append(i);
            s.append(" ");
        }

        TextView textView = (TextView) this.findViewById(R.id.TextView);
        textView.setText(s);
    }
}
