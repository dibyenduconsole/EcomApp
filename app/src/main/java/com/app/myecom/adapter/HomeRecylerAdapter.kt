package com.app.myecom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.myecom.R
import com.app.myecom.utilities.LOG
import com.app.myecom.view.ViewType
import com.app.myecom.view.categories.CategoriesAdapter
import com.app.myecom.view.categories.CategoriesClickHandler
import com.app.myecom.view.categories.CategoriesData
import com.app.myecom.view.model.HomeViewData
import com.app.myecom.view.offer.OfferClickHandler
import com.app.myecom.view.offer.OfferData
import com.app.myecom.view.offer.OfferZoneAdapter
import com.bumptech.glide.Glide

class HomeRecylerAdapter (context: Context, pageSectionList: List<HomeViewData>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return homePageSectionList.size
    }

    var homePageSectionList = pageSectionList
    var context = context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // create a new view
        val view: View
        if (viewType === ViewType.viewBANNER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_view_banner, parent, false)
            return BannerViewHolder(view)

        } else if(viewType === ViewType.viewCATEGORIES){
            view = LayoutInflater.from(context).inflate(R.layout.item_view_categories, parent, false)
            return CategoriesViewHolder(view)
        }
        else if(viewType === ViewType.viewOFFERZONE){
            view = LayoutInflater.from(context).inflate(R.layout.item_view_offer, parent, false)
            return OfferZoneViewHolder(view)
        }
        else if(viewType === ViewType.viewTRENDING){
            view = LayoutInflater.from(context).inflate(R.layout.item_view_tranding, parent, false)
            return TrendingViewHoler(view)
        }
        else if(viewType === ViewType.viewBANNERSMALL){
            view = LayoutInflater.from(context).inflate(R.layout.item_view_banner_small, parent, false)
            return BannerSmallViewHoler(view)
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.item_view_ad, parent, false)
            return AdViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (homePageSectionList.get(position).getViewType() != null) {
            homePageSectionList.get(position).getViewType().toString().toInt()
        } else {
            ViewType.viewADSHOW
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ViewType.viewBANNER) {
            (viewHolder as BannerViewHolder).setBannerDetails(homePageSectionList.get(position))
        }
        else if (getItemViewType(position) == ViewType.viewCATEGORIES) {
            (viewHolder as CategoriesViewHolder).setCategoriesDetails(homePageSectionList.get(position), position)
        }
        else if (getItemViewType(position) == ViewType.viewOFFERZONE) {
            (viewHolder as OfferZoneViewHolder).setOfferZOneDetails(homePageSectionList.get(position), position)
        }
        else if (getItemViewType(position) == ViewType.viewTRENDING) {
            (viewHolder as TrendingViewHoler).setBannerDetails(homePageSectionList.get(position))
        }
        else if (getItemViewType(position) == ViewType.viewBANNERSMALL) {
            (viewHolder as BannerSmallViewHoler).setBannerDetails(homePageSectionList.get(position))
        }
        else {
            (viewHolder as AdViewHolder).setAdViewDetails(homePageSectionList.get(position))
        }
    }


    internal inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), CategoriesClickHandler {
        override fun onClickCategoriesItem(categoriesData: CategoriesData) {

            LOG.d(">>CLICK-CATEGORIES ITEM","-"+categoriesData)


        }

        private val _tvCategories: TextView
        private val _recylerCategories: RecyclerView

        init {
            _tvCategories = itemView.findViewById(R.id._tvCategories)

            _recylerCategories = itemView.findViewById(R.id._recylerCategories)
        }

        fun setCategoriesDetails(homePageSection: HomeViewData, position:Int) {
            _tvCategories.setText(homePageSection.getViewText())

            var cateList = ArrayList<CategoriesData>()
            for (i in 0..7){
                cateList.add(CategoriesData())
            }
            var categoriesAdapter = CategoriesAdapter(this)
            _recylerCategories.adapter = categoriesAdapter

            categoriesAdapter.setCategoriesList(cateList)


        }
    }



    internal inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val _tvCategoriesHeader: TextView

        init {
            //_tvCategoriesHeader = itemView.findViewById(R.id._tvCategoriesHeader)
        }

        fun setBannerDetails(homePageSection: HomeViewData) {
            //_tvCategoriesHeader.setText(homePageSection.getTitle())
            //txtAddress.setText(employee.getAddress())
        }
    }

    internal inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val _tvCategoriesHeader: TextView

        init {
            //_tvCategoriesHeader = itemView.findViewById(R.id._tvCategoriesHeader)
        }

        fun setAdViewDetails(homePageSection: HomeViewData) {
            //_tvCategoriesHeader.setText(homePageSection.getTitle())
            //txtAddress.setText(employee.getAddress())
        }
    }


    internal inner class OfferZoneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OfferClickHandler {
        override fun onClickOfferItem(offerData: OfferData) {
             LOG.d(">>CLICK-OFFERZONE ITEM","-"+offerData)
        }

        private val _tvOfferText: TextView
        private val _recylerOfferZOne: RecyclerView

        init {
            _tvOfferText = itemView.findViewById(R.id._tvOfferText)

            _recylerOfferZOne = itemView.findViewById(R.id._recylerOfferZOne)
        }

        fun setOfferZOneDetails(homePageSection: HomeViewData, position:Int) {
            _tvOfferText.setText(homePageSection.getViewText())

            var offerList = ArrayList<OfferData>()
            for (i in 0..11){
                offerList.add(OfferData())
            }
            var offerZoneAdapter = OfferZoneAdapter(this)
            _recylerOfferZOne.adapter = offerZoneAdapter

            offerZoneAdapter.setOfferList(offerList)


        }
    }

    internal inner class TrendingViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val _tvCategoriesHeader: TextView

        init {
            //_tvCategoriesHeader = itemView.findViewById(R.id._tvCategoriesHeader)
        }

        fun setBannerDetails(homePageSection: HomeViewData) {
            //_tvCategoriesHeader.setText(homePageSection.getTitle())
            //txtAddress.setText(employee.getAddress())
        }
    }

    internal inner class BannerSmallViewHoler(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //private val _tvCategoriesHeader: TextView

        init {
            //_tvCategoriesHeader = itemView.findViewById(R.id._tvCategoriesHeader)
        }

        fun setBannerDetails(homePageSection: HomeViewData) {
            //_tvCategoriesHeader.setText(homePageSection.getTitle())
            //txtAddress.setText(employee.getAddress())
        }
    }


}