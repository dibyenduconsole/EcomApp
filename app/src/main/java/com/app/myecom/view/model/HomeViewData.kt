package com.app.myecom.view.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class HomeViewData {
    @SerializedName("view_type")
    @Expose
    private var viewType: Int? = null
    @SerializedName("view_text")
    @Expose
    private var viewText: String? = null

    fun getViewType(): Int? {
        return viewType
    }

    fun setViewType(viewType: Int?) {
        this.viewType = viewType
    }

    fun getViewText(): String? {
        return viewText
    }

    fun setViewText(viewText: String?) {
        this.viewText = viewText
    }
}