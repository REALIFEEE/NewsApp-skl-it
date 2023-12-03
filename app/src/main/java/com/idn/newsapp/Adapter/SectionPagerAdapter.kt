package com.idn.newsapp.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.idn.newsapp.AboutQuranFragment
import com.idn.newsapp.UI.Aljazeera
import com.idn.newsapp.UI.WarningFragment
import com.idn.newsapp.UI.commonFragment

class SectionPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun getItemCount() = 4

    override fun createFragment(position: Int): Fragment {
       return when(position) {
           0 -> commonFragment()
           1 -> AboutQuranFragment()
           2 -> Aljazeera()
           3 -> WarningFragment()
           else -> Aljazeera()
       }
    }
}