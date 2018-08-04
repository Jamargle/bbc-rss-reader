package com.jmlb0003.bbcnews.domain.model

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
        @field:Element(name = "thumbnail", required = false) var image: NewsThumbnail = NewsThumbnail())
