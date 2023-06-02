package di

import android.content.Context
import androidx.room.Room
import com.squareup.picasso.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.base.Base
import data.network.EzloApi
import data.repositorys.DataBaseRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    var EZLO_API_URL = "https://veramobile.mios.com"
    @Provides
    @Singleton
    fun provideNetworkApi(client: OkHttpClient): EzloApi {
        return Retrofit.Builder()
            .baseUrl(EZLO_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(EzloApi::class.java)
    }
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
    }
    @Provides
    @Singleton
    fun provideDataBaseRepository(db: Base): DataBaseRepositoryImpl {
        return DataBaseRepositoryImpl(db.deviceDao()!!)
    }

    private var db: Base? = null

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context : Context): Base {
        if (db == null) {
            db = Room.databaseBuilder(
                context,
                Base::class.java, "user_data_base"
            )
                .allowMainThreadQueries()
                .build()
        }
        return db!!
    }
}