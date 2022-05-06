package com.yoc.examplevisxschowcase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.yoc.examplevisxschowcase.databinding.ActivityVisxUniversalBinding
import com.yoc.visx.sdk.VisxAdManager
import com.yoc.visx.sdk.adview.tracker.VisxCallbacks
import com.yoc.visx.sdk.util.ad.AdSize

class VisxUniversalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisxUniversalBinding
    private lateinit var visxAdManager: VisxAdManager

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VisxUniversalActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVisxUniversalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initVisxUniversal()
    }

    private fun initVisxUniversal() {
        visxAdManager = VisxAdManager.Builder()
            .visxAdUnitID("912268")
            .context(this)
            .adSize(AdSize.MEDIUM_RECTANGLE_300x250)
            .anchorView(binding.scrollView)
            .callback(object : VisxCallbacks() {
                override fun onAdRequestStarted(visxAdManager: VisxAdManager?) {
                    super.onAdRequestStarted(visxAdManager)
                    Log.i("---> Banner", "")
                }

                override fun onAdResponseReceived(visxAdManager: VisxAdManager?, message: String?) {
                    super.onAdResponseReceived(visxAdManager, message)
                }

                override fun onAdLoadingFinished(visxAdManager: VisxAdManager?, message: String?) {
                    super.onAdLoadingFinished(visxAdManager, message)
                    binding.inlineContainer.addView(visxAdManager?.adContainer)
                }

                override fun onAdLoadingFailed(message: String?, errorCode: Int, isFinal: Boolean) {
                    super.onAdLoadingFailed(message, errorCode, isFinal)
                }
            }).build()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::visxAdManager.isInitialized) {
            visxAdManager.stop()
        }
    }
}