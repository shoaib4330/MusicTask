package com.tidal.tidaltask.base

import android.os.Bundle
import android.widget.Toast
import com.tidal.tidaltask.R
import com.tidal.tidaltask.domain.artist.ui.SearchArtistFragment
import dagger.android.AndroidInjection

class HomeActivity : BaseFragmentActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // load the initial fragment
        addFragment(
            SearchArtistFragment.newInstance(),
            clearBackStack = false,
            addToBackstack = false
        )
    }
}
