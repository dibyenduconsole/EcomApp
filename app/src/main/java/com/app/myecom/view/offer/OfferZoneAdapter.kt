package com.app.myecom.view.offer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.myecom.databinding.ItemProductOfferViewBinding
import java.util.ArrayList

class OfferZoneAdapter (clickHandler: OfferClickHandler) : RecyclerView.Adapter<OfferZoneAdapter.OfferZoneViewHolder>(){

    var offerZoneList: ArrayList<OfferData> = arrayListOf()
    var clickPresenter: OfferClickHandler = clickHandler

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferZoneViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductOfferViewBinding.inflate(inflater, parent, false)


        return OfferZoneViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (offerZoneList != null) {
            offerZoneList.size
        } else {
            0
        }

    }

    override fun onBindViewHolder(holder: OfferZoneViewHolder, position: Int) {

        val product = offerZoneList.get(position)
        holder.bind(product, clickPresenter)

    }


    class OfferZoneViewHolder(private val itemProductOfferViewBinding: ItemProductOfferViewBinding) :
        RecyclerView.ViewHolder(itemProductOfferViewBinding.getRoot()){

        fun bind(offerData: OfferData, clickPresenter: OfferClickHandler) {
            itemProductOfferViewBinding.offerdata = offerData
            itemProductOfferViewBinding.handler = clickPresenter
            //itemMyBagListBinding.promotionItemSpecialLabel.visibility = if(item.isSpecial) View.VISIBLE else View.GONE
            itemProductOfferViewBinding.executePendingBindings()
        }

    }


    fun setOfferList(catsList: List<OfferData>) {
        offerZoneList?.clear()
        offerZoneList?.addAll(catsList)
        notifyDataSetChanged()
    }

}