package com.ben.boonya.architecturecomponentdemo.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Boonya Kitpitak on 6/16/17.
 */
data class Film(
        @SerializedName("episode_id") val episodeId: Long,
        @SerializedName("title") val title: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("director") val director: String,
        @SerializedName("opening_crawl") val openingCrawl: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Film> = object : Parcelable.Creator<Film> {
            override fun createFromParcel(source: Parcel): Film = Film(source)
            override fun newArray(size: Int): Array<Film?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(episodeId)
        dest.writeString(title)
        dest.writeString(releaseDate)
        dest.writeString(director)
        dest.writeString(openingCrawl)
    }
}