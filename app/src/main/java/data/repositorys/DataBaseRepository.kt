package data.repositorys

import data.base.models.DeviceDb

interface DataBaseRepository {
    suspend fun saveDevices(deviceDb: MutableList<DeviceDb>)
    suspend fun getDevices(): MutableList<DeviceDb>
    suspend fun saveDevice(deviceDb:DeviceDb)
}