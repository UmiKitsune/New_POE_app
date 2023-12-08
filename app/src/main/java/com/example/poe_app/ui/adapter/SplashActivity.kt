package com.example.poe_app.ui.adapter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.example.poe_app.R
import com.example.poe_app.ui.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashArr = arrayOf(R.drawable.splash_affliction1,
            R.drawable.splash_affliction2, R.drawable.splash_affliction3)
        val nowSplash = splashArr.random()
        val splashImgView = findViewById<ImageView>(R.id.imgForSplash)
        splashImgView.setImageResource(nowSplash)

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
        }, 1300)

    }


}