package me.dcal.tapelapin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //private ResultProfileBinding binding;
    private List<Button> LstButtons;
    private int PositionTaupe;
    private int PositionBombe = 0; //mode expert
    private int nbScore = 0;
    private int nbVie = 3;
    private String mode = "";
    private String typeFin ="";
    int nbAnimaux = 0;

    private ImageView heart1;
    private ImageView heart2;
    private ImageView heart3;

    private Button  Bombe;


    private static final long TEMPS_TIMER = 60000; // en milliseconde soit 6 sec
    private TextView TimerView;
    private CountDownTimer CountDownTimer;
    private long TimerLeft = TEMPS_TIMER; // on commence avec le timer plein


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gestion choix nv
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //score
            String ExtraMode = extras.getString("mode");
            /*TextView TextmodeJeu = (TextView) findViewById(R.id.modeJeu);
            TextmodeJeu.setText("mode : " + ExtraMode);*/
            mode = ExtraMode; // je met le mode
            switch (mode){
                case "deb":
                    nbAnimaux = 4;
                    break;
                case "inter":
                    nbAnimaux = 5;
                    break;
                case "expert":
                    nbAnimaux = 6;
                    break;

                default:
                    break;
            }

        }



        TimerView = findViewById(R.id.timer);
        startTimer();

        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);

        addButton();
        Jeu();

    }

    private void addButton(){
        //je garde de coté les boutons, ca c'est les boutons de bases
        LstButtons = new ArrayList<>();
        LstButtons.add((Button) findViewById(R.id.button1));
        LstButtons.add((Button) findViewById(R.id.button2));
        LstButtons.add((Button) findViewById(R.id.button3));
        LstButtons.add((Button) findViewById(R.id.button4));

        switch (mode){
            case "deb":
                break;
            case "inter":
                Button b5 = (Button)findViewById(R.id.button5);
                b5.setVisibility(View.VISIBLE);
                LstButtons.add(b5);
                break;
            case "expert":
                Button bt5 = (Button)findViewById(R.id.button5);
                bt5.setVisibility(View.VISIBLE);
                LstButtons.add(bt5);

                Button bt6 = (Button)findViewById(R.id.button6);
                bt6.setVisibility(View.VISIBLE);
                LstButtons.add(bt6);
                break;
            default:
                break;
        }




    }


    private void Jeu(){
        updateCountDownText();
        NewRound();

        for (Button b : LstButtons) {
            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Toast.makeText(getApplicationContext(),"button1", Toast.LENGTH_SHORT).show();
                    OnCLickButton(v);

                }
            });
        }
    }

    private void NewRound(){
        setAutre();
        setTaupe();
    }

    private void setAutre(){
        for (Button b : LstButtons) {
            Resources res = this.getResources();
            Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.lievre_t, null);
            b.setForeground(myImage);

            //b.setText("Autre");
        }
            switch (mode){
                case "deb":
                    //le mode deb est celui de base
                    ImageView mode_deb = findViewById(R.id.mode_deb);
                    mode_deb.setVisibility(View.VISIBLE);
                    break;
                case "inter":
                    ImageView mode_inter = findViewById(R.id.mode_inter);
                    mode_inter.setVisibility(View.VISIBLE);
                    //ajout d'un rat
                    setRat();
                    break;
                case "expert":
                    ImageView mode_expert = findViewById(R.id.mode_expert);
                    mode_expert.setVisibility(View.VISIBLE);
                    //ajout d'une bombe
                    setRat();
                    setBombe();
                    break;

                default:
                    break;
            }


    }
    private void setRat(){
        //on utilise le hasard pour decider uqel bouton sera le bon
        Random seed = new Random();
        int PositionRat = seed.nextInt(nbAnimaux);
        Button b = LstButtons.get(PositionRat);
        Resources res = this.getResources();
        Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.souris_tr, null);
        b.setForeground(myImage);
        //b.setText("Rat");
    }

    private void setBombe(){

        //on utilise le hasard pour decider uqel bouton sera le bon
        Random seed = new Random();
        PositionBombe = seed.nextInt(nbAnimaux);
        Button b = LstButtons.get(PositionBombe);
        Resources res = this.getResources();
        Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.bombe, null);
        b.setForeground(myImage);
        //Toast.makeText(getApplicationContext(),"setBombe : "+PositionBombe, Toast.LENGTH_SHORT).show();
        //b.setText("Bombe");
    }

    private void setTaupe(){

        //on utilise le hasard pour decider uqel bouton sera le bon
        Random seed = new Random();
        PositionTaupe = seed.nextInt(nbAnimaux);
        Button b = LstButtons.get(PositionTaupe);
        //b.setText("Taupe");
        Resources res = this.getResources();
        Drawable myImage = ResourcesCompat.getDrawable(res, R.drawable.taupe_trapp, null);
        b.setForeground(myImage);

    }
    private boolean isFailGame(){
        if(nbVie <= 0){
            typeFin = "Mort";
            return true;
        }
        return false;
    }

    private void OnCLickButton (View view){
        //Toast.makeText(getApplicationContext(),"onCllicj", Toast.LENGTH_SHORT).show();
        Button Taupe = LstButtons.get(PositionTaupe);
        if(mode.equals("expert")) {
            Bombe = LstButtons.get(PositionBombe);
        }


        if(view == Taupe){
            //OK c'est la taupe
            nbScore++;
            TextView TextScore = (TextView) findViewById(R.id.score);
            TextScore.setText("Score : " + nbScore);

        }
        else{
            if(mode.equals("expert")) {
                if (view == Bombe) {
                    typeFin = "Boom";
                    //Toast.makeText(getApplicationContext(), "BOOM", Toast.LENGTH_SHORT).show();

                    ImageView ImageBoom = findViewById(R.id.imageboom);
                    ImageBoom.setVisibility(View.VISIBLE);
                    startEndActivity();

                }
            }
            //FAUX
            nbVie--;
            switch (nbVie){
                case 3:
                    break;
                case 2 :
                    heart3.setVisibility(View.INVISIBLE);
                    break;
                case 1 :
                    heart2.setVisibility(View.INVISIBLE);
                    heart3.setVisibility(View.INVISIBLE);
                    break;
                default:
                    break;
            }
            //TextView TextFail = (TextView) findViewById(R.id.fail);
            //TextFail.setText("Fail : "+nbFail);

        }
        //Log.i("Timer Left : ", TimerLeft + "");
        if(true == isFailGame()){
            startEndActivity();
        }
        else{
            NewRound();
        }
    }



    private void startTimer(){
        CountDownTimer = new CountDownTimer(TEMPS_TIMER,1000) {
            @Override
            public void onTick(long l) {
                TimerLeft = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                if(typeFin.equals("")){
                    typeFin = "Timer";
                    startEndActivity();
                }


            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (TimerLeft / 1000) / 60;
        int secondes = (int) (TimerLeft / 1000) % 60;

        String TimerLeftFormat = String.format(Locale.getDefault(), "%02d:%02d",minutes,secondes);
        TimerView.setText(TimerLeftFormat);
    }

    private void startEndActivity(){
        Intent intent = new Intent(this, FinDuJeu.class);
        intent.putExtra("Score", Integer.toString(nbScore)); //Optional parameters
        intent.putExtra("mode", mode); //Optional parameters
        intent.putExtra("Type", typeFin); //Optional parameters
        //Log.i("SCOREMAINActivity", " : "+ nbScore);
        startActivity(intent);
    }


    }
