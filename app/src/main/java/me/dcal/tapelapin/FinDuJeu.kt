package me.dcal.tapelapin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dcal.tapelapin.R
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import me.dcal.tapelapin.MainActivity
import me.dcal.tapelapin.SelectLevel
import android.widget.TextView

class FinDuJeu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin_du_jeu)
        val extras = intent.extras
        val Encore = findViewById<Button>(R.id.buttonRetry)
        Encore.setOnClickListener {
            //commenecr game
            val myIntent = Intent(this@FinDuJeu, MainActivity::class.java)
            val modeI = extras!!.getString("mode")
            myIntent.putExtra("mode", modeI) //Optional parameters
            this@FinDuJeu.startActivity(myIntent)
            //Log.i("RETOUR ","AVANNTT");
        }
        val ChangerNiv = findViewById<Button>(R.id.buttonBacktoSelect)
        ChangerNiv.setOnClickListener {
            //commenecr game
            val myIntent = Intent(this@FinDuJeu, SelectLevel::class.java)
            //myIntent.putExtra("mode", "deb"); //Optional parameters
            this@FinDuJeu.startActivity(myIntent)
            //Log.i("RETOUR ","AVANNTT");
        }
        if (extras != null) {
            //score
            val value = extras.getString("Score")
            val TextScoreFin = findViewById<View>(R.id.textScoreFin) as TextView
            TextScoreFin.text = "Score : $value"

            //typeFin
            val typeFin = extras.getString("Type")
            when (typeFin) {
                "Mort" -> {
                    val Fail = findViewById<View>(R.id.titleFail) as TextView
                    Fail.visibility = View.VISIBLE
                }
                "Timer" -> {
                    val Fin = findViewById<View>(R.id.titleFin) as TextView
                    Fin.visibility = View.VISIBLE
                }
                "Boom" -> {
                    val Boom = findViewById<ImageView>(R.id.imageboom)
                    Boom.visibility = View.VISIBLE
                }
                else -> {}
            }


            //title
        }
    }
}