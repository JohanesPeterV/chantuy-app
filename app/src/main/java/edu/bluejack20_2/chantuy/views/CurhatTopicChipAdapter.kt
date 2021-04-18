package edu.bluejack20_2.chantuy.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.bluejack20_2.chantuy.R
import edu.bluejack20_2.chantuy.models.Curhat
import edu.bluejack20_2.chantuy.models.CurhatTopic

class CurhatTopicChipAdapter : ListAdapter<CurhatTopic, CurhatTopicChipAdapter.ViewHolder>(TopicChipDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = getItem(position)
        holder.bind(topic)
    }

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val name : TextView = view.findViewById(R.id.filter_topic_chip_text)


        fun bind(topic: CurhatTopic) {
            name.text = topic.name
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.topic_chip_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}

object TopicChipDiffCallback : DiffUtil.ItemCallback<CurhatTopic>() {
    override fun areItemsTheSame(oldItem: CurhatTopic, newItem: CurhatTopic): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CurhatTopic, newItem: CurhatTopic): Boolean {
        return oldItem.id == newItem.id
    }
}