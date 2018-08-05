package com.jmlb0003.bbcnews.presentation.news

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import com.jmlb0003.bbcnews.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
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

}
