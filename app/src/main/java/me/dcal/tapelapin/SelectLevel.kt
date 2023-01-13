package me.dcal.tapelapin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelectLevel : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_level)

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