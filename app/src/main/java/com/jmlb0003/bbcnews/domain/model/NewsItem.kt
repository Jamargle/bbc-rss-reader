package com.jmlb0003.bbcnews.domain.model

import android.os.Parcel
import android.os.Parcelable
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.util.*

@Root(name = "item", strict = false)
data class NewsItem(
        // We need empty constructor for serialization so default values are needed
        @field:Element(name = "title", data = true, required = false) var title: String = "",
        @field:Element(name = "description", data = true, required = false) var description: String = "",
        @field:Element(name = "link", required = false) var link: String = "",
        @field:Element(name = "pubDate", required = false) var publicationDate: Date = Date(),
        @field:Element(name = "thumbnail", required = false) var image: NewsThumbnail = NewsThumbnail()) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            Date(parcel.readLong()),
            parcel.readParcelable(NewsThumbnail::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(link)
        parcel.writeLong(publicationDate.time)
        parcel.writeParcelable(image, flags)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<NewsItem> {
        override fun createFromParcel(parcel: Parcel) = NewsItem(parcel)

        override fun newArray(size: Int): Array<NewsItem?> = arrayOfNulls(size)
    }
}
