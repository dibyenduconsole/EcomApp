package com.app.myecom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_dashboard.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigationBar = view.findViewById(
            R.id.bottom_navigationView
        ) as BottomNavigationView

        var navHostFragment = childFragmentManager.findFragmentById(R.id.navigation_bottom) as NavHostFragment
        NavigationUI.setupWithNavController(bottom_navigationView, navHostFragment.navController)


    }



    companion object{

        var bottom_navigationBar:BottomNavigationView? = null

    }
}
