package presentation.devices

import domain.models.Device

interface ClickListener {
    fun onClick(item : Device)
    fun onLongClick(item : Device)
    fun editButtonClick(item : Device)
}