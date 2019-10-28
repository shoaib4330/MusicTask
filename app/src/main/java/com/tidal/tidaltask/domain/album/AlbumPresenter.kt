package com.tidal.tidaltask.domain.album

import com.tidal.tidaltask.backend.DeezerApiGateway
import com.tidal.tidaltask.backend.NetworkHelper
import com.tidal.tidaltask.backend.ServiceCallback
import com.tidal.tidaltask.base.BasePresenter
import com.tidal.tidaltask.domain.album.model.dto.AlbumsOfArtistResponseDTO
import javax.inject.Inject

class AlbumPresenter @Inject constructor(
    private val deezerApiGateway: DeezerApiGateway,
    private val networkHelper: NetworkHelper
) : BasePresenter<AlbumView>(), ServiceCallback<AlbumsOfArtistResponseDTO> {

    fun getAlbums(artistId: Int) {
        artistId?.let {
            view?.onOffProgressBar(true)
            networkHelper.serviceCall(deezerApiGateway.queryAlbumsOfArtist(artistId), this)
        }
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