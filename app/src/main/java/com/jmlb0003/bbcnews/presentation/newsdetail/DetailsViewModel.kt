package com.jmlb0003.bbcnews.presentation.newsdetail

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailsViewModel
@Inject constructor() : ViewModel() {

    val newsLink = ObservableField<String>()

    fun setNewsItemToShow(newsItemLink: String) {
        newsLink.set(newsItemLink)
    }

}
