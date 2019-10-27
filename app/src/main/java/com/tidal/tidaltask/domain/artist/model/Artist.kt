package com.tidal.tidaltask.domain.artist.model

import java.io.Serializable

data class Artist constructor(
    val id: Int? ,
    val name: String?,
    val link: String?,
    val share: String?,
    val picture: String?,
    val picture_small: String?,
    val picture_medium: String?,
    val picture_big: String,
    val picture_xl: String?,
    val nb_album: Int?,
    val nb_fan: Int?,
    val radio: Boolean?,
    val tracklist: String?
) : Serializable