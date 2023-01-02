package me.dcal.tapelapin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.dcal.tapelapin.R
import android.content.Intent
import android.widget.Button
import me.dcal.tapelapin.MainActivity
import me.dcal.tapelapin.SelectLevel
import android.widget.TextView

class SelectLevel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_level)
        val buttonStartDeb = findViewById<Button>(R.id.startGameDeb)
        buttonStartDeb.setOnClickListener { //commenecr game
            val myIntent = Intent(this@SelectLevel, MainActivity::class.java)
            myIntent.putExtra("mode", "deb") //Optional parameters
            this@SelectLevel.startActivity(myIntent)
        }
        val buttonStartInter = findViewById<Button>(R.id.startGameInter)
        buttonStartInter.setOnClickListener { //commenecr game
            val myIntent = Intent(this@SelectLevel, MainActivity::class.java)
            myIntent.putExtra("mode", "inter") //Optional parameters
            this@SelectLevel.startActivity(myIntent)
        }
        val buttonStartExpert = findViewById<Button>(R.id.StartGameExpert)
        buttonStartExpert.setOnClickListener { //commenecr game
            val myIntent = Intent(this@SelectLevel, MainActivity::class.java)
            myIntent.putExtra("mode", "expert") //Optional parameters
            this@SelectLevel.startActivity(myIntent)
        }
    }
}