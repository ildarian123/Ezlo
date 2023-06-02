package presentation.devicedetails

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.ezlo.R
import com.example.ezlo.databinding.FragmentDeviceDetailsBinding
import com.squareup.picasso.Picasso
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
        getParameters()
        setlisteners()
        setupDevice(device)
    }

    private fun setlisteners() {
        binding.saveButton.setOnClickListener {
            device?.title = binding.deviceTitleEditText.text.toString()
            vm.saveDevice(device?: Device())
        }
    }
    private fun getParameters() {
        device = requireArguments().getParcelable("device")
        edit = requireArguments().getBoolean("edit")
    }
    private fun setupDevice(device: Device?) {
        if (edit) {
            binding.deviceTitleEditText.requestFocus()
            val im = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.showSoftInput(binding.deviceTitleEditText, InputMethodManager.SHOW_IMPLICIT)
        }
        binding.deviceTitleEditText.isEnabled = edit
        binding.deviceTitleEditText.setText(device?.title, TextView.BufferType.EDITABLE)
        binding.macAddressTextView.text = device?.MacAddress.toString()
        binding.firmWareTextView.text = device?.Firmware.toString()
        binding.modelTextView.text = device?.Platform.toString()
        binding.snTextView.text = device?.PK_Device.toString()
        Picasso.get().load(getImageDrawable(device?.Platform.toString())).into(binding.deviceAvatarImageView)
    }
    private fun getImageDrawable(platform: String): Int {
        return when (platform) {
            requireContext().getString(R.string.sercomm_g450) -> R.drawable.vera_plus_big
            requireContext().getString(R.string.sercomm_g550) -> R.drawable.vera_secure_big
            requireContext().getString(R.string.micasaverde_veralite) -> R.drawable.vera_edge_big
            requireContext().getString(R.string.sercomm_na900) -> R.drawable.vera_edge_big
            requireContext().getString(R.string.sercomm_na301) -> R.drawable.vera_edge_big
            requireContext().getString(R.string.sercomm_na930) -> R.drawable.vera_edge_big
            else -> R.drawable.vera_edge_big
        }
    }
}