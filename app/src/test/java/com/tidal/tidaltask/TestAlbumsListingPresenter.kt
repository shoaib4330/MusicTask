package com.tidal.tidaltask

import android.provider.SyncStateContract
import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.firstValue
import com.nhaarman.mockito_kotlin.times
import com.tidal.tidaltask.backend.DeezerApiGateway
import com.tidal.tidaltask.backend.NetworkHelper
import com.tidal.tidaltask.backend.ServiceCallback
import com.tidal.tidaltask.domain.album.listing.AlbumListingPresenter
import com.tidal.tidaltask.domain.album.listing.AlbumListingView
import com.tidal.tidaltask.domain.album.model.dto.AlbumsOfArtistResponseDTO
import com.tidal.tidaltask.util.Constants
import com.tidal.tidaltask.util.NetworkConstants
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Response

class TestAlbumsListingPresenter {
    @Mock
    lateinit var albumListingView: AlbumListingView

    @Mock
    lateinit var deezerApiGateway: DeezerApiGateway

    @Mock
    lateinit var networkHelper: NetworkHelper

    @InjectMocks
    lateinit var albumListingPresenter: AlbumListingPresenter

    @Mock
    lateinit var albumListNetworkCallSingle: Single<Response<AlbumsOfArtistResponseDTO>>

    private val artistId : Int = 13

    private val errorMessage : String = Constants.ERROR_MESSAGE

    private val errorMessageNetwork : String = NetworkConstants.REQUEST_FAILURE_MESSAGE

    @Captor
    private lateinit var capturedServiceCallbackInterface : ArgumentCaptor<ServiceCallback<AlbumsOfArtistResponseDTO>>

    @Before
    fun setupPriorPerMethod() {
        MockitoAnnotations.initMocks(this)
        albumListingPresenter.attachView(albumListingView)
    }

    @Test
    fun `when findAlbums successResponse`() {
        val responseDTO : AlbumsOfArtistResponseDTO =
            Mockito.mock(AlbumsOfArtistResponseDTO::class.java)

        Mockito.`when`(deezerApiGateway.queryAlbumsOfArtist(ArgumentMatchers.anyInt())).thenReturn(albumListNetworkCallSingle)
        albumListingPresenter.getAlbums(artistId)
        Mockito.verify(networkHelper, times(1)).remoteCall(com.nhaarman.mockito_kotlin.eq(albumListNetworkCallSingle), capture(capturedServiceCallbackInterface))
        capturedServiceCallbackInterface.firstValue.onSuccess(responseDTO)

        Mockito.verify(albumListingView, times(1)).showAlbums(responseDTO.data)
    }

    @Test
    fun `when findAlbums fails`(){
        val responseDTO : AlbumsOfArtistResponseDTO =
            Mockito.mock(AlbumsOfArtistResponseDTO::class.java)

        Mockito.`when`(deezerApiGateway.queryAlbumsOfArtist(ArgumentMatchers.anyInt())).thenReturn(albumListNetworkCallSingle)
        albumListingPresenter.getAlbums(artistId)
        Mockito.verify(networkHelper, times(1)).remoteCall(com.nhaarman.mockito_kotlin.eq(albumListNetworkCallSingle), capture(capturedServiceCallbackInterface))
        capturedServiceCallbackInterface.firstValue.onFailure(errorMessageNetwork)

        Mockito.verify(albumListingView, times(1)).showError(errorMessageNetwork)
    }

    @Test
    fun `when findAlbums nullId doesNothing`(){
        albumListingPresenter.getAlbums(null)
        Mockito.verifyZeroInteractions(networkHelper)
        Mockito.verify(albumListingView, times(1)).showError(errorMessage)
    }

}