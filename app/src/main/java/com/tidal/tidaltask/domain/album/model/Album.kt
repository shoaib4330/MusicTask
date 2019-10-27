package com.tidal.tidaltask.domain.album.model

import com.tidal.tidaltask.domain.artist.model.Artist

import java.io.Serializable

data class Album constructor(
    val id: String?,
    val title: String?,
    val upc: String?,
    val link: String?,
    val share: String?,
    val cover: String?,
    val cover_small: String?,
    val cover_medium: String?,
    val cover_big: String?,
    val cover_xl: String?,
    val genre_id: Int?,
    val label: String?,
    val nb_tracks: Int?,
    val duration: Int?,
    val record_type: String?,
    val artist: Artist?,
    val tracklist: String?
) : Serializable