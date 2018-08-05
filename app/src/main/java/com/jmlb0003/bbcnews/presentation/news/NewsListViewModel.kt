package com.jmlb0003.bbcnews.presentation.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import com.jmlb0003.bbcnews.presentation.State
import com.jmlb0003.bbcnews.presentation.navigation.NewsNavigator
import com.jmlb0003.bbcnews.utils.Schedulers
import com.jmlb0003.bbcnews.utils.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListViewModel
@Inject constructor(private val repository: NetworkNewsRepository,
                    private val navigator: NewsNavigator,
                    private val schedulers: Schedulers) : ViewModel() {

    val newsList = ObservableField<List<NewsItem>>()
    val state = ObservableField<State>(State.Initial)

    private val errorCallback = SingleLiveEvent<Throwable>()
    private val disposables = CompositeDisposable()

    fun getErrorCallback(): LiveData<Throwable> = errorCallback

    // region Display news list
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
        newsList.set(news)
        if (news.isEmpty()) {
            state.set(State.Empty)
        } else {
            state.set(State.Done)
        }
    }

    private fun handleErrorResult(exception: Throwable) {
        state.set(State.Error)
        errorCallback.postValue(exception)
    }
    //endregion

    // region Navigation
    fun getNavigationToDetails(): LiveData<NewsItem> = navigator.navigateToDetailsTrigger

    fun onNewsClicked(news: NewsItem) {
        navigator.navigateToDetailsTrigger.postValue(news)
    }

    fun getNavigationToSettings(): LiveData<Unit> = navigator.navigateToDeviceSettingsTrigger

    fun onGoToSettings() {
        navigator.navigateToDeviceSettingsTrigger.postValue(Unit)
    }
    //endregion

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}
