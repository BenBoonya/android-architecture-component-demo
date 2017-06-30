package com.ben.boonya.architecturecomponentdemo.common

import android.os.Parcel
import android.os.Parcelable

/**
 * ErrorCrate
 * on 7/27/2016

 * @author Jutikorn Varojananulux <jutikorn.v></jutikorn.v>@gmail.com>
 */
class Error : Parcelable {

    var type = "N/A"
    var message = "N/A"

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.type)
        dest.writeString(this.message)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.type = `in`.readString()
        this.message = `in`.readString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<Error> = object : Parcelable.Creator<Error> {
            override fun createFromParcel(source: Parcel): Error {
                return Error(source)
            }

            override fun newArray(size: Int): Array<Error?> {
                return arrayOfNulls(size)
            }
        }
    }
}
