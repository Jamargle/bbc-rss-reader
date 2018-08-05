package com.jmlb0003.bbcnews.utils

import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.webkit.WebView
import android.widget.TextView
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.presentation.news.adapter.NewsAdapter
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class BindingAdaptersTest {

    @Test
    fun `on setNews given null news list do nothing`() {
        // Given
        val view = mock<RecyclerView>()

        // When
        setNews(view, null)

        // Then
        verifyZeroInteractions(view)
    }

    @Test
    fun `on setNews given news list bind list through NewsAdapter`() {
        // Given
        val view = mock<RecyclerView>()
        val adapter = mock<NewsAdapter>()
        whenever(view.adapter).thenReturn(adapter)
        val newsList = listOf(NewsItem(), NewsItem())

        // When
        setNews(view, newsList)

        // Then
        verify(adapter).bindNews(newsList)
    }

    @Test
    fun `on setNewsDetail given null news link do nothing`() {
        // Given
        val view = mock<WebView>()

        // When
        setNewsDetail(view, null)

        // Then
        verifyZeroInteractions(view)
    }

    @Test
    fun `on setNewsDetail given news link load article in the web view`() {
        // Given
        val view = mock<WebView>()
        val link = "any link"

        // When
        setNewsDetail(view, link)

        // Then
        verify(view).loadUrl(link)
    }

    @Test
    fun `on setFormattedDate given null date do nothing`() {
        // Given
        val view = mock<TextView>()

        // When
        setFormattedDate(view, null)

        // Then
        verifyZeroInteractions(view)
    }

    @Test
    fun `on setFormattedDate given a date in this minute show right now string`() {
        // Given
        val view = mock<TextView>()
        val context = mock<Context>()
        whenever(view.context).thenReturn(context)
        val resources = mock<Resources>()
        whenever(context.resources).thenReturn(resources)
        val rightNowText = "Right now"
        whenever(resources.getString(R.string.right_now)).thenReturn(rightNowText)
        val currentTime = Date()

        // When
        setFormattedDate(view, currentTime)

        // Then
        verify(view).text = rightNowText
    }

    @Test
    fun `on setFormattedDate given a date in this hour show one minute ago string`() {
        // Given
        val view = mock<TextView>()
        val context = mock<Context>()
        whenever(view.context).thenReturn(context)
        val resources = mock<Resources>()
        whenever(context.resources).thenReturn(resources)
        val minuteAgoText = "1 minute ago"
        whenever(resources.getQuantityString(R.plurals.minutes_ago, 1, 1)).thenReturn(minuteAgoText)

        val minutesAgoTime = Date(Date().time - 70 * 1000)  // 70 seconds ago = 1 minute ago

        // When
        setFormattedDate(view, minutesAgoTime)

        // Then
        verify(view).text = minuteAgoText
    }

    @Test
    fun `on setFormattedDate given a date in this hour show some minutes ago string`() {
        // Given
        val view = mock<TextView>()
        val context = mock<Context>()
        whenever(view.context).thenReturn(context)
        val resources = mock<Resources>()
        whenever(context.resources).thenReturn(resources)
        val minuteAgoText = "3 minutes ago"
        whenever(resources.getQuantityString(R.plurals.minutes_ago, 3, 3)).thenReturn(minuteAgoText)

        val minutesAgoTime = Date(Date().time - 200 * 1000)  // 200 seconds ago = 3 minutes ago

        // When
        setFormattedDate(view, minutesAgoTime)

        // Then
        verify(view).text = minuteAgoText
    }

    @Test
    fun `on setFormattedDate given a date in this day show hour ago string`() {
        // Given
        val view = mock<TextView>()
        val context = mock<Context>()
        whenever(view.context).thenReturn(context)
        val resources = mock<Resources>()
        whenever(context.resources).thenReturn(resources)
        val hourAgoText = "1 hour ago"
        whenever(resources.getQuantityString(R.plurals.hours_ago, 1, 1)).thenReturn(hourAgoText)

        val hoursAgoTime = Date(Date().time - 70 * 60 * 1000)  // 70 minutes ago = 1 hour ago

        // When
        setFormattedDate(view, hoursAgoTime)

        // Then
        verify(view).text = hourAgoText
    }

    @Test
    fun `on setFormattedDate given a date in this day show some hours ago string`() {
        // Given
        val view = mock<TextView>()
        val context = mock<Context>()
        whenever(view.context).thenReturn(context)
        val resources = mock<Resources>()
        whenever(context.resources).thenReturn(resources)
        val hoursAgoText = "3 hours ago"
        whenever(resources.getQuantityString(R.plurals.hours_ago, 3, 3)).thenReturn(hoursAgoText)

        val hoursAgoTime = Date(Date().time - 200 * 60 * 1000)  // 200 minutes ago = 3 hours ago

        // When
        setFormattedDate(view, hoursAgoTime)

        // Then
        verify(view).text = hoursAgoText
    }

    @Test
    fun `on setFormattedDate given a date not today show formatted date string`() {
        // Given
        val view = mock<TextView>()
        val daysAgoTime = Date(18572400000)  // 04 August 2018
        val daysAgoText = SimpleDateFormat("EEE, dd MMM", Locale.getDefault()).format(daysAgoTime)

        // When
        setFormattedDate(view, daysAgoTime)

        // Then
        verify(view).text = daysAgoText
    }

}
