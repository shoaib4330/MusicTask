package com.tidal.tidaltask.domain.album.listing

import com.tidal.tidaltask.backend.DeezerApiGateway
import com.tidal.tidaltask.backend.NetworkHelper
import com.tidal.tidaltask.backend.ServiceCallback
import com.tidal.tidaltask.base.BasePresenter
import com.tidal.tidaltask.domain.album.model.dto.AlbumsOfArtistResponseDTO
import com.tidal.tidaltask.util.Constants
import javax.inject.Inject

class AlbumListingPresenter @Inject constructor(
    private val deezerApiGateway: DeezerApiGateway,
    private val networkHelper: NetworkHelper
) : BasePresenter<AlbumView>(), ServiceCallback<AlbumsOfArtistResponseDTO> {

    fun getAlbums(artistId: Int?) {
        artistId?.let {
            view?.onOffProgressBar(true)
            networkHelper.remoteCall(deezerApiGateway.queryAlbumsOfArtist(artistId), this)
        } ?: run { view?.showError(Constants.ERROR_MESSAGE) }
    }

    override fun onSuccess(response: AlbumsOfArtistResponseDTO) {
        view?.showAlbums(response.data)
        view?.onOffProgressBar(false)
    }

    override fun onFailure(error: String, statusCode: Int, errorCodeString: String) {
        view?.showError(error)
        view?.onOffProgressBar(false)
    }

}