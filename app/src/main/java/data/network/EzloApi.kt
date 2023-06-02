package data.network

import domain.models.DevicesResponse
import retrofit2.Response
import retrofit2.http.GET

interface EzloApi {

    @GET("/test_android/items.test")
    suspend fun getDevices(
    ): Response<DevicesResponse>
}