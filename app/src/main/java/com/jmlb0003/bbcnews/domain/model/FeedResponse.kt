package com.jmlb0003.bbcnews.domain.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class FeedResponse constructor(
        @field:Element(name = "channel", required = false) var channel: Channel = Channel())
