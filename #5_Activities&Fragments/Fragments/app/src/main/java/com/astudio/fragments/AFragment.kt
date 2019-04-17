package com.astudio.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_a.*
/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AFragment.OnButtonClicked] interface
 * to handle interaction events.
 * Use the [AFragment.newInstance] factory method to
 * create an instance of this fragment.
 * [AFragment] contains Button for display [BFragment] in PORTRAIT configuration
 * and increment number of clicks Button.
 */
class AFragment : Fragment() {

    private var listener: OnButtonClicked? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnButtonClicked) {
            listener = context
        } else {
            throw ClassCastException(
                context.toString() + " must implement OnButtonClicked!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onStart() {
        super.onStart()
        button.setOnClickListener {
            listener?.onButtonClicked()
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnButtonClicked {
        fun onButtonClicked()
    }

    companion object{
        val TAG = AFragment::class.java.name

        fun newInstance():AFragment{
            return AFragment()
        }
    }
}
