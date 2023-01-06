package com.example.bestquizz.network

import com.example.bestquizz.model.ScoreEntity
import com.example.bestquizz.model.ScoreReponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object APIScore {
    // URL de l'api
    private val BASE_URL = "https://unequalednewparticle.cedriccnam.repl.co"
    //val score = data["body"]

    // initialisation de l'objet Moshi qui gère les api
    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
/*
    val listType = Types.newParameterizedType(List::class.java, ScoreReponse::class.java)
    val adapter: JsonAdapter<List<ScoreReponse>> = moshi.adapter(listType)
    val pushPortMessageList = adapter.fromJson(score)
*/
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
    }

    // intialisation de la class (maj = la classe)
    val scoreService: ScoreService by lazy {
        retrofit.create(ScoreService::class.java)
    }

}

interface ScoreService {
    // interface où on met les route de notre api
    @GET("/data")
    fun fetchQuestions(): Call<ScoreReponse>
}