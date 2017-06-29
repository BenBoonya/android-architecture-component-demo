package com.ben.boonya.architecturecomponentdemo.film

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ben.boonya.architecturecomponentdemo.R

class FilmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_container)
        title = ""

        if (savedInstanceState == null) {
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.fragment_container, FilmDetailFragment.newInstance()).commit()
        }
    }
}
