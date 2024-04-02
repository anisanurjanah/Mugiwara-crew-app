package com.example.mugiwaracrew

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mugiwaracrew.databinding.ItemRowCrewBinding

class ListCrewAdapter(private val listCrew: ArrayList<Crew>) : RecyclerView.Adapter<ListCrewAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(var binding: ItemRowCrewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowCrewBinding.inflate(LayoutInflater.from(parent.context),
            parent, false
        )
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listCrew.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, position, from, description, photo, link) = listCrew[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemPosition.text = position
        holder.binding.tvItemDescription.text = description
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.binding.imgItemPhoto)

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCrew[holder.adapterPosition])
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Crew)
    }
}