package data.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import data.base.dao.DeviceDao
import data.base.models.DeviceDb

@Database(entities = [DeviceDb::class], version = 1)
abstract class Base: RoomDatabase() {

    private var db: Base? = null

    open fun getDataBase(applicationContext: Context?): Base? {
        if (db == null) {
            db = Room.databaseBuilder(
                applicationContext!!,
                Base::class.java, "device_data_base"
            )
                .allowMainThreadQueries()
                .build()
        }
        return db
    }

    abstract fun deviceDao(): DeviceDao?

}