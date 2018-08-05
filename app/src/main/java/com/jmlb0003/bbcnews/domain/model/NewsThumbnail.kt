package com.jmlb0003.bbcnews.domain.model

import android.os.Parcel
import android.os.Parcelable
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "thumbnail", strict = false)
data class NewsThumbnail constructor(
        @field:Attribute(name = "url") var url: String = "") : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) = parcel.writeString(url)

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<NewsThumbnail> {
        override fun createFromParcel(parcel: Parcel) = NewsThumbnail(parcel)

        override fun newArray(size: Int): Array<NewsThumbnail?> = arrayOfNulls(size)
    }
}
