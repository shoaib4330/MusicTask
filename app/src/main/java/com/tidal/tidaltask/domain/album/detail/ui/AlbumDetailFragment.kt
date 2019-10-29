package com.tidal.tidaltask.domain.album.detail.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide

import com.tidal.tidaltask.R
import com.tidal.tidaltask.base.BaseFragment
import com.tidal.tidaltask.domain.album.detail.AlbumDetailPresenter
import com.tidal.tidaltask.domain.album.detail.AlbumDetailView
import com.tidal.tidaltask.domain.album.model.Track
import com.tidal.tidaltask.util.Constants
import kotlinx.android.synthetic.main.fragment_album_detail.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : BaseFragment(), AlbumDetailView, TracksRecyclerAdapter.OnClickListener {

    @Inject
    lateinit var presenter: AlbumDetailPresenter
    private var rvAdapter: TracksRecyclerAdapter? = null
    private var albumId: Int? = null
    private var albumTitle: String? = null
    private var coverUrl: String? = null
    private var albumLabel: String? = null
    private var albumArtist: String? = null

    override fun getLayoutId(): Int = R.layout.fragment_album_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            albumId = arguments!!.getInt(Constants.ARG_PARAM2)
            albumTitle = arguments!!.getString(Constants.ARG_PARAM3)
            coverUrl = arguments!!.getString(Constants.ARG_PARAM4)
            albumLabel = arguments!!.getString(Constants.ARG_PARAM5)
            albumArtist = arguments!!.getString(Constants.ARG_PARAM6)
        }

        rvAdapter = TracksRecyclerAdapter(context = context, clickListener = this)

        presenter.attachView(this)
    }


    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)

        rvTracks.adapter = rvAdapter

        tvAlbumTitle.text = albumTitle

        albumArtist?.let { tvAlbumArtist.text = it }

        albumLabel?.let { tvVolume.text = it }

        Glide.with(parent.context)
            .load(coverUrl)
            .into(ivDetailAlbumCover)

        setupToolbar()

        presenter.getTracks(albumId)
    }

    fun setupToolbar() {
        toolbar?.show()
        toolbar?.title = toolbarTitle
        toolbar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun displayTracks(tracks: List<Track>) {
        rvAdapter?.setData(tracks, true)
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

    override fun onClick(track: Track) {
        // On Track Click, Do Nothing
    }

    /* ----- Fragment Creation Factory / Companion ------ */
    companion object {

        fun newInstance(
            albumId: Int,
            albumTitle: String?,
            coverUrl: String?,
            label: String?,
            albumArtist: String?
        ): AlbumDetailFragment {

            val fragment = AlbumDetailFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_PARAM2, albumId)
            args.putString(Constants.ARG_PARAM3, albumTitle)
            args.putString(Constants.ARG_PARAM4, coverUrl)
            args.putString(Constants.ARG_PARAM5, label)
            args.putString(Constants.ARG_PARAM6, albumArtist)
            fragment.arguments = args
            return fragment
        }
    }
}
