package org.sherman.magic.weatherportfolio.Adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Admin on 12/9/2017.
 */
class ForecastViewPagerAdapter(fm: FragmentManager?, fragments: List<Fragment>) : FragmentPagerAdapter(fm) {
    var fragments = fragments

    override fun getItem(position: Int): Fragment {
        return fragments?.get(position)!!
    }

    override fun getCount(): Int {
        return fragments!!.size
    }
}