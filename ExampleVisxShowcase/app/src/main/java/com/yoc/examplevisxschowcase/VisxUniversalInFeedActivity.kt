package com.yoc.examplevisxschowcase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yoc.examplevisxschowcase.databinding.ActivityVisxUniversalInFeedBinding
import com.yoc.visx.sdk.VisxAdManager
import com.yoc.visx.sdk.adview.tracker.VisxCallbacks
import com.yoc.visx.sdk.util.ad.AdSize

class VisxUniversalInFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVisxUniversalInFeedBinding
    private lateinit var adapter: InfeedAdapter
    private lateinit var visxAdManager: VisxAdManager

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, VisxUniversalInFeedActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visx_universal_in_feed)
        binding = ActivityVisxUniversalInFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        initVisxSDK()
    }

    private fun initAdapter() {
        adapter = InfeedAdapter(this)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.addAll(HardcodedUtil.getArticles(this))
    }

    private fun initVisxSDK() {
        visxAdManager = VisxAdManager.Builder()
            .context(this)
            .callback(object : VisxCallbacks() {
                override fun onAdResponseReceived(visxAdManager: VisxAdManager?, message: String?) {
                    Log.i("---> Universal InFeed", "onAdResponseReceived() $message")
                }

                override fun onAdLoadingFinished(visxAdManager: VisxAdManager?, message: String?) {
                    Log.i("---> Universal InFeed", "onAdLoadingFinished() $message")
                    displayAd()
                }

                override fun onAdLoadingFailed(message: String?, errorCode: Int, isFinal: Boolean) {
                    Log.i(
                        "---> Universal InFeed",
                        "onAdLoadingFailed() $message errorCode: $errorCode"
                    )
                }

                override fun onAdSizeChanged(width: Int, height: Int) {
                    Log.i(
                        "---> Universal InFeed",
                        "onAdSizeChanged() width: $width height: $height"
                    )
                }
            })
            .visxAdUnitID("912268")
            .adSize(AdSize.SMARTPHONE_320x50)
            .appDomain("yoc.com")
            .anchorView(binding.recyclerView)
            .build()
    }

    private fun displayAd() {
        adapter.displayCreative(visxAdManager)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::visxAdManager.isInitialized) {
            visxAdManager.stop()
        }
    }
}