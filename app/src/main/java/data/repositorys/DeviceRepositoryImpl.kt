package data.repositorys

import data.network.EzloApi
import domain.models.Device
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(private val ezloApi: EzloApi): DeviceRepository {
    override suspend fun getDevices(): MutableList<Device> {
        val result = ezloApi.getDevices()
        return result.body()?.Devices?: mutableListOf()
    }
}