package com.tidal.tidaltask.domain.album.model.dto

import com.tidal.tidaltask.domain.album.model.Track

data class AlbumDetailDTO(
    val data: List<Track>,
    val total: Int?
)