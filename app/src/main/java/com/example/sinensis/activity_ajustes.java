package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_ajustes extends AppCompatActivity {

    ImageButton btn_ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        btn_ajustes = (ImageButton) findViewById(R.id.flechaA);
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ajustes.this, activity_principal.class);
                startActivity(intent);
                finish();
            }
        });

    }

    /*public void flechaA(View view){
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ajustes.this, activity_principal.class);
                startActivity(intent);
                finish();
            }
        });
    }*/




}
