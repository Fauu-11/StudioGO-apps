package com.example.ap3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import com.example.ap3.login.Home

class Splash : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Mengatur agar aktivitas full screen
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        // Menghilangkan title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // Mengatur layout untuk splash screen
        setContentView(R.layout.activity_splash)

        // Thread untuk menunggu beberapa detik sebelum melanjutkan ke aktivitas berikutnya
        val timer: Thread = object : Thread() {
            override fun run() {
                try {
                    // Menunggu selama 4 detik
                    sleep(4000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    // Selesai splash screen dan memulai aktivitas berikutnya
                    finish()
                    val intent = Intent(this@Splash, Home::class.java)
                    startActivity(intent)
                }
            }
        }
        timer.start()
    }
}
