package com.tidal.tidaltask.domain.artist

import com.tidal.tidaltask.backend.DeezerApiGateway
import com.tidal.tidaltask.backend.NetworkHelper
import com.tidal.tidaltask.backend.ServiceCallback
import com.tidal.tidaltask.base.BasePresenter
import com.tidal.tidaltask.domain.artist.model.dto.SearchArtistResponseDTO
import javax.inject.Inject

class SearchArtistPresenter : BasePresenter<ArtistView>, ServiceCallback<SearchArtistResponseDTO> {

    private val deezerApiGateway: DeezerApiGateway
    private val networkHelper: NetworkHelper

    @Inject
    constructor(deezerApiGateway: DeezerApiGateway, networkHelper: NetworkHelper) {
        this.deezerApiGateway = deezerApiGateway
        this.networkHelper = networkHelper
    }

    fun findArtists(searchParam: String) {
        if (searchParam.isNotBlank())
            searchParam.let {
                view?.onOffProgressBar(true)
                networkHelper.remoteCall(deezerApiGateway.queryArtists(searchParam), this)
            }
    }

    override fun onSuccess(response: SearchArtistResponseDTO) {
        view?.showArtists(response.data)
        view?.onOffProgressBar(false)
    }

    override fun onFailure(error: String, statusCode: Int, errorCodeString: String) {
        view?.onOffProgressBar(false)
        view?.showError(error)
    }
}