package com.andromeda.latihanuts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

val TABS_FIXED = listOf(
    R.string.class_enrollment,
    R.string.profile
)

class UnionAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return TABS_FIXED.size
    }

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> return ListFragment()
        }

        return ProfileFragment()
    }

}