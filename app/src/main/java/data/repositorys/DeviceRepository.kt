package data.repositorys

import domain.models.Device

interface DeviceRepository {
    suspend fun getDevices(): List<Device>
}