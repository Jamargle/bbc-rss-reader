package com.jmlb0003.bbcnews.presentation.news

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import com.jmlb0003.bbcnews.R
import com.jmlb0003.bbcnews.di.ViewModelFactory
import com.jmlb0003.bbcnews.presentation.newsdetail.DetailActivity
import com.jmlb0003.bbcnews.presentation.newsdetail.DetailsFragment
import com.jmlb0003.bbcnews.utils.isForTablet
import com.jmlb0003.bbcnews.utils.viewExists
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
    }

    override fun onStart() {
        super.onStart()
        subscribeToEvents()
    }

    private fun initToolbar() = supportActionBar?.let { actionBar ->
        with(actionBar) {
            setDisplayUseLogoEnabled(true)
            setDisplayShowHomeEnabled(true)
            setLogo(R.drawable.ic_news)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_news_list, menu)
        menu?.findItem(R.id.action_search)?.let {
            (it.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    // TODO Implement onQueryTextSubmit
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    // TODO Implement onQueryTextChange
                    return false
                }
            })
        }
        return true
    }

    private fun subscribeToEvents() {
        val newsListViewModel = ViewModelProviders.of(this, viewModelFactory).get(NewsListViewModel::class.java)

        // Display errors
        newsListViewModel.getErrorCallback().observe(this, Observer {
            Toast.makeText(this, "We had an issue. ${it?.message}", Toast.LENGTH_SHORT).show()
        })

        // Navigation
        newsListViewModel.getNavigationToDetails().observe(this, Observer { articleToShow ->
            articleToShow?.let {
                if (resources.isForTablet() && viewExists(R.id.news_details_container)) {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.news_details_container, DetailsFragment.newInstance(articleToShow))
                            .addToBackStack(articleToShow.title)
                            .commit()
                } else {
                    val intent = Intent(this, DetailActivity::class.java)
                    startActivity(intent.putExtras(DetailActivity.newBundle(articleToShow)))
                }
            }
        })
        newsListViewModel.getNavigationToSettings().observe(this, Observer { it ->
            it?.let {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
        })
    }

}
