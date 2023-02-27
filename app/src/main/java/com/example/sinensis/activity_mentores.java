package com.example.sinensis;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class activity_mentores extends AppCompatActivity {

    Button boton,boton2,boton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentores);

        boton = (Button) findViewById(R.id.button_ines);
        boton2 = (Button) findViewById(R.id.button_paula);
        boton3 = (Button) findViewById(R.id.button_jimena);

        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_mentores.this, activity_principal.class);
                startActivity(intent);

                Intent intent1 = new Intent(activity_mentores.this, activity_principal.class);
                intent1.putExtra("mentor", "Has elegido a Ines");
                startActivity(intent1);

            }

        });
        boton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_mentores.this,activity_principal.class);
                startActivity(intent);

                Intent intent1 = new Intent(activity_mentores.this, activity_principal.class);
                intent1.putExtra("mentor", "Has elegido a Paula");
                startActivity(intent1);

            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_mentores.this, activity_principal.class);
                startActivity(intent);

                Intent intent1 = new Intent(activity_mentores.this, activity_principal.class);
                intent1.putExtra("mentor", "Has elegido a Jimena");
                startActivity(intent1);

            }
        });
    }
}