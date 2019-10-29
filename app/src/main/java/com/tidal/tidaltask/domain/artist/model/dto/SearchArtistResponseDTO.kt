package com.tidal.tidaltask.domain.artist.model.dto

import com.tidal.tidaltask.domain.artist.model.Artist

data class SearchArtistResponseDTO constructor(
    val data: List<Artist>,
    val next: String?,
    val prev: String?,
    val total: Int?
)