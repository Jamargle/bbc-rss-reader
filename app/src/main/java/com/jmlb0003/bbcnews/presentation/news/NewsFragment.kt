package com.jmlb0003.bbcnews.presentation.news

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.di.ViewModelFactory
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.domain.repository.NetworkNewsRepository
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var repository: NetworkNewsRepository

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_news_list, container, false)
        initRecyclerView(rootView)
        initViewModel()
        return rootView
    }

    private fun initRecyclerView(rootView: View) {
        val newsView = rootView.news_recycler_view
        newsView.setHasFixedSize(true)

        val disposables = CompositeDisposable()

        disposables.add(Single.create(SingleOnSubscribe<List<NewsItem>> { emitter -> emitter.onSuccess(repository.obtainNews()) })
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(this::displaySuccess, this::displayError))
    }

    fun displaySuccess(news: List<NewsItem>) {
        Toast.makeText(activity, "Downloaded ${news.size}!!!", Toast.LENGTH_SHORT).show()
    }

    fun displayError(exception: Throwable) {
        Toast.makeText(activity, exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun initViewModel() {
        val recipeListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

}
