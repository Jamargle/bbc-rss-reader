package com.jmlb0003.bbcnews.presentation.newsdetail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.jmlb0003.bbcnews.BR
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.di.ViewModelFactory
import com.jmlb0003.bbcnews.domain.model.NewsItem
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_details.view.*
import javax.inject.Inject

class DetailsFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_details, container, false)
        arguments?.let {
            val detailsViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailsViewModel::class.java)
            initWebView(rootView, detailsViewModel)
            val newsItem = it.getParcelable<NewsItem>(NEWS_ITEM_TO_SHOW)
            detailsViewModel.setNewsItemToShow(newsItem.link)
            initDataBinding(rootView, detailsViewModel)
        }
        return rootView
    }

    private fun initWebView(rootView: View, detailsViewModel: DetailsViewModel) {
        rootView.news_details_view.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                detailsViewModel.displayLoading()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                detailsViewModel.hideLoading()
            }

        }
    }

    private fun initDataBinding(rootView: View, detailsViewModel: DetailsViewModel) {
        DataBindingUtil.bind<ViewDataBinding>(rootView)?.apply {
            setVariable(BR.viewModel, detailsViewModel)
        }
    }

    companion object {

        private const val NEWS_ITEM_TO_SHOW = "Key:news_item_to_show"

        fun newInstance(newsItem: NewsItem) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(NEWS_ITEM_TO_SHOW, newsItem)
            }
        }
    }

}
