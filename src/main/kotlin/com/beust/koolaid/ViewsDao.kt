package com.beust.koolaid

interface ViewsDao {
    var count: Int
    fun getViewCountAndIncrement(): Int = count++
}

