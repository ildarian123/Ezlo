package domain.usecase

import data.base.models.DeviceDb
import data.repositorys.DataBaseRepositoryImpl
import javax.inject.Inject

class SaveDevicesToDataBaseUseCase @Inject constructor(private val dataBaseRepository: DataBaseRepositoryImpl){
    suspend fun execute(devices: MutableList<DeviceDb>) {
        dataBaseRepository.saveDevices(devices)
    }
}