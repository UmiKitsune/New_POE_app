package com.example.poe_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity
import com.example.poe_app.data.url.models.Title
import com.example.poe_app.databinding.ItemMissionBinding
import com.example.poe_app.domain.models.TitleWithMissions

class MissionAdapter : RecyclerView.Adapter<MissionViewHolder>() {

    var missions: List<TitleWithMissionsEntity> = emptyList()
    set(newValue) {
        field = newValue
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMissionBinding.inflate(inflater, parent, false)
        return MissionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MissionViewHolder, position: Int) {
        val mission = missions[position]
        holder.bind(mission)
    }

    override fun getItemCount(): Int = missions.size

}