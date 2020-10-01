package com.sibi.reigntest

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sibi.reigntest.data.local.AppDatabase
import com.sibi.reigntest.data.local.PostDao
import com.sibi.reigntest.data.remote.PostRemoteDataSource
import com.sibi.reigntest.data.remote.PostService
import com.sibi.reigntest.data.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://hn.algolia.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun providePostService(retrofit: Retrofit): PostService =
        retrofit.create(PostService::class.java)

    @Provides
    @Singleton
    fun providePostRemoteDataSource(postService: PostService) = PostRemoteDataSource(postService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun providePostDao(db: AppDatabase) = db.postDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: PostRemoteDataSource,
        localDataSource: PostDao
    ) =
        PostRepository(remoteDataSource, localDataSource)
}