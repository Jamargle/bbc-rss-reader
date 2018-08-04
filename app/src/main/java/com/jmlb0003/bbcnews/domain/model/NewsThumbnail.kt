package com.jmlb0003.bbcnews.domain.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "thumbnail", strict = false)
data class NewsThumbnail constructor(
        @field:Attribute(name = "url") var url: String = "")
