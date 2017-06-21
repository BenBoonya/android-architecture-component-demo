package com.ben.boonya.architecturecomponentdemo.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ben.boonya.architecturecomponentdemo.characterlist.CharacterListFragment
import com.ben.boonya.architecturecomponentdemo.filmlist.FilmListFragment

/**
 * Created by oozou on 6/21/2017 AD.
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment? {
        if (position == 0) return FilmListFragment.newInstance()
        else if (position == 1) return CharacterListFragment.newInstance()
        return null
    }

    override fun getPageTitle(position: Int): CharSequence {
        if (position == 0) return "All Films"
        else if (position == 1) return "Characters"
        return "-"
    }

}