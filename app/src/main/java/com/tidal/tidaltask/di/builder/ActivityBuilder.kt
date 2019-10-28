package com.tidal.tidaltask.di.builder

import com.tidal.tidaltask.di.annotations.ActivityScope
import com.tidal.tidaltask.base.HomeActivity
import com.tidal.tidaltask.domain.album.listing.ui.AlbumListingFragment
import com.tidal.tidaltask.domain.artist.ui.SearchArtistFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder{
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeDispatchActivity() : HomeActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeSearchArtistFragment() : SearchArtistFragment

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeAlbumListingFragment() : AlbumListingFragment
}