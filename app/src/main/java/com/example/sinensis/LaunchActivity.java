package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;


public class LaunchActivity extends AppCompatActivity{

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        handler=new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(LaunchActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}
