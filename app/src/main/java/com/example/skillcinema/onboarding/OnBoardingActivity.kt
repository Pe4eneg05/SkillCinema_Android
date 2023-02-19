package com.example.skillcinema.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.skillcinema.R
import com.example.skillcinema.databinding.ActivityOnBoardingBinding
import com.example.skillcinema.screen.HomeFragment
import com.example.skillcinema.screen.MainActivity
import com.example.skillcinema.splashscreen.SplashScreenActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class OnBoardingActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter: OnBoardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var sharedPreferences: SharedPreferences? = null
    var position = 0

    private lateinit var binding: ActivityOnBoardingBinding
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.overridePendingTransition(R.anim.activity_in, R.anim.activity_out)

        if (restorePrefData()) {
            val i = Intent(applicationContext, SplashScreenActivity::class.java)
            startActivity(i)
            finish()
        }

        tabLayout = findViewById(R.id.tab_indicator)

        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(
            OnBoardingData(
                "Узнавай\nо премьерах",
                R.drawable.onboarding_1
            )
        )
        onBoardingData.add(
            OnBoardingData(
                "Создавай\nколлекции",
                R.drawable.onboarding_2
            )
        )
        onBoardingData.add(
            OnBoardingData(
                "Делись\nс друзьями",
                R.drawable.onboarding_3
            )
        )

        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        handler = Handler()

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position == onBoardingData.size - 1) {
                    handler.postDelayed({
                        savePrefData()
                        val i = Intent(applicationContext, SplashScreenActivity::class.java)
                        startActivity(i)
                        finish()
                    }, 1500)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.skip.setOnClickListener {
            savePrefData()
            val i = Intent(applicationContext, SplashScreenActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<OnBoardingData>) {

        onBoardingViewPager = findViewById(R.id.screenPager)
        onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)

    }

    private fun savePrefData() {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean {
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }
}