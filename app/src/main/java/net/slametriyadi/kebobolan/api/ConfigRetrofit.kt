package net.slametriyadi.kebobolan.api

import net.slametriyadi.kebobolan.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConfigRetrofit {
    var retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service = retrofit.create(Api::class.java)
}