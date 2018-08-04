package com.jmlb0003.bbcnews.presentation.news

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import com.jmlb0003.bbcnews.utils.Schedulers
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListViewModel
@Inject constructor(private val repository: NetworkNewsRepository,
                    private val schedulers: Schedulers) : ViewModel() {

    val newsList = ObservableField<List<String>>()
    val state = ObservableField<State>(State.Initial)

    private val disposables = CompositeDisposable()

    fun displayNewsFeed() {
        displayLoading()
        disposables.add(Single.create(SingleOnSubscribe<List<NewsItem>> { emitter -> emitter.onSuccess(repository.obtainNews()) })
                                .subscribeOn(schedulers.getBackgroundThread())
                                .observeOn(schedulers.getUiThread())
                                .subscribe(this::handleSuccessResult, this::handleErrorResult))
    }

    private fun displayLoading() {
        state.set(State.Busy)
    }

    private fun handleSuccessResult(news: List<NewsItem>) {
        newsList.set(news.map { newsItem -> newsItem.title })
        state.set(State.Done)
        if (news.isEmpty()) {
            state.set(State.Empty)
        } else {
            state.set(State.Done)
        }
    }

    private fun handleErrorResult(exception: Throwable) {
        //  TODO display some error
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
