package araikovich.inc.araikovichdemo.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val restModule = module {
    single { provideRetrofit(get()) }
    single { provideGson() }
}

fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun provideGson(): Gson = GsonBuilder().setLenient().create()

