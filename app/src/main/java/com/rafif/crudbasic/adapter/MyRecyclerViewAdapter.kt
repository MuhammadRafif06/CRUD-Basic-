package com.rafif.crudbasic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rafif.crudbasic.R
import com.rafif.crudbasic.databinding.ItemListBinding
import com.rafif.crudbasic.db.Subreker

class MyRecyclerViewAdapter(
    private val subrekerList: List<Subreker>,
    private val clickListener : (Subreker) -> Unit
    )
    : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemListBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_list, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subrekerList[position], clickListener)
    }

    override fun getItemCount(): Int = subrekerList.size
}

class MyViewHolder (val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(subreker: Subreker, clickListener: (Subreker) -> Unit){
        binding.nameTextView.text = subreker.name
        binding.emailTextView.text = subreker.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subreker)
        }
    }
}