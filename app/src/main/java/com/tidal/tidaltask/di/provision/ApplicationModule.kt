package com.tidal.tidaltask.di.provision

import android.app.Application
import android.content.Context
import com.tidal.tidaltask.TidalApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

//    @ContributesAndroidInjector
//    abstract fun contributeActivityInjector() : HomeActivity

    @Provides
    @Singleton
    fun provideApplicationContext(application: TidalApplication): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApplication(application: TidalApplication): Application {
        return application
    }

}