package com.jmlb0003.bbcnews.domain.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel constructor(
        @field:ElementList(name = "item", inline = true, required = false)
        var items: MutableList<NewsItem> = emptyList<NewsItem>().toMutableList())
