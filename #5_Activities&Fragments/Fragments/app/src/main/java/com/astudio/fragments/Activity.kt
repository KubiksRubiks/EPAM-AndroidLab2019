package com.astudio.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
/**
 * A simple [Activity] subclass.
 * This class must implement the
 * [AFragment.OnButtonClicked] interface
 * to handle interaction events.
 * Activity contains [AFragment] and [BFragment]
 */
class Activity : FragmentActivity(), AFragment.OnButtonClicked  {

    private var clickCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)

        if (savedInstanceState != null) {
            clickCounter = savedInstanceState.getInt(CLICK_COUNTER)
        }
        addFragment()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLICK_COUNTER, clickCounter)
    }

    private fun addFragment(){
        replaceFragment(
            R.id.fragment_container,
            AFragment.newInstance(),
            AFragment.TAG,
            null)
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            replaceFragment(
                R.id.fragmentB_container,
                BFragment.newInstance(clickCounter),
                BFragment.TAG,
                null)
        }
    }

    private fun replaceFragment(
        containerViewId: Int,
        fragment: Fragment,
        fragmentTag: String,
        backStackStateName: String?
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(containerViewId, fragment, fragmentTag)
            .addToBackStack(backStackStateName)
            .commit()
    }

    override fun onButtonClicked() {
        clickCounter++

        val fragmentB = supportFragmentManager.findFragmentByTag(BFragment.TAG) as BFragment?
        if (fragmentB != null && fragmentB.isVisible) {
            fragmentB.setCounter(clickCounter.toString())
        } else {
            replaceFragment(
                R.id.fragment_container,
                BFragment.newInstance(clickCounter),
                BFragment.TAG,
                null)
        }
    }

    companion object{
        const val CLICK_COUNTER = "CLICK_COUNTER"
    }
}
