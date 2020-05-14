package com.app.myecom
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.myecom.reciver.ConnectivityReceiver
import com.google.android.material.snackbar.Snackbar


//THIS IS THE BASE ACTIVITY OF ALL ACTIVITIES OF THE APPLICATION.

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {
    private var mSnackBar: Snackbar? = null

    lateinit var broadcastReceiver:BroadcastReceiver
    private lateinit var customProgressDialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        broadcastReceiver = ConnectivityReceiver()


    }


    private fun showMessage(isConnected: Boolean) {



        if (!isConnected) {

            val messageToUser = "You are offline now." //TODO

            mSnackBar = Snackbar.make(findViewById(R.id.rootLayout), messageToUser, Snackbar.LENGTH_INDEFINITE) //Assume "rootLayout" as the root layout of every activity.

            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }


    }

    fun showCustomProgrssDialog(activity: Context) {

        customProgressDialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)
        customProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customProgressDialog.setCancelable(false)
        customProgressDialog.setContentView(R.layout.dialog_custom_progress)

        if (customProgressDialog.isShowing()) {

        } else {
            customProgressDialog.show()
        }

    }

    fun hideCustomProgrssDialog() {
        if (::customProgressDialog.isInitialized && customProgressDialog != null) {
            customProgressDialog.dismiss()
        }
    }


    fun alertDialogWithMsg(activity: Context, image:Int, textHeader:String, textMsg:String){

        var dialog = Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_alert_msg)
        dialog.show()

        var tvHeader = dialog.findViewById<TextView>(R.id.tv_dlg_header)
        var tvMsg = dialog.findViewById<TextView>(R.id.tv_dlg_msg)
        var imgAlert = dialog.findViewById<ImageView>(R.id.img_dlg_alert)

        tvHeader.setText(textHeader)
        tvMsg.setText(textMsg)
        imgAlert.setImageResource(image)

        var btn_close = dialog.findViewById<Button>(R.id.btn_dlg_close)
        btn_close.setOnClickListener {
            dialog.dismiss()
        }


    }

    fun showToastMsg(context: Context, msg:String){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        registerReceiver(broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun onPause() {
        super.onPause()

        if(broadcastReceiver != null)
         unregisterReceiver(broadcastReceiver)
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }


    override fun onDestroy() {
        super.onDestroy()


    }
}