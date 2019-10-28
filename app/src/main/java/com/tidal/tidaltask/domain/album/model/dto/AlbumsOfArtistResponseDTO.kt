package com.tidal.tidaltask.domain.album.model.dto

import com.tidal.tidaltask.domain.album.model.Album
import java.io.Serializable

data class AlbumsOfArtistResponseDTO constructor(
    val data: List<Album>,
    val next: String?,
    val prev: String?,
    val total: Int?
) : Serializable