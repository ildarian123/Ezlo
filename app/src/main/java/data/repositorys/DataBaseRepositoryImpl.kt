package data.repositorys

import data.base.dao.DeviceDao
import data.base.models.DeviceDb
import javax.inject.Inject

class DataBaseRepositoryImpl @Inject constructor(private val dao: DeviceDao): DataBaseRepository {
    override suspend fun saveDevices(devicesDb: MutableList<DeviceDb>) {
        devicesDb.forEach {
            if (dao.getDeviceById(it.pk_Device).isEmpty()){
                dao.insertDevice(it)
            }
        }
    }
    override suspend fun getDevices(): MutableList<DeviceDb> {
        return dao.getDevices()
    }
    override suspend fun saveDevice(deviceDb: DeviceDb) {
        dao.insertDevice(deviceDb)
    }
}