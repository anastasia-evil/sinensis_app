package com.example.sinensis;

import static com.example.sinensis.activity_datos.nombre;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_ajustes extends AppCompatActivity {

    ImageButton btn_ajustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        //variables
        Button play;
        SeekBar seekBarVol;
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        btn_ajustes = (ImageButton) findViewById(R.id.flechaA);
        int max_vol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        Intent intent1 = new Intent(this, activity_principal.class);
        intent1.putExtra("nombre", nombre.getText().toString());
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_ajustes.this, activity_principal.class);
                startActivity(intent);
                startActivity(intent1);
                finish();
            }
        });

        play = (Button)findViewById(R.id.button_play);
        MediaPlayer mp = MediaPlayer.create(this, R.raw.sinensis_t);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    Toast.makeText(activity_ajustes.this, "Pausa", Toast.LENGTH_SHORT).show();
                }else{
                    mp.start();
                    Toast.makeText(activity_ajustes.this, "Play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekBarVol = (SeekBar) findViewById(R.id.seekBar_vol);
        seekBarVol.setMax(max_vol);
        seekBarVol.setProgress(currentVolume);
        seekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //papa
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i ,0 );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
