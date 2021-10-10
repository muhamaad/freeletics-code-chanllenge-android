package com.zarea.freeleticscodechanllenge.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.zarea.freeleticscodechanllenge.api.RepositoryAPI
import com.zarea.freeleticscodechanllenge.database.AppDatabase
import com.zarea.freeleticscodechanllenge.database.RepoDao
import com.zarea.freeleticscodechanllenge.repository.DataRepo
import com.zarea.freeleticscodechanllenge.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideHttpOkClient(): OkHttpClient = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor()
            .apply { this.level = HttpLoggingInterceptor.Level.BODY }
    )
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.SERVER)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .build()

    @Provides
    fun provideRepositoryAPI(retrofit: Retrofit): RepositoryAPI =
        retrofit.create(RepositoryAPI::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RepositoryDB"
        ).build()
    }

    @Provides
    fun provideRepoDao(appDatabase: AppDatabase): RepoDao {
        return appDatabase.RepoDao()
    }

    @Provides
    fun provideDataRepo(repositoryAPI: RepositoryAPI, repoDao: RepoDao) =
        DataRepo(repositoryAPI, repoDao)
}
