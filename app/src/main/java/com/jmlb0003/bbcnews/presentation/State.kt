package com.jmlb0003.bbcnews.presentation

sealed class State {

    object Initial : State()

    object Busy : State()

    object Done : State()

    object Empty : State()

    object Error : State()

}