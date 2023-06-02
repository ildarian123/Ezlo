package domain.usecase

import data.base.models.DeviceDb
import data.repositorys.DataBaseRepositoryImpl
import javax.inject.Inject

class GetDevicesFromDbUseCase @Inject constructor(private val dataBaseRepository: DataBaseRepositoryImpl){
    suspend fun execute(): MutableList<DeviceDb> {
        return dataBaseRepository.getDevices()
    }
}