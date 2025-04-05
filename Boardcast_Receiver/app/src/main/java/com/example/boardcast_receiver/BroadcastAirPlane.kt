package com.example.boardcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.boardcast_receiver.databinding.ActivityMainBinding

class BroadcastAirPlanne: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirPlaneMode = intent?.getBooleanExtra("state", false) ?: return

        // Gửi một intent mới về Activity
        // Gửi intent về MainActivity
        val uiIntent = Intent("boardcast_receiver")
        uiIntent.putExtra("airplane_mode", isAirPlaneMode)
        context?.sendBroadcast(uiIntent)
    }
}