package com.jmlb0003.bbcnews.presentation.news

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListViewModel
@Inject constructor(private val repository: NetworkNewsRepository) : ViewModel() {

    val newsList = ObservableField<List<String>>()
    private val disposables = CompositeDisposable()

    fun fetchNewsFeed() {
        disposables.add(Single.create(SingleOnSubscribe<List<NewsItem>> { emitter -> emitter.onSuccess(repository.obtainNews()) })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::handleSuccessResult, this::handleErrorResult))
    }

    private fun handleSuccessResult(news: List<NewsItem>) {
        newsList.set(news.map { newsItem -> newsItem.title })
    }

    private fun handleErrorResult(exception: Throwable) {
        //  TODO display some error
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
