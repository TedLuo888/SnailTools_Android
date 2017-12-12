package com.alexluo.snailtools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        FileTools.hasSdcard(this);

        LogTools.d("number",NumberTools.format(2.1244567d,5)+"");
        LogTools.d("number",NumberTools.format(2.1235f,3)+"");

        new Thread() {
            @Override
            public void run() {
                int i = 102;
                do {
                    Log.e("number", "short->"+FileTools.getReadableFileSize(i,true));
                    Log.e("number", FileTools.getReadableFileSize(i,false));
                    i *= 556;
                } while (i > 0 && i < 1187708226);
            };

        }.start();
    }
}
