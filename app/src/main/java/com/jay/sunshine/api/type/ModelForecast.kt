package com.jay.sunshine.api.type

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by jay on 17/5/31.
 */
data class ModelForecast(var date: Long, var description: String, var high: Float,
                         var low: Float, var icon: String) : Parcelable {


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(date)
        dest.writeString(description)
        dest.writeFloat(high)
        dest.writeFloat(low)
        dest.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    protected constructor(source: Parcel) : this(
            source.readLong(),
            source.readString(),
            source.readFloat(),
            source.readFloat(),
            source.readString())


    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<ModelForecast> = object : Parcelable.Creator<ModelForecast> {
            override fun newArray(size: Int): Array<ModelForecast?> {
                return arrayOfNulls(size)
            }

            override fun createFromParcel(source: Parcel): ModelForecast? {
                return ModelForecast(source)
            }
        }
    }

}
