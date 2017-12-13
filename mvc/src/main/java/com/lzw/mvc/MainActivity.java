package com.lzw.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author: lzw
 * @date: 09/12/2017 11:15 AM
 * @desc:
 */

public class MainActivity extends AppCompatActivity {
    private MainModel mainModel;
    private AppCompatEditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainModel = new MainModel();
        mainModel.setUserId(9527);
        mainModel.setUserName("MVC");
        findViewById(R.id.mvc_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "My UserName is " + mainModel.getUserName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
