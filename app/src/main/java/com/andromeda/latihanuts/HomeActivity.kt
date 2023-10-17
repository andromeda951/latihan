package com.andromeda.latihanuts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val actionBar: ActionBar? = supportActionBar

        actionBar?.title = "UNION"
        actionBar?.setIcon(R.drawable.app_logo)

        actionBar?.setDisplayUseLogoEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        // Tab Layout
        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)

        val adapter = UnionAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TABS_FIXED[position])
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)

        val name = intent.getStringExtra("NAME")

        val nameMenuItem: MenuItem? = menu?.findItem(R.id.username)
        nameMenuItem?.title = "Hi, $name"

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> finish()
        }

        return super.onOptionsItemSelected(item)
    }

}