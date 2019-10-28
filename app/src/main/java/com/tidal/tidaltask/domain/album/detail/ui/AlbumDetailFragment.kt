package com.tidal.tidaltask.domain.album.detail.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.tidal.tidaltask.R
import com.tidal.tidaltask.base.BaseFragment
import com.tidal.tidaltask.domain.album.detail.AlbumDetailPresenter
import com.tidal.tidaltask.domain.album.detail.AlbumDetailView
import com.tidal.tidaltask.domain.album.listing.AlbumPresenter
import com.tidal.tidaltask.domain.album.listing.ui.AlbumRecyclerAdapter
import com.tidal.tidaltask.domain.album.model.Track
import com.tidal.tidaltask.util.Constants
import kotlinx.android.synthetic.main.fragment_album_detail.*
import kotlinx.android.synthetic.main.fragment_album_listing.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class AlbumDetailFragment : BaseFragment(), AlbumDetailView, TracksRecyclerAdapter.OnClickListener {

    @Inject
    lateinit var presenter: AlbumDetailPresenter
    private var rvAdapter: TracksRecyclerAdapter? = null
    private var albumId: Int? = null

    override fun getLayoutId(): Int = R.layout.fragment_album_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null)
            albumId = arguments!!.getInt(Constants.ARG_PARAM2)

        rvAdapter = TracksRecyclerAdapter(context = context, clickListener = this)

        presenter.attachView(this)
    }


    override fun initViews(parent: View, savedInstanceState: Bundle?) {
        super.initViews(parent, savedInstanceState)
        rvTracks.adapter = rvAdapter
        presenter.getTracks(albumId)
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

        fun newInstance(param2: Int): AlbumDetailFragment {
            val fragment = AlbumDetailFragment()
            val args = Bundle()
            args.putInt(Constants.ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
