package com.example.admobtuttorial

import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.gms.ads.*
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import com.example.admobtuttorial.natipe.TemplateView
import com.example.admobtuttorial.natipe.NativeTemplateStyle
import com.google.android.gms.ads.formats.UnifiedNativeAd
import com.google.android.gms.ads.AdLoader





class MainActivity : BaseActivity() {

    lateinit var relatipe : RelativeLayout
    lateinit var lin : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adReq = AdRequest.Builder().build()
        relatipe = findViewById(R.id.View_Relatip)

        lin = LinearLayout(this)
        lin.orientation = LinearLayout.VERTICAL

        relatipe.addView(lin)


        Banner(adReq)
        Inters(adReq)
        Reward(adReq)
        Native(adReq)
    }

    fun Banner(adReq : AdRequest){
        // first alternatif
        val adView : AdView
        adView = findViewById(R.id.adView)
        adView.loadAd(adReq)

        //secend alternatif

        val text = TextView(this)

        val adView2 = AdView(this)
        adView2.adSize = AdSize.SMART_BANNER
        adView2.adUnitId = getString(R.string.APP_ID_AD_BANNER)
        adView2.adListener = object : AdListener() {
            override fun onAdOpened() {
                super.onAdOpened()
                text.text = "ADOPEN"
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                text.text = "ADLOADED"
            }

        }

        adView2.loadAd(adReq)

        lin.addView(adView2)
        lin.addView(text)
    }
    fun Inters(adReq: AdRequest){
        val text = TextView(this)

        val inter = InterstitialAd(this)
        inter.adUnitId = getString(R.string.APP_ID_AD_INTERST)
        inter.adListener = object :AdListener(){
            override fun onAdClosed() {
                super.onAdClosed()
                inter.loadAd(adReq)
            }

            override fun onAdImpression() {
                super.onAdImpression()
                text.text = "ONIMPRSSI"
            }


            override fun onAdFailedToLoad(p0: Int) {
                super.onAdFailedToLoad(p0)
                text.text = "Failed Code :"+ p0
            }


            override fun onAdLoaded() {
                super.onAdLoaded()
                text.text = "ADLOADED"
            }

        }

        inter.loadAd(adReq)

        val button = Button(this)
        lin.addView(button)
        lin.addView(text)
        button.setText("INTERSTITIAL")
        button.setOnClickListener(View.OnClickListener {
            if(inter.isLoaded) inter.show()
        })
    }
    fun Reward(adReq: AdRequest){

        val rewardedAd = MobileAds.getRewardedVideoAdInstance(this)
        rewardedAd.rewardedVideoAdListener = object : RewardedVideoAdListener{
            override fun onRewardedVideoAdClosed() {
                rewardedAd.loadAd(getString(R.string.APP_ID_AD_REWARD),adReq)
            }

            override fun onRewardedVideoAdLeftApplication() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdLoaded() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdOpened() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoCompleted() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewarded(p0: RewardItem?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoStarted() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onRewardedVideoAdFailedToLoad(p0: Int) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
        rewardedAd.loadAd(getString(R.string.APP_ID_AD_REWARD),adReq)

        val button = Button(this)
        lin.addView(button)
        button.setText("REWARD")
        button.setOnClickListener(View.OnClickListener {
            if(rewardedAd.isLoaded) rewardedAd.show()
        })
    }
    fun Native(adReq: AdRequest){
        val templateView : TemplateView
        templateView = findViewById(R.id.nativeAD)

        val adLoader = AdLoader.Builder(this, getString(R.string.APP_ID_AD_NATIVE))
            .forUnifiedNativeAd { unifiedNativeAd ->
                val styles = NativeTemplateStyle.Builder().build()
                templateView.setStyles(styles)
                templateView.setNativeAd(unifiedNativeAd)
            }
            .build()

        adLoader.loadAd(adReq)
    }
}
