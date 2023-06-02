package domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DevicesResponse(
    val Devices: MutableList<Device>? = null
) : Parcelable