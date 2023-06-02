package presentation.devices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ezlo.R
import com.example.ezlo.databinding.DeviceListItemBinding
import com.squareup.picasso.Picasso
import domain.models.Device

class DevicesAdapter(
    var deviceList: List<Device>, private val clickListener: ClickListener
) : RecyclerView.Adapter<DevicesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: DeviceListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DeviceListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(deviceList[position]) {
                Picasso.get().load(R.drawable.vera_secure).into(binding.deviceImageView)
                binding.homeNumberTextView.text = this.title
                binding.snTextView.text = this.PK_Device.toString()

                binding.root.setOnLongClickListener {
                    clickListener.onLongClick(this)
                    true
                }

                binding.root.setOnClickListener {
                    clickListener.onClick(this)
                }

                binding.editButton.setOnClickListener {
                    clickListener.editButtonClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }
}