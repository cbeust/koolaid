package com.beust.koolaid

class ViewsDaoInMemory: ViewsDao {
    var count = 0
    override fun getViewCountAndIncrement(): Int {
        val result = count
        count++
        return result
    }
}