package com.astudio.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astudio.fragments.Activity.Companion.CLICK_COUNTER
import kotlinx.android.synthetic.main.fragment_b.*
/**
 * A simple [Fragment] subclass.
 *Use the [BFragment.newInstance] factory method to
 * create an instance of this fragment.
 * [BFragment] contains TextView that display numbers of Button from [AFragment] clicks.
 */
class BFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onStart() {
        super.onStart()
        val bundle = arguments
        if (bundle != null) {
            val counter = bundle.getInt(CLICK_COUNTER)
            setCounter(counter.toString())
        }
    }

    fun setCounter(counter: String) {
        textView.setText(counter)
    }

    companion object{
        val TAG = BFragment::class.java.name
        fun newInstance(clickCounter: Int):BFragment{
            val bundle = Bundle()
            val fragment = BFragment()
            bundle.putInt(CLICK_COUNTER, clickCounter)
            fragment.arguments = bundle
            return fragment
        }
    }

}
