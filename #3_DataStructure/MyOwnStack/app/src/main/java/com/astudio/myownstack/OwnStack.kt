package com.astudio.myownstack


import java.util.ArrayList

class OwnStack<T> {
    private val tArrayList: ArrayList<T>
    var top: Int = 0

    public val isEmpty: Boolean
        get() = tArrayList.isEmpty()

    public val getTop: Int
        get() = top

    init {
        tArrayList = ArrayList()
        top = -1
    }

    public fun push(tItems: T) {
        val index = ++top
        tArrayList.add(index, tItems)
    }

    public fun pop(): T? {
        val index = top--
        return if (!isEmpty && index > -1) {
            tArrayList.removeAt(index)
        } else
            null
    }

    fun top(): T {
        return tArrayList[top]
    }

}