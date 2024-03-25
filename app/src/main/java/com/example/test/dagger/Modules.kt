package com.example.test.dagger

import android.content.Context
import androidx.room.Room
import com.example.test.repository.Repository
import com.example.test.retorfit.PokemonService
import com.example.test.room.RoomPokemonDao
import com.example.test.room.RoomPokemonDatabase
import com.example.test.schedulers.SchedulersBase
import com.example.test.schedulers.SchedulersImpl
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Singleton
    @Provides
    fun provideSchedulers(): SchedulersBase = SchedulersImpl()
}

@Module(includes = [PokemonServiceModule::class, RoomModule::class, ContextModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokemonService: PokemonService,
        roomPokemonDao: RoomPokemonDao,
        context: Context
    ): Repository = Repository(pokemonService, roomPokemonDao,context)
}

@Module(includes = [RetrofitModule::class])
class PokemonServiceModule {

    @Singleton
    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)

}

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        callAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .cache(cache)
            .build()

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Timber.d(it) })
            .apply { level = HttpLoggingInterceptor.Level.BASIC }

    @Singleton
    @Provides
    fun provideCache(context: Context): Cache = Cache(context.cacheDir, 5 * 5 * 1024)

    @Singleton
    @Provides
    fun provideRxCallAdapter(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

}

@Module(includes = [ContextModule::class])
class RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(context: Context): RoomPokemonDatabase {
        return Room.databaseBuilder(
            context,
            RoomPokemonDatabase::class.java, "favorite_pokemon_database"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesPokemonDao(appDatabase: RoomPokemonDatabase): RoomPokemonDao {
        return appDatabase.roomPokemonDao()
    }
}


@Module
class ContextModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context
}