package com.ilhmdhn.gamecatalog.core.di

import androidx.room.Room
import com.ilhmdhn.gamecatalog.core.BuildConfig
import com.ilhmdhn.gamecatalog.core.data.GameRepository
import com.ilhmdhn.gamecatalog.core.data.source.local.LocalDataSource
import com.ilhmdhn.gamecatalog.core.data.source.local.room.GameDatabase
import com.ilhmdhn.gamecatalog.core.data.source.remote.RemoteDataSource
import com.ilhmdhn.gamecatalog.core.data.source.remote.network.ApiService
import com.ilhmdhn.gamecatalog.core.domain.repository.IGameRepository
import com.ilhmdhn.gamecatalog.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory{get<GameDatabase>().gameDao()}
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("ilhmdhn".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single{
        OkHttpClient.Builder()
            .addInterceptor ( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) )
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module{
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository>{
        GameRepository(
            get(),
            get(),
            get()
        )
    }
}