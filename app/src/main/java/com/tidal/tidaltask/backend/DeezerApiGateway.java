package com.tidal.tidaltask.backend;

import com.tidal.tidaltask.domain.album.model.dto.AlbumDetailDTO;
import com.tidal.tidaltask.domain.album.model.dto.AlbumsOfArtistResponseDTO;
import com.tidal.tidaltask.domain.artist.model.dto.ArtistDTO;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DeezerApiGateway {

    /* ------- Album Endpoints ------- */
//    @GET(value = "/search/album")
//    Single<Response<AlbumDTO>> queryAlbums(@Query("q") String param);

    @GET(value = "/album/{albumId}/tracks")
    Single<Response<AlbumDetailDTO>> queryAlbumTracks(@Path("albumId") Integer param);

    /* ------ Artist -------- */
    @GET(value = "/search/artist")
    Single<Response<ArtistDTO>> queryArtists(@Query("q") String param);

    @GET(value = "/artist/{artistId}/albums")
    Single<Response<AlbumsOfArtistResponseDTO>> queryAlbumsOfArtist(@Path("artistId") Integer param);
}
