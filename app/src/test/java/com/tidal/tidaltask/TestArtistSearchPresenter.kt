package com.tidal.tidaltask

import com.nhaarman.mockito_kotlin.capture
import com.nhaarman.mockito_kotlin.firstValue
import com.nhaarman.mockito_kotlin.times
import com.tidal.tidaltask.backend.DeezerApiGateway
import com.tidal.tidaltask.backend.NetworkHelper
import com.tidal.tidaltask.backend.ServiceCallback
import com.tidal.tidaltask.domain.artist.SearchArtistPresenter
import com.tidal.tidaltask.domain.artist.ArtistView
import com.tidal.tidaltask.domain.artist.model.dto.SearchArtistResponseDTO
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.mock
import retrofit2.Response

class TestArtistSearchPresenter {

    @Mock
    lateinit var searchArtistView: ArtistView

    @Mock
    lateinit var deezerApiGateway: DeezerApiGateway

    @Mock
    lateinit var networkHelper: NetworkHelper

    @InjectMocks
    lateinit var searchArtistSearchPresenter: SearchArtistPresenter

    @Mock
    lateinit var artistNetworkCallSingle: Single<Response<SearchArtistResponseDTO>>

    private val artistQuery : String = "jay z"

    @Captor
    private lateinit var capturedServiceCallbackInterface : ArgumentCaptor<ServiceCallback<SearchArtistResponseDTO>>

    @Before
    fun setupPriorPerMethod() {
        MockitoAnnotations.initMocks(this)
        searchArtistSearchPresenter.attachView(searchArtistView)
    }

    @Test
    fun when_findArtists_successResponse() {
        val responseDTO : SearchArtistResponseDTO = mock(SearchArtistResponseDTO::class.java)

        Mockito.`when`(deezerApiGateway.queryArtists(anyString())).thenReturn(artistNetworkCallSingle)
        searchArtistSearchPresenter.findArtists(artistQuery)
        Mockito.verify(networkHelper, times(1)).remoteCall(com.nhaarman.mockito_kotlin.eq(artistNetworkCallSingle), capture(capturedServiceCallbackInterface))
        capturedServiceCallbackInterface.firstValue.onSuccess(responseDTO)

        Mockito.verify(searchArtistView, times(1)).showArtists(responseDTO.data)
    }

    @Test
    fun when_findArtist_emptySearchString_doesNothing(){
        searchArtistSearchPresenter.findArtists("")
        Mockito.verifyZeroInteractions(networkHelper)
        Mockito.verifyZeroInteractions(searchArtistView)
    }

}