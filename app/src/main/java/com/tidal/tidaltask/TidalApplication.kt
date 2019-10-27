package com.tidal.tidaltask

import com.tidal.tidaltask.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TidalApplication : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerApplicationComponent.builder().create(this)
    }

    companion object{
        private lateinit var instance: TidalApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}