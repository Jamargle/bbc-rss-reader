package com.jmlb0003.bbcnews.presentation.news

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news_list.view.*
import javax.inject.Inject

class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        newsView.adapter = NewsAdapter(mutableListOf("ASDasdas 1 ", "asdasfdasd ra", "asdasfasgaodna sodnas"))
    }

    private fun initViewModel() {
        val recipeListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
    }

}
