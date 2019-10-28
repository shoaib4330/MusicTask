package com.tidal.tidaltask.domain.artist.ui

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.tidal.tidaltask.R
import com.tidal.tidaltask.base.BaseFragment
import com.tidal.tidaltask.domain.album.listing.ui.AlbumListingFragment
import com.tidal.tidaltask.domain.artist.ArtistPresenter
import com.tidal.tidaltask.domain.artist.ArtistView
import com.tidal.tidaltask.domain.artist.model.Artist
import kotlinx.android.synthetic.main.search_artist_fragment.*
import java.util.*
import javax.inject.Inject

class SearchArtistFragment : BaseFragment(), ArtistView, ArtistRecyclerAdapter.OnClickListener {

    override fun getLayoutId(): Int = R.layout.search_artist_fragment

    @Inject
    lateinit var presenter: ArtistPresenter

    private lateinit var rvAdapter: ArtistRecyclerAdapter

    private val handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rvAdapter = ArtistRecyclerAdapter(context = context, clickListener = this)

        presenter.attachView(this)
    }

    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)
        rv_artists.adapter = rvAdapter
        configureArtistSearchView()
    }

    override fun onStart() {
        super.onStart()
        toolbar?.hide() // hide the toolbar
    }

    private fun clearArtistsList() {
        this.rvAdapter?.clearData()
    }

    override fun showArtists(artists: List<Artist>) {
        this.rvAdapter.setData(artists, true)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOffProgressBar(toShow: Boolean) {
        if (toShow)
            fragmentHelper.showLoadingDialog()
        else
            fragmentHelper.hideLoadingDialog()
    }

    override fun onClick(artist: Artist) {
        artist.id?.let {
            fragmentHelper.replaceFragment(
                AlbumListingFragment.newInstance(artist.id, artist.name!!),
                clearBackStack = false,
                addToBackstack = true
            )
        }
    }

    private fun configureArtistSearchView() {
        svArtistSearch?.setOnClickListener { svArtistSearch?.onActionViewExpanded() }

        val searchEditText: EditText? =
            svArtistSearch?.findViewById((androidx.appcompat.R.id.search_src_text))

        searchEditText?.addTextChangedListener(object : TextWatcher {

            var timer: Timer? = null

            override fun afterTextChanged(p0: Editable?) {
                val text: String? = p0?.toString() // get the text
                timer = Timer() // user typed something, start timer
                timer!!.schedule(object : TimerTask() {
                    override fun run() {
                        text?.let {
                            when {
                                it.isNotEmpty() -> {
                                    handler.post {presenter.findArtists(it)}
                                }
                                else -> handler.post{clearArtistsList()}
                            }
                        } ?: handler.post{clearArtistsList()}
                    }
                }, 600)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing here
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                timer?.cancel() // user is typing, cancel already started timers
            }

        })
    }

    /* ----- Fragment Creation Factory / Companion ------ */
    companion object {
        fun newInstance(): SearchArtistFragment {
            return SearchArtistFragment()
        }
    }
}