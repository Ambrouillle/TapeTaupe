package me.dcal.tapelapin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLevel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);

        Button buttonStartDeb = findViewById(R.id.startGameDeb);
        buttonStartDeb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //commenecr game
                Intent myIntent = new Intent(SelectLevel.this, MainActivity.class);
                myIntent.putExtra("mode", "deb"); //Optional parameters
                SelectLevel.this.startActivity(myIntent);

            }
        });

        Button buttonStartInter = findViewById(R.id.startGameInter);
        buttonStartInter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //commenecr game
                Intent myIntent = new Intent(SelectLevel.this, MainActivity.class);
                myIntent.putExtra("mode", "inter"); //Optional parameters
                SelectLevel.this.startActivity(myIntent);

            }
        });

        Button buttonStartExpert = findViewById(R.id.StartGameExpert);
        buttonStartExpert.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //commenecr game
                Intent myIntent = new Intent(SelectLevel.this, MainActivity.class);
                myIntent.putExtra("mode", "expert"); //Optional parameters
                SelectLevel.this.startActivity(myIntent);

            }
        });
    }
}