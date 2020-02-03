package com.santiagoperdomo.movies.http

import com.santiagoperdomo.movies.root.Const
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MoviesInfoApiModule {

    @Provides
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("apikey", Const.API_KEY_OMDb).build()
            request = request.newBuilder().url(url).build()
            return@addInterceptor chain.proceed(request)
        }.build()
    }

    @Provides
    fun provideRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideMoviesInfoApiService(): MoviesInfoApiService {
        return provideRetrofit(Const.BASE_URL_OMDb, provideHttpClient()).create(MoviesInfoApiService::class.java)
    }

}