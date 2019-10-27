package com.tidal.tidaltask.backend;

import com.tidal.tidaltask.domain.album.model.AlbumFindRemoteResponseDTO;
import com.tidal.tidaltask.domain.album.model.DetailedAlbumResponseDTO;
import com.tidal.tidaltask.domain.artist.model.dto.ArtistAlbumsResponseDTO;
import com.tidal.tidaltask.domain.artist.model.dto.ArtistDTO;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeezerApiGateway {

    /* ------- Album Endpoints ------- */
    @GET(value = "/search/album")
    Single<Response<AlbumFindRemoteResponseDTO>> queryAlbums(@Query("q") String param);

    @GET(value = "/album/{albumId}/tracks")
    Single<Response<DetailedAlbumResponseDTO>> queryAlbumTracks(@Path("albumId") Integer param);

    /* ------ Artist -------- */
    @GET(value = "/search/artist?order=ARTIST_ASC")
    Single<Response<ArtistDTO>> queryArtists(@Query("q") String param);

    @GET(value = "/artist/{artistId}/albums")
    Single<Response<ArtistAlbumsResponseDTO>> queryAlbumsOfArtist(@Path("artistId") Integer param);
}
