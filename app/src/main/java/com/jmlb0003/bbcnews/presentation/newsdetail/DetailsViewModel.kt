package com.jmlb0003.bbcnews.presentation.newsdetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.jmlb0003.bbcnews.presentation.State
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsViewModel
@Inject constructor() : ViewModel() {

    val state = ObservableField<State>(State.Initial)
    val newsLink = ObservableField<String>()

    fun setNewsItemToShow(newsItemLink: String) {
        state.set(State.Busy)
        newsLink.set(newsItemLink)
    }

    fun displayLoading() {
        state.set(State.Busy)
    }

    fun hideLoading() {
        state.set(State.Done)
    }

}
