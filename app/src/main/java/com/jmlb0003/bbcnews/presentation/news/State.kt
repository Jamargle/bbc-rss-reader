package com.jmlb0003.bbcnews.presentation.news

sealed class State {

    object Initial : State()

    object Busy : State()

    object Done : State()

    object Empty : State()

}