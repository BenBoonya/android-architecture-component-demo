package com.ben.boonya.architecturecomponentdemo.common

import android.os.Parcel
import android.os.Parcelable


/**
 * ErrorResponse
 * on 7/27/2016

 * @author Jutikorn Varojananulux <jutikorn.v></jutikorn.v>@gmail.com>
 */
class ErrorResponse : Parcelable {
    var error: Error? = null

    constructor() {}

    constructor(error: Error?) {
        this.error = error
    }

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
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {}
}
