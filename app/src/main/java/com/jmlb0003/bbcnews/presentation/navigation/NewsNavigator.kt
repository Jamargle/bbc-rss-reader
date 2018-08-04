package com.jmlb0003.bbcnews.presentation.navigation

import android.arch.lifecycle.MutableLiveData
import com.jmlb0003.bbcnews.domain.model.NewsItem

data class NewsNavigator(val navigateToDetailsTrigger: MutableLiveData<NewsItem> = MutableLiveData())
