package com.example.test

import android.app.Application
import com.example.test.dagger.AppComponent
import com.example.test.dagger.ContextModule
import com.example.test.dagger.DaggerAppComponent
import java.io.File

class MyApp : Application() {

    val appComponent: AppComponent by lazy {
        val dexOutputDir: File = codeCacheDir
        dexOutputDir.setReadOnly()
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()

    }

}
