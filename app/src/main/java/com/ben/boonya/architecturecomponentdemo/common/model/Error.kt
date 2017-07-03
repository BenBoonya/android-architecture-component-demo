package com.ben.boonya.architecturecomponentdemo.common.model

import android.os.Parcel
import android.os.Parcelable

/**
 * ErrorCrate
 * on 7/27/2016

 * @author Jutikorn Varojananulux <jutikorn.v></jutikorn.v>@gmail.com>
 */
data class Error(val type: String = "N/A", val message: String = "N/A") : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Error> = object : Parcelable.Creator<Error> {
            override fun createFromParcel(source: Parcel): Error = Error(source)
            override fun newArray(size: Int): Array<Error?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(type)
        dest.writeString(message)
    }
}
