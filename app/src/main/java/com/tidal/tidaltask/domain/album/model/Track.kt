package com.tidal.tidaltask.domain.album.model

import com.tidal.tidaltask.domain.artist.model.Artist

data class Track(
    val id: Int?,
    val readable: Boolean?,
    val title: String?,
    val title_short: String?,
    val title_version: String?,
    val isrc: String?,
    val link: String?,
    val duration: String?,
    val track_position: Int,
    val artist: Artist?
)