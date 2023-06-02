package presentation.devicedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ezlo.databinding.FragmentDeviceDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import domain.models.Device
import presentation.devices.DeviceViewModel

@AndroidEntryPoint
class DeviceDetailsFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentDeviceDetailsBinding
    private val vm: DeviceViewModel by viewModels()
    private var device: Device? = null
    private var edit: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navController = findNavController()
        binding = FragmentDeviceDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        device = requireArguments().getParcelable("device")
        edit = requireArguments().getBoolean("edit")
        setupDevice(device)
        binding.deviceTitleEditText.isEnabled = edit
        binding.saveButton.setOnClickListener {
            device?.title = binding.deviceTitleEditText.text.toString()
            vm.saveDevice(device?: Device())
        }
    }

    private fun setupDevice(device: Device?) {
        binding.deviceTitleEditText.setText(device?.title, TextView.BufferType.EDITABLE)
        binding.macAddressTextView.text = device?.MacAddress.toString()
        binding.firmWareTextView.text = device?.Firmware.toString()
        binding.modelTextView.text = device?.Platform.toString()
        binding.snTextView.text = device?.PK_Device.toString()
    }
}