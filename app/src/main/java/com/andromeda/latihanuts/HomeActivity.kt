package com.andromeda.latihanuts

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.viewpager2.widget.ViewPager2
import com.andromeda.latihanuts.retrofit.ApiServices
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeActivity : AppCompatActivity() {

    val TAG: String = "HomeActivity"

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

    override fun onStart() {
        super.onStart()
        getDataFromApi()
    }

    private fun getDataFromApi() {
        ApiServices.endpoint.getMovie()
            .enqueue(object : retrofit2.Callback<List<MovieModel>> {
                override fun onResponse(
                    call: Call<List<MovieModel>>,
                    response: Response<List<MovieModel>>
                ) {
//                    printLog(response.body().toString())
                    showMovies(response.body()!!)
                }

                override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                    printLog(t.toString())
                }
            })
    }

    private fun showMovies(movies: List<MovieModel>) {
        for (movie in movies) {
            printLog("Title: ${movie.title}")
//            printLog(movie.description)
//            printLog(movie.thumbnail)
        }
    }

    private fun printLog(Movies: String) {
        Log.d(TAG, Movies)
    }
}