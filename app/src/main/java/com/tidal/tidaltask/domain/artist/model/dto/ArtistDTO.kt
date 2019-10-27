package com.tidal.tidaltask.domain.artist.model.dto

import com.tidal.tidaltask.domain.artist.model.Artist

data class ArtistDTO constructor(
    val data: List<Artist>,
    val next: String?,
    val prev: String?,
    val total: Int?
)