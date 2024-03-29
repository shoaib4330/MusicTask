package com.tidal.tidaltask.domain.album.listing

import com.tidal.tidaltask.base.BaseView
import com.tidal.tidaltask.domain.album.model.Album

interface AlbumListingView : BaseView {
    fun showAlbums(albums: List<Album>)
}