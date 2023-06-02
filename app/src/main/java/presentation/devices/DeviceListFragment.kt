package presentation.devices

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ezlo.R
import com.example.ezlo.databinding.FragmentDeviceListBinding
import dagger.hilt.android.AndroidEntryPoint
import domain.models.Device

@AndroidEntryPoint
class DeviceListFragment : Fragment(), ClickListener {

    private lateinit var navController: NavController
    private lateinit var binding: FragmentDeviceListBinding
    private val vm: DeviceViewModel by viewModels()
    private lateinit var mAdapter: DevicesAdapter
    private var deviceList: MutableList<Device> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        vm.getDevices()
    }

    private fun addObservers() {
        vm.devices.observe(viewLifecycleOwner) {
            if (it == null) {
                Toast.makeText(requireContext(), "server error", Toast.LENGTH_LONG).show()
            } else {
                deviceList = it
                updateDevicesList(deviceList)
            }
        }
    }

    private fun updateDevicesList(deviceList: List<Device>) {
        val manager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity)
        mAdapter = DevicesAdapter(requireContext(), deviceList, clickListener = this)
        binding.rvDevices.layoutManager = manager
        binding.rvDevices.adapter = mAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        navController = findNavController()
        binding = FragmentDeviceListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onClick(item: Device) {
        val bundle = bundleOf(Pair("device", item))
        bundle.putBoolean("edit", false)
        navController.navigate(R.id.action_devices_fragment_to_device_details_fragment, bundle)
    }

    override fun onLongClick(item: Device) {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Delete device?")
        builder.setNegativeButton(android.R.string.no) { _, _ -> }
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            deviceList.remove(item)
            mAdapter.deviceList = deviceList
            item.deleted = true
            vm.saveDevice(item)
            mAdapter.notifyDataSetChanged()
        }

        builder.show()
    }
    override fun editButtonClick(item: Device) {
        val bundle = bundleOf(Pair("device", item))
        bundle.putBoolean("edit", true)
        navController.navigate(R.id.action_devices_fragment_to_device_details_fragment, bundle)
    }
}