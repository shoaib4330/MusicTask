package com.tidal.tidaltask.domain.artist.ui

import android.os.Bundle
import android.os.Handler
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.tidal.tidaltask.R
import com.tidal.tidaltask.base.BaseFragment
import com.tidal.tidaltask.domain.artist.ArtistPresenter
import com.tidal.tidaltask.domain.artist.ArtistView
import com.tidal.tidaltask.domain.artist.model.Artist
import kotlinx.android.synthetic.main.search_artist_fragment.*
import javax.inject.Inject

class SearchArtistFragment : BaseFragment(), ArtistView, ArtistRecyclerAdapter.OnClickListener {

    override fun getLayoutId(): Int = R.layout.search_artist_fragment

    @Inject
    lateinit var presenter: ArtistPresenter

    private lateinit var rvAdapter: ArtistRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View? = super.onCreateView(inflater, container, savedInstanceState)

        rvAdapter = ArtistRecyclerAdapter(
            context = context,
            clickListener = this
        )

        return v
    }

    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)
        rv_artists.adapter = rvAdapter
        initiate()
    }

    private fun initiate() {
        configureArtistSearchView()
        presenter.attachView(this)
        //presenter.findArtists("eminem")
    }

    private fun clearArtistsList(){
        this.rvAdapter?.clearData()
    }

    private fun configureArtistSearchView() {
        svArtistSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            private val handler : Handler = Handler()

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { presenter.findArtists(query) }
                return true // report action handled
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    when {
                        it.length > 1 -> {
                            handler.removeCallbacks(null) // cancel previously sent messages
                            handler.postDelayed({ presenter.findArtists(it) }, 1000L)
                        }
                        it.isBlank() -> clearArtistsList()
                        else -> {
                            // when length is one, lets not do anything
                        }
                    }
                } ?: clearArtistsList()
                return true // report action handled
            }
        })
    }

    override fun showArtists(artists: List<Artist>) {
        this.rvAdapter.setData(artists, true)
    }

    override fun showError(message: String) {
        Toast.makeText(context, "showError called", Toast.LENGTH_SHORT).show()
    }

    override fun onOffProgressBar(toShow: Boolean) {
        if (toShow)
            fragmentHelper.showLoadingDialog()
        else
            fragmentHelper.hideLoadingDialog()
    }

    override fun onClick(artist: Artist) {
        Toast.makeText(context, "Artist List OnClick() called", Toast.LENGTH_SHORT).show()
    }
}