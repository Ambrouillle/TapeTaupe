package me.dcal.tapelapin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bestquizz.model.ScoreEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelectLevel : AppCompatActivity() {
    lateinit var scoreValues : ArrayList<ScoreEntity>
    var scoreIsLoaded : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_level)
        /*
        val tScore : TextView = findViewById<TextView>(R.id.viewScore)

        loadScores();

        if(scoreIsLoaded) {
            Log.w("test is load", scoreValues[0].toString())
            // TO DO Recycler view
            //tScore.setText("")
        }*/

        loadScores()

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

    fun loadScores(){
        /*
        // ------------------- API call -------------------
        // ------------------ questions request ------------------------ //

        val scoreResponse = APIScore.scoreService.fetchQuestions()

        scoreResponse.enqueue(object : retrofit2.Callback<ScoreReponse> {
            override fun onResponse(
                    call: Call<ScoreReponse>,
                    response: Response<ScoreReponse>
            ) {
                if(response.isSuccessful) {
                    Log.w("SCOOORE", ""+response.body())
                    val result = response.body()?.result
                    result?.let {
                        // use result val here
                        scoreValues = result as ArrayList<ScoreEntity>
                        scoreIsLoaded = true
                    }
                }
            }
            override fun onFailure(call: Call<ScoreReponse>, t: Throwable) {
                Log.e("failed api", "TETETETTETE"+ t.message)
                Toast.makeText(this@SelectLevel, "Les scores n'ont pas pu être chargé", Toast.LENGTH_SHORT).show()

            }
        })*/
        val retrofit = Retrofit.Builder()
                .baseUrl("https://UnequaledNewParticle.cedriccnam.repl.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()



        val dataService = retrofit.create(DataService::class.java)
        val call = dataService.getData()
        call.enqueue(object : Callback<List<Score>> {
            override fun onResponse(call: Call<List<Score>>, response: Response<List<Score>>) {

                val data = response.body()

                val recyclerView: RecyclerView = findViewById(R.id.recycler_view_score)
                recyclerView.layoutManager = LinearLayoutManager(this@SelectLevel)


                recyclerView.adapter = DataAdapter(response.body()!!)

            }

            override fun onFailure(call: Call<List<Score>>, t: Throwable) {
                val recyclerView: RecyclerView = findViewById(R.id.recycler_view_score)
                recyclerView.layoutManager = LinearLayoutManager(this@SelectLevel)
                recyclerView.adapter = DataAdapter(emptyList())
                Toast.makeText(this@SelectLevel,
                        "Les scores n'ont pas pu être chargé", Toast.LENGTH_SHORT).show()
            }

        })


    }
}