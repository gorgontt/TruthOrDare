package com.example.truthordare.viewpager

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.truthordare.R
import com.example.truthordare.adapter.ViewPagerAdapter
import me.relex.circleindicator.CircleIndicator3

class ViewPagerActivity : AppCompatActivity() {

    //lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_pager)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewPager: ViewPager2 = findViewById(R.id.pager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            SoftModeFragment(),
            HotModeFragment(),
            HardModeFragment(),
            ExtremeModeFragment()
        )

        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter

        val indicator: CircleIndicator3 = findViewById(R.id.indicator)
        indicator.setViewPager(viewPager)
    }

//    override fun onBackPressed() {
//
//        if (viewPager.currentItem == 0){
//            super.onBackPressed()
//        }else{
//            viewPager.currentItem -= 1
//        }
//
//    }
}