package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class activity_mentores extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentores);
    }

    public void onClick(View view){
        boolean visto = ((Button)view).isClickable();

        switch (view.getId()){

            case R.id.button_ines:
                if(visto){
                    Snackbar mySnackbar = Snackbar.make(view,"Has elegido a Ines", 5);
                    mySnackbar.show();
                }
                break;

            case R.id.button_paula:
                if(visto){
                    Snackbar mySnackbar = Snackbar.make(view,"Has elegido a Paula", 5);
                    mySnackbar.show();

                }
                break;

            case R.id.button_jimena:
                if(visto){
                    Snackbar mySnackbar = Snackbar.make(view,"Has elegido a Jimena", 5);
                    mySnackbar.show();

                }
                break;
        }
    }



}