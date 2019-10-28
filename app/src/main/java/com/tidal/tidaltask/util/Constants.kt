package com.tidal.tidaltask.util

object Constants {
    const val EMPTY_STRING = ""
    const val ERROR_MESSAGE = "Something went wrong"

    const val ARG_PARAM1 = "artistId"

    const val ARG_PARAM2 = "albumId"
    const val ARG_PARAM3 = "coverUrl"
    const val ARG_PARAM4 = "albumTitle"
    const val ARG_PARAM5 = "label"
    const val ARG_PARAM6 = "albumArtist"
}

object NetworkConstants {
    /* Remote API Calls (Deezer) Constants*/
    const val BASE_URL = "http://api.deezer.com"
    const val CONNECTION_TIMEOUT = 10L /* 10 seconds */
    const val REQUEST_FAILURE_MESSAGE = "Oops! Gotta try again"
    const val PAGE_SIZE = 15
    const val SORT_ORDERS_DESC = "desc"
    const val SORT_ORDERS_ASC = "asc"
}