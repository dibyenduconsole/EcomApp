package com.app.myecom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.myecom.adapter.HomeRecylerAdapter
import com.app.myecom.utilities.LOG
import com.app.myecom.view.ViewType
import com.app.myecom.view.model.HomeViewData
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONArray

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    var homeResponse = ViewType.homeResponse
    var listHomeResult = ArrayList<HomeViewData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        try {
            var jsonArray = JSONArray(homeResponse)
            for (m in 0 until jsonArray.length()){
                var jsonObject = jsonArray.getJSONObject(m)
                var viewData = HomeViewData()
                viewData.setViewText(jsonObject.optString("view_text"))
                viewData.setViewType(jsonObject.optString("view_type").toInt())
                listHomeResult.add(viewData)
            }

            var adapter = context?.let { HomeRecylerAdapter(it,listHomeResult) }
            _recylerViewHome.adapter = adapter


        }catch (e:Exception){
            LOG.d(">Exp:",""+e)
        }

    }



}
