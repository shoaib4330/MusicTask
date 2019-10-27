package com.tidal.tidaltask.domain.artist.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.tidal.tidaltask.R
import com.tidal.tidaltask.domain.artist.model.Artist
import com.tidal.tidaltask.base.BaseFragment
import com.tidal.tidaltask.domain.artist.ArtistView
import kotlinx.android.synthetic.main.search_artist_fragment.*

class SearchArtistFragment : BaseFragment(), ArtistView ,ArtistRecyclerAdapter.OnClickListener {

    override fun getLayoutId(): Int = R.layout.search_artist_fragment

    private var rvAdapter: ArtistRecyclerAdapter =
        ArtistRecyclerAdapter(
            context = activity as Context,
            clickListener = this
        )

    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)
        rv_artists.adapter = rvAdapter
    }

    override fun showArtists(artists: List<Artist>) {
        this.rvAdapter.setData(artists, true)
    }

    override fun showError(message: String) {
        Toast.makeText(context, "showError called", Toast.LENGTH_SHORT).show()
    }

    override fun onOffProgressBar(toShow: Boolean) {
        if(toShow)
            fragmentHelper.showLoadingDialog()
        else
            fragmentHelper.hideLoadingDialog()
    }

    override fun onClick(artist: Artist) {
        Toast.makeText(context, "Artist List OnClick() called", Toast.LENGTH_SHORT).show()
    }
}