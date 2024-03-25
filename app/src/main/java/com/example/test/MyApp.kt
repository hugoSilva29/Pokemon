package com.example.test

import android.app.Application
import com.example.test.dagger.AppComponent
import com.example.test.dagger.ContextModule
import com.example.test.dagger.DaggerAppComponent

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

}
