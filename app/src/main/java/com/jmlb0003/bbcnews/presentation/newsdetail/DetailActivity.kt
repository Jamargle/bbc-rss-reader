package com.jmlb0003.bbcnews.presentation.newsdetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jmlb0003.bbcnews.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.news_details_container, DetailsFragment.newInstance(intent.getStringExtra(ARTICLE_TO_SHOW)))
                    .commit()
        }
    }

    companion object {

        private const val ARTICLE_TO_SHOW = "Key:article_link_to_show"

        fun newBundle(articleLinkToShow: String) = Bundle().apply {
            putString(ARTICLE_TO_SHOW, articleLinkToShow)
        }

    }

}
