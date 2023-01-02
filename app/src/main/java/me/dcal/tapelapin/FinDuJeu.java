package me.dcal.tapelapin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FinDuJeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_du_jeu);

        Bundle extras = getIntent().getExtras();
        Button Encore = findViewById(R.id.buttonRetry);
        Encore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //commenecr game
                Intent myIntent = new Intent(FinDuJeu.this, MainActivity.class);
               String modeI = extras.getString("mode");
                myIntent.putExtra("mode", modeI); //Optional parameters
                FinDuJeu.this.startActivity(myIntent);
                //Log.i("RETOUR ","AVANNTT");

            }
        });

        Button ChangerNiv = findViewById(R.id.buttonBacktoSelect);
        ChangerNiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //commenecr game
                Intent myIntent = new Intent(FinDuJeu.this, SelectLevel.class);
                //myIntent.putExtra("mode", "deb"); //Optional parameters
                FinDuJeu.this.startActivity(myIntent);
                //Log.i("RETOUR ","AVANNTT");

            }
        });




        if (extras != null) {
            //score
            String value = extras.getString("Score");
            TextView TextScoreFin = (TextView) findViewById(R.id.textScoreFin);
            TextScoreFin.setText("Score : " + value);

            //typeFin
            String typeFin = extras.getString("Type");
            //Log.i("TypeFin"," : "+ typeFin);
            switch (typeFin){
                case "Mort":
                    TextView Fail = (TextView) findViewById(R.id.titleFail);
                    Fail.setVisibility(View.VISIBLE);
                    break;
                case "Timer":
                    TextView Fin = (TextView) findViewById(R.id.titleFin);
                    Fin.setVisibility(View.VISIBLE);
                    break;
                case "Boom":
                    ImageView Boom = findViewById(R.id.imageboom);
                    Boom.setVisibility(View.VISIBLE);
                    break;

                default:
                    break;

            }



            //title


        }
    }
}