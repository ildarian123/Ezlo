package domain.usecase

import data.repositorys.DeviceRepositoryImpl
import domain.models.Device
import javax.inject.Inject

class GetDevicesFromNetworkUseCase @Inject constructor(private val deviceRepository: DeviceRepositoryImpl) {
    suspend fun execute(): MutableList<Device> {
        return deviceRepository.getDevices()
    }
}