package com.wesleyerick.podracer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.wesleyerick.podracer.data.model.vehicles.Vehicle
import com.wesleyerick.podracer.databinding.ItemListAdapterBinding
import com.wesleyerick.podracer.util.ImageTypeEnum
import com.wesleyerick.podracer.util.getItemListId
import com.wesleyerick.podracer.util.getPhotoUrl


class ListAdapter(
    private val list: List<Vehicle>,
    private val onClick: (id: String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClick
        )


    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ItemViewHolder).bind(list[position])

    private class ItemViewHolder(
        private val itemBinding: ItemListAdapterBinding,
        private val onClick: (id: String) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(
            item: Vehicle,
        ) = with(itemBinding) {

            val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(48))

            Glide.with(itemBinding.root)
                .load(getPhotoUrl(url = item.url, path = ImageTypeEnum.VEHICLES.path))
                .apply(requestOptions)
                .into(itemBinding.itemListImage)

            itemListTitleText.text = item.name
            itemListSubtitleFirstText.text = "Passengers: ${item.passengers}"
            itemListSubtitleSecondText.text = "Model: ${item.model}"

            itemBinding.root.setOnClickListener {
                onClick.invoke(getItemListId(item.url))
            }
        }
    }
}