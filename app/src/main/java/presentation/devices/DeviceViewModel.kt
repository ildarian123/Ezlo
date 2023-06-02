package presentation.devices

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.base.models.DeviceDb
import domain.models.Device
import domain.usecase.GetDevicesFromDbUseCase
import domain.usecase.GetDevicesFromNetworkUseCase
import domain.usecase.SaveDeviceToDbUseCase
import domain.usecase.SaveDevicesToDataBaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(
    private val getDevicesFromNetworkUseCase: GetDevicesFromNetworkUseCase,
    private val saveDevicesToDbUseCase: SaveDevicesToDataBaseUseCase,
    private val getDevicesFromDbUseCase: GetDevicesFromDbUseCase,
    private val saveDeviceToDbUseCase: SaveDeviceToDbUseCase
) :
    ViewModel() {

    private val scope = CoroutineScope(Dispatchers.IO)
    var devices: MutableLiveData<MutableList<Device>> = MutableLiveData()

    fun getDevices() {
        scope.launch {
            saveDevicesToDb(getDevicesFromNetworkUseCase.execute())
            val listOfDevicesDb = getDevicesFromDbUseCase.execute()
            val listOfDevices = mutableListOf<Device>()
            listOfDevicesDb.forEach {
                listOfDevices.add(convertToDevice(it))
            }
            devices.postValue(listOfDevices)
        }
    }

    private fun saveDevicesToDb(devices: List<Device>) {
        val listOfDevicesDb = mutableListOf<DeviceDb>()
        devices.forEach {
            listOfDevicesDb.add(convertToDeviceDb(it))
        }
        scope.launch {
            saveDevicesToDbUseCase.execute(listOfDevicesDb)
        }
    }

    fun saveDevice(device: Device) {
        scope.launch {
            saveDeviceToDbUseCase.execute(convertToDeviceDb(device))
        }
    }

    private fun convertToDeviceDb(device: Device): DeviceDb {
        val deviceDb = DeviceDb()
        deviceDb.pk_Device = device.PK_Device ?: 0
        deviceDb.MacAddress = device.MacAddress ?: ""
        deviceDb.PK_DeviceType = device.PK_DeviceType ?: 0
        deviceDb.PK_DeviceSubType = device.PK_DeviceSubType ?: 0
        deviceDb.Firmware = device.Firmware ?: ""
        deviceDb.Server_Device = device.Server_Device ?: ""
        deviceDb.Server_Event = device.Server_Event ?: ""
        deviceDb.Server_Account = device.Server_Account ?: ""
        deviceDb.InternalIP = device.InternalIP ?: ""
        deviceDb.LastAliveReported = device.LastAliveReported ?: ""
        deviceDb.Platform = device.Platform ?: ""
        deviceDb.deleted = device.deleted
        deviceDb.edited = device.edited
        deviceDb.title = device.title

        return deviceDb
    }

    private fun convertToDevice(deviceDb: DeviceDb): Device {
        val device = Device()
        device.PK_Device = deviceDb.pk_Device
        device.MacAddress = deviceDb.MacAddress
        device.PK_DeviceType = deviceDb.PK_DeviceType
        device.PK_DeviceSubType = deviceDb.PK_DeviceSubType
        device.Firmware = deviceDb.Firmware
        device.Server_Device = deviceDb.Server_Device
        device.Server_Event = deviceDb.Server_Event
        device.Server_Account = deviceDb.Server_Account
        device.InternalIP = deviceDb.InternalIP
        device.LastAliveReported = deviceDb.LastAliveReported
        device.Platform = deviceDb.Platform
        device.title = deviceDb.title

        return device
    }

}