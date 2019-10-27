package com.tidal.tidaltask.domain.artist

import com.tidal.tidaltask.base.BaseView
import com.tidal.tidaltask.domain.artist.model.Artist

interface ArtistView : BaseView {
    fun showArtists(artists: List<Artist>)
}