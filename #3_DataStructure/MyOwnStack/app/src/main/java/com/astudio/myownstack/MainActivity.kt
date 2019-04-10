package com.astudio.myownstack

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal val buttonPush = findViewById(R.id.push_button) as Button
    internal val buttonPop = findViewById(R.id.pop_button) as Button
    internal var editText = findViewById(R.id.edit_text) as EditText
    internal var textView = findViewById(R.id.text_view) as TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var stackArrayList = OwnStack<String>()

        val onClickListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.push_button -> {
                    stackArrayList.push(editText.text.toString())
                    textView.setText(stackArrayList.toString())
                }
                R.id.pop_button -> if (!stackArrayList.isEmpty) {
                    stackArrayList.pop()
                    textView.setText(stackArrayList.toString())
                } else if (stackArrayList.top=== -1) {
                    textView.text = ""
                }
                else -> {
                }
            }
        }

        buttonPush.setOnClickListener(onClickListener)
        buttonPop.setOnClickListener(onClickListener)
    }

}