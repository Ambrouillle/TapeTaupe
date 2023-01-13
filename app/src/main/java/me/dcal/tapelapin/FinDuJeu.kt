package me.dcal.tapelapin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FinDuJeu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fin_du_jeu)
        val extras = intent.extras

        //BOUTON ENCORE
        val Encore = findViewById<Button>(R.id.buttonRetry)
        Encore.setOnClickListener {
            //recommencer game
            val myIntent = Intent(this@FinDuJeu, MainActivity::class.java)
            val modeI = extras!!.getString("mode")
            myIntent.putExtra("mode", modeI) //Optional parameters
            this@FinDuJeu.startActivity(myIntent)
        }

        //BOUTON CHANGER DE NV
        val ChangerNiv = findViewById<Button>(R.id.buttonBacktoSelect)
        ChangerNiv.setOnClickListener {
            val myIntent = Intent(this@FinDuJeu, SelectLevel::class.java)
            this@FinDuJeu.startActivity(myIntent)
        }



        if (extras != null) {
            //score
            val valueScore = extras.getString("Score")
            val TextScoreFin = findViewById<View>(R.id.textScoreFin) as TextView
            TextScoreFin.text = "Score : $valueScore"

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


            //ENREGISTRER SCORE
            val playerName = findViewById<TextView>(R.id.PlayerName)
            val send = findViewById<TextView>(R.id.Send)

            val buttonEnreg = findViewById<Button>(R.id.bEnregScore)
            buttonEnreg.setOnClickListener {
                playerName.visibility = View.VISIBLE
                send.visibility = View.VISIBLE
            }

            //quand on clique sur Send
            send.setOnClickListener {

                //requete POST
                val retrofit = Retrofit.Builder()
                        .baseUrl("https://UnequaledNewParticle.cedriccnam.repl.co")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                val dataService = retrofit.create(DataService::class.java)
                val data = Score(name = playerName.getText().toString(), score = valueScore?.toInt(),
                level = extras!!.getString("mode"))


                val call = dataService.postData(data)
                call.enqueue(/* callback = */ object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            Log.d("Data", "Data posted successfully")
                        } else {
                            Log.e("Data", "Error posting data")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.e("Data", "Error posting data: ${t.message}")
                    }


                })
                //et je retourne au menu
                val myIntent = Intent(this@FinDuJeu, SelectLevel::class.java)
                this@FinDuJeu.startActivity(myIntent)

            }



        }
    }
}