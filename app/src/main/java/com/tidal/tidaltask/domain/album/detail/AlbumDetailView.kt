package com.tidal.tidaltask.domain.album.detail

import com.tidal.tidaltask.base.BaseView
import com.tidal.tidaltask.domain.album.model.Track

interface AlbumDetailView : BaseView {
    fun displayTracks(tracks: List<Track>)
}