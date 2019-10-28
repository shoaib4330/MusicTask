package com.tidal.tidaltask.domain.album.detail

import com.tidal.tidaltask.backend.DeezerApiGateway
import com.tidal.tidaltask.backend.NetworkHelper
import com.tidal.tidaltask.backend.ServiceCallback
import com.tidal.tidaltask.base.BasePresenter
import com.tidal.tidaltask.domain.album.model.dto.AlbumDetailDTO
import com.tidal.tidaltask.util.Constants
import javax.inject.Inject

class AlbumDetailPresenter @Inject constructor(
    private val deezerApiGateway: DeezerApiGateway,
    private val networkHelper: NetworkHelper
) : BasePresenter<AlbumDetailView>(), ServiceCallback<AlbumDetailDTO> {

    fun getTracks(albumId: Int?) {
        albumId?.let {
            view?.onOffProgressBar(true)
            networkHelper.serviceCall(deezerApiGateway.queryAlbumTracks(albumId), this)
        } ?: run { view?.showError(Constants.ERROR_MESSAGE) }
    }

    override fun onSuccess(response: AlbumDetailDTO) {
        view?.displayTracks(response.data)
        view?.onOffProgressBar(false)
    }

    override fun onFailure(error: String, statusCode: Int, errorCodeString: String) {
        view?.showError(error)
        view?.onOffProgressBar(false)
    }

}