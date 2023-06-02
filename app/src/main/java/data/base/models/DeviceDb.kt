package data.base.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "devices")
class DeviceDb {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "PK_Device")
    var pk_Device: Int = 0

    @ColumnInfo(name = "MacAddress")
    var MacAddress: String = ""

    @ColumnInfo(name = "PK_DeviceType")
    var PK_DeviceType: Int = 0

    @ColumnInfo(name = "PK_DeviceSubType")
    var PK_DeviceSubType: Int = 0

    @ColumnInfo(name = "Firmware")
    var Firmware: String = ""

    @ColumnInfo(name = "Server_Device")
    var Server_Device: String = ""

    @ColumnInfo(name = "Server_Event")
    var Server_Event: String = ""

    @ColumnInfo(name = "Server_Account")
    var Server_Account: String = ""

    @ColumnInfo(name = "InternalIP")
    var InternalIP: String = ""

    @ColumnInfo(name = "LastAliveReported")
    var LastAliveReported: String = ""

    @ColumnInfo(name = "Platform")
    var Platform: String = ""

    @ColumnInfo(name = "deleted")
    var deleted: Boolean = false

    @ColumnInfo(name = "edited")
    var edited: Boolean = false

    @ColumnInfo(name = "title")
    var title: String = ""
}