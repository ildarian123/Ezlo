package domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Device(
    var PK_Device: Int? = null,
    var MacAddress: String? = null,
    var PK_DeviceType: Int? = null,
    var PK_DeviceSubType: Int? = null,
    var Firmware: String? = null,
    var Server_Device: String? = null,
    var Server_Event: String? = null,
    var Server_Account: String? = null,
    var InternalIP: String? = null,
    var LastAliveReported: String? = null,
    var Platform: String? = null,
    var edited: Boolean = false,
    var deleted: Boolean = false,
    var title: String = ""
) : Parcelable