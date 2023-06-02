package domain.usecase

import data.base.models.DeviceDb
import data.repositorys.DataBaseRepositoryImpl
import javax.inject.Inject

class SaveDeviceToDbUseCase @Inject constructor(private val dataBaseRepository: DataBaseRepositoryImpl) {
    suspend fun execute(deviceDb: DeviceDb) {
        dataBaseRepository.saveDevice(deviceDb)
    }
}