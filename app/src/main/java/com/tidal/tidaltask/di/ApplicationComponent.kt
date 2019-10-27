package com.tidal.tidaltask.di

import com.tidal.tidaltask.TidalApplication
import com.tidal.tidaltask.di.builder.ActivityBuilder
import com.tidal.tidaltask.di.provision.ApplicationModule
import com.tidal.tidaltask.di.provision.RemoteModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, ActivityBuilder::class, RemoteModule::class])
interface ApplicationComponent : AndroidInjector<TidalApplication>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TidalApplication>(){
        abstract override fun build(): ApplicationComponent
    }
}