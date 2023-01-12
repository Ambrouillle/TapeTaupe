package me.dcal.tapelapin

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface DataService {
    @GET("/data")
    fun getData(): Call<List<Score>>
    @POST("/data")
    fun postData(@Body data: Score): Call<ResponseBody>
}