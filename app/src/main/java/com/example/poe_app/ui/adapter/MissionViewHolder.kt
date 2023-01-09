package com.example.poe_app.ui.adapter

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.poe_app.R
import com.example.poe_app.data.database.entities.TitleWithMissionsEntity
import com.example.poe_app.databinding.ItemMissionBinding


class MissionViewHolder(
    private val binding: ItemMissionBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(data: TitleWithMissionsEntity) {
        with(binding) {
            if (data.title.isCompleted) {
                completedImg.visibility = View.VISIBLE
            } else {
                completedImg.visibility = View.GONE
            }

            itemTitle.text = data.title.titleName
            itemHowManyCompleted.text = data.title.hmCompleted
            itemDescription.text = data.title.description

            itemLinearLayout.removeAllViews()

            for (mission in data.missions){
                val textView = TextView(itemView.context)
                if (mission.isClicked) {
                    textView.alpha = 0.3f
                    //textView.setTextColor(getColor(itemView.context, R.color.white))
                }
                textView.text = mission.missionText

                itemLinearLayout.addView(textView)
            }
        }
    }
}