package com.tidal.tidaltask.base

import android.os.Bundle
import android.widget.Toast
import com.tidal.tidaltask.R
import com.tidal.tidaltask.domain.artist.ui.SearchArtistFragment
import dagger.android.AndroidInjection

class HomeActivity : BaseFragmentActivity() {

    override fun getLayoutId(): Int = R.layout.activity_main

    fun processIntent(){
        val mBundle = Bundle()
        mBundle.putString(FRAGMENT_CLASS_NAME, SearchArtistFragment::class.java.name)
        intent.putExtras(mBundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        processIntent()
        super.onCreate(savedInstanceState)
        //AndroidInjection.inject(this)
        Toast.makeText(this, "HomeActivity created", Toast.LENGTH_SHORT).show()
    }
}
