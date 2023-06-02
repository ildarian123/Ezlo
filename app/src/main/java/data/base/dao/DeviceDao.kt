package data.base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import data.base.models.DeviceDb

@Dao
interface DeviceDao {

    @Query("SELECT * FROM devices WHERE deleted = 0")
    fun getDevices(): MutableList<DeviceDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDevice(userDb: DeviceDb?)

    @Query("SELECT * FROM devices WHERE PK_Device = :id")
    fun getDeviceById(id: Int): MutableList<DeviceDb>
}