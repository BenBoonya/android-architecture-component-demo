package com.ben.boonya.architecturecomponentdemo.common

import android.os.Parcel
import android.os.Parcelable


/**
 * ErrorResponse
 * on 7/27/2016

 * @author Jutikorn Varojananulux <jutikorn.v></jutikorn.v>@gmail.com>
 */
data class ErrorResponse(val error: Error?) : Parcelable {
    override fun toString(): String {
        error?.message?.let {
            return it
        }
        return ""
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<ErrorResponse> = object : Parcelable.Creator<ErrorResponse> {
            override fun createFromParcel(source: Parcel): ErrorResponse = ErrorResponse(source)
            override fun newArray(size: Int): Array<ErrorResponse?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readParcelable<Error>(Error::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(error, 0)
    }
}
