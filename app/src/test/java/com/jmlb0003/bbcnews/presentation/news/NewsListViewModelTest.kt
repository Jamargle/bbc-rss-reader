package com.jmlb0003.bbcnews.presentation.news

import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import com.jmlb0003.bbcnews.presentation.State
import com.jmlb0003.bbcnews.presentation.navigation.NewsNavigator
import com.jmlb0003.bbcnews.utils.Schedulers
import com.jmlb0003.bbcnews.utils.SingleLiveEvent
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.internal.schedulers.ExecutorScheduler
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import java.util.*
import java.util.concurrent.Executor

class NewsListViewModelTest {

    private val repository = mock<NetworkNewsRepository>()
    private val navigatorToDetailsTrigger = mock<SingleLiveEvent<NewsItem>>()
    private val schedulers = mock<Schedulers>()
    private val immediateScheduler = object : Scheduler() {
        override fun createWorker(): Scheduler.Worker {
            return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
        }
    }

    private val viewModel = NewsListViewModel(repository, NewsNavigator(navigatorToDetailsTrigger), schedulers)

    @Before
    fun setUp() {
        whenever(schedulers.getBackgroundThread()).thenReturn(immediateScheduler)
        whenever(schedulers.getUiThread()).thenReturn(immediateScheduler)
    }

    @Test
    fun `on displayNewsFeed should show error when the list comes empty`() {
        // Given
        val error = Throwable()
        `when`(repository.obtainNews()).thenReturn(Single.error(error))

        // When
        viewModel.displayNewsFeed()

        // Then
        assertTrue(viewModel.state.get() is State.Error)
    }

    @Test
    fun `on displayNewsFeed should show empty state when success but the list comes empty`() {
        // Given
        `when`(repository.obtainNews()).thenReturn(Single.just(emptyList()))

        // When
        viewModel.displayNewsFeed()

        // Then
        assertTrue(viewModel.state.get() is State.Empty)
    }

    @Test
    fun `on displayNewsFeed should show done state when success`() {
        // Given
        val newsFeed = listOf(NewsItem(), NewsItem(), NewsItem())
        `when`(repository.obtainNews()).thenReturn(Single.just(newsFeed))

        // When
        viewModel.displayNewsFeed()

        // Then
        assertTrue(viewModel.state.get() is State.Done)
    }

    @Test
    fun `on displayNewsFeed should show ordered news by date on success`() {
        // Given
        val news1 = NewsItem(title = "news 1", publicationDate = Date(12300000))
        val news2 = NewsItem(title = "news 2", publicationDate = Date(45600000))
        val news3 = NewsItem(title = "news 3", publicationDate = Date(78900000))
        val newsFeed = listOf(news2, news3, news1)
        `when`(repository.obtainNews()).thenReturn(Single.just(newsFeed))

        // When
        viewModel.displayNewsFeed()

        // Then
        with(viewModel.newsList.get()) {
            assertEquals(news3, this?.get(0))
            assertEquals(news2, this?.get(1))
            assertEquals(news1, this?.get(2))
        }
    }

    @Test
    fun `on onNewsClicked should navigate to details`() {
        // Given
        val news = NewsItem(title = "news 1", publicationDate = Date(12300000))

        // When
        viewModel.onNewsClicked(news)

        // Then
        verify(navigatorToDetailsTrigger).postValue(news)
    }

}