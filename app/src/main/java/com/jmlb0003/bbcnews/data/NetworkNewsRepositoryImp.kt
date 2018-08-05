package com.jmlb0003.bbcnews.data

import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import io.reactivex.Single
import java.io.IOException

class NetworkNewsRepositoryImp(private val apiService: BBCNewsApiClient) : NetworkNewsRepository {

    override fun obtainNews(): Single<List<NewsItem>> {
        return Single.create { emitter ->
            try {
                apiService.getFeed().execute().body()?.let { newsFeed ->
                    emitter.onSuccess(newsFeed.channel.items)
                }
            } catch (e: IOException) {
                emitter.onError(Throwable(e.message))
            }
            if (!emitter.isDisposed) {
                emitter.onError(Throwable("We had an issue downloading the news"))
            }
        }
    }

}
