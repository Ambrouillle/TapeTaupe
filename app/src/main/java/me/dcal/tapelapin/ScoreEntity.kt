package com.example.bestquizz.model


import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScoreEntity(
        val id: String,
        val score:Int,
        val level: String,

): Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readInt()!!,
            parcel.readString()!!,

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(score)
        parcel.writeString(level)


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ScoreEntity> {
        override fun createFromParcel(parcel: Parcel): ScoreEntity {
            return ScoreEntity(parcel)
        }

        override fun newArray(size: Int): Array<ScoreEntity?> {
            return arrayOfNulls(size)
        }
    }

}

@JsonClass(generateAdapter = true)
data class ScoreReponse(@Json(name="results")
                            val result : List<ScoreEntity>)