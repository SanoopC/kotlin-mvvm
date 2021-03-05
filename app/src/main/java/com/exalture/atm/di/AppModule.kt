package com.exalture.atm.di

import android.content.Context
import androidx.room.Room
import com.exalture.atm.database.AccountDatabaseDao
import com.exalture.atm.database.ExaltureDatabase
import com.exalture.atm.database.ProjectsDao
import com.exalture.atm.network.ApiService
import com.exalture.atm.network.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
            .build()
    }


    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideProjectsDao(database: ExaltureDatabase): ProjectsDao {
        return database.projectsDao
    }

    @Singleton
    @Provides
    fun provideAccountDao(database: ExaltureDatabase): AccountDatabaseDao {
        return database.accountDatabaseDao
    }

    @Singleton
    @Provides
    fun provideDatabase(
        appContext: Context
    ): ExaltureDatabase {
        return Room.databaseBuilder(
            appContext,
            ExaltureDatabase::class.java,
            "account_database"
        ).fallbackToDestructiveMigration().build()
    }

}