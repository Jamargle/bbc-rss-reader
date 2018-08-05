package com.jmlb0003.bbcnews.presentation.newsdetail

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.domain.model.NewsItem
import com.jmlb0003.bbcnews.presentation.news.MainActivity
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

        val article = intent.getParcelableExtra<NewsItem>(ARTICLE_TO_SHOW)
        initToolbar(article.title)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .add(R.id.news_details_container, DetailsFragment.newInstance(article))
                    .commit()
        }
    }

    private fun initToolbar(title: String) = supportActionBar?.let { actionBar ->
        with(actionBar) {
            setDisplayHomeAsUpEnabled(true)
            setTitle(title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {

        private const val ARTICLE_TO_SHOW = "Key:article_to_show"

        fun newBundle(article: NewsItem) = Bundle().apply {
            putParcelable(ARTICLE_TO_SHOW, article)
        }

    }

}
