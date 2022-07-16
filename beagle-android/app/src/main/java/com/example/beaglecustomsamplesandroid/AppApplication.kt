package com.example.beaglecustomsamplesandroid

import android.app.Application
import com.example.designsystem.component.sharedpreference.Storage
import com.example.designsystem.component.sharedpreference.StorageImpl
import com.example.serverdriven.ServerDrivenInitializer
import com.google.gson.Gson
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ServerDrivenInitializer.initialize(this,
            StorageImpl(
            this,
            "BEABLEDOCS",
            Gson()
        ))

        val t = HomeDelegateImpl()
    }
}

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: MainActivity)

    fun inject(app: HomeDelegateImpl)

    fun inject(app: AppApplication)

}

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesGson(): Gson = Gson()

    @Singleton
    @Provides
    fun providesStorage(application: AppApplication, gson: Gson): Storage = StorageImpl(
        application,
        "BEABLEDOCS",
        gson
    )

}

class Info @Inject constructor(){
    val text = "Hello Dagger 2"
}

class HomeDelegateImpl {

    @Inject
    lateinit var info: Info

    init {
        DaggerAppComponent.create().inject(this)
    }

}

