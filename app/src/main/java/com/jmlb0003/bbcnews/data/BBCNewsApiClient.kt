package com.jmlb0003.bbcnews.data

import com.jmlb0003.bbcnews.domain.model.FeedResponse
import retrofit2.Call
import retrofit2.http.GET

interface BBCNewsApiClient {

    @GET("news/world/rss.xml")
    fun getFeed(): Call<FeedResponse>

}
