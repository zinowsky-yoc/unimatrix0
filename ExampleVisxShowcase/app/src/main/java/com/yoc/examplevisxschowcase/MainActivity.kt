package com.yoc.examplevisxschowcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yoc.examplevisxschowcase.databinding.ActivityMainBinding
import net.consentmanager.sdk.CMPConsentTool

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CMPConsentTool.createInstance(
            this,
            20296,
            "consentmanager.mgr.consensu.org",
            "YOC Showcase",
            "EN"
        )
        setupUi()
    }

    private fun setupUi() {
        binding.buttonBanner.setOnClickListener {
            VisxBannerActivity.start(this)
        }
        binding.buttonInterstitial.setOnClickListener {
            VisxInterstitialActivity.start(this)
        }
        binding.buttonUniversal.setOnClickListener {
            VisxUniversalActivity.start(this)
        }
        binding.buttonUniversalInFeed.setOnClickListener {
            VisxUniversalInFeedActivity.start(this)
        }
    }
}