package edu.bluejack20_2.chantuy.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import edu.bluejack20_2.chantuy.databinding.ProfilePostedReplyCardItemBinding
import edu.bluejack20_2.chantuy.models.CurhatComment
import edu.bluejack20_2.chantuy.repositories.CurhatRepository
import edu.bluejack20_2.chantuy.utils.CurhatUtil
import edu.bluejack20_2.chantuy.utils.CurhatViewUtil

class ProfileRepliedCurhatAdapter(): ListAdapter<CurhatComment, ProfileRepliedCurhatAdapter.ViewHolder>(ProfileRepliedCurhatDiffCallback) {

    class ViewHolder(val binding: ProfilePostedReplyCardItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: CurhatComment) {
            CurhatRepository.getById(comment.curhatId) {
                binding.profileRepliedCurhat.text = it.content
            }

            binding.profileRepliedContent.text = comment.content
            binding.profileRepliedDate.text = CurhatViewUtil.formatDate(comment.createdAt,binding.root.context)

            binding.profileRepliedViewBtn.setOnClickListener {
                CurhatUtil.moveToCurhatDetail(comment.curhatId, binding.root.context)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfilePostedReplyCardItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }
}

object ProfileRepliedCurhatDiffCallback : DiffUtil.ItemCallback<CurhatComment>() {
    override fun areItemsTheSame(oldItem: CurhatComment, newItem: CurhatComment): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CurhatComment, newItem: CurhatComment): Boolean {
        return oldItem.commentId == newItem.commentId
    }
}