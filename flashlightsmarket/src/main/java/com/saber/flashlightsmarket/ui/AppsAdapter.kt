package com.saber.flashlightsmarket.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saber.flashlightsmarket.databinding.ItemLightAppBinding
import com.saber.flashlightsmarket.model.LightApp

class AppsAdapter(private val onClickedListener: OnLightAppClickedListener) :
    RecyclerView.Adapter<AppsAdapter.AppsViewHolder>() {

    private val items: MutableList<LightApp> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppsViewHolder {
        return AppsViewHolder(
            ItemLightAppBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AppsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: List<LightApp>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearApps() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class AppsViewHolder(private val binding: ItemLightAppBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LightApp) {
            binding.apply {
                lightApp = item
                onAppClickedListener = this@AppsAdapter.onClickedListener
                executePendingBindings()
            }
        }
    }

    interface OnLightAppClickedListener {
        fun onAppClicked(app: LightApp)
    }

}