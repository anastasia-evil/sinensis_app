package com.example.sinensis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_ajustes extends AppCompatActivity {

    //ImageButton btn_ajustes;

    private ListView listview_ajustes;
    private ArrayList<String> ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        listview_ajustes = (ListView) findViewById(R.id.listview_ajustes);
        ajustes = new ArrayList<String>();
        ajustes.add("Sobre la app");
        ajustes.add("Creadoras");
        ajustes.add("ODS");
        ajustes.add("Mis datos");
        ajustes.add("Idioma");
        ajustes.add("Notificaciones");

        ArrayAdapter<String> adapter_ajustes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ajustes);

        listview_ajustes.setAdapter(adapter_ajustes);
        listview_ajustes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                showPopUp(view);
            }
        });

        /*  CÓDIGO PARA REPRODUCIR MUSICA Y CONTROLAR VOLUMEN
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
        */

    }

    public void showPopUp(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = getLayoutInflater();

        //this is custom dialog
        //custom_popup_dialog contains textview only
        View customView = layoutInflater.inflate(R.layout.popup_ajustes, null);
        // reference the textview of custom_popup_dialog
        TextView tv = (TextView) customView.findViewById(R.id.tvpopup);


        //this textview is from the adapter
        TextView text = (TextView) view.findViewById(R.id.textView);
        // get the text of the view clicked
        String day = text.getText().toString();
        //set the text to the view of custom_popop_dialog.
        tv.setText(day);

        builder.setView(customView);
        builder.create();
        builder.show();

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
