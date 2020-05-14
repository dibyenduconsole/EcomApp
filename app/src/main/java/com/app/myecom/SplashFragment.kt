package com.app.myecom

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.app.myecom.utilities.LOG

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    var TAG = "SplashScreenFragment"

    lateinit var handler: Handler
    lateinit var runnable:Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler()
        runnable = Runnable {

            view.findNavController()
                .navigate(R.id.action_splashFragment_to_dashboardFragment)
        }


        handler.postDelayed(runnable,3*1000)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        LOG.d(TAG,"-Destroy View")
        if(handler != null){
            if(runnable != null) {
                handler.removeCallbacks(runnable)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        LOG.d(TAG,"-Detach")
    }
}
