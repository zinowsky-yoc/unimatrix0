package com.yoc.examplevisxschowcase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yoc.examplevisxschowcase.databinding.ActivityVisxInterstitialBinding
import com.yoc.visx.sdk.adview.VisxAd

class VisxInterstitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisxInterstitialBinding

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VisxInterstitialActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisxInterstitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        VisxAd(this).displayInterstitial("912263", "yoc.com")
    }
}