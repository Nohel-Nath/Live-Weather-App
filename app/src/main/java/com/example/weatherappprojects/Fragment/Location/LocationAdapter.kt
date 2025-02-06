package com.example.weatherappprojects.Fragment.Location

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherappprojects.data.RemoteLocation
import com.example.weatherappprojects.databinding.ItemContainerCurrentLocationBinding
import com.example.weatherappprojects.databinding.ItemContainerLocationBinding

class LocationAdapter (
    private val onLocationClicked:(RemoteLocation) ->Unit
):RecyclerView.Adapter<LocationAdapter.LocationViewHolder>(){

    private val locations = mutableListOf<RemoteLocation>()
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data:List<RemoteLocation>){
        Log.d("LocationAdapter", "New Data Size: ${data.size}")
        locations.clear()
        locations.addAll(data)
        Log.d("LocationAdapter", "After adding: ${locations.size}")
        notifyDataSetChanged()
    }

    inner class LocationViewHolder(
        private val binding: ItemContainerLocationBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(remoteLocation: RemoteLocation){
            with(remoteLocation){
                val location = "$name, $region, $country"
                binding.textRemoteLocation.text = location
                binding.root.setOnClickListener { onLocationClicked(remoteLocation) }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LocationAdapter.LocationViewHolder {
        return LocationViewHolder(
            ItemContainerLocationBinding.inflate(LayoutInflater.from(parent.context),
                parent,false)
        )
    }

    override fun onBindViewHolder(holder: LocationAdapter.LocationViewHolder, position: Int) {
        holder.bind(remoteLocation = locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

}