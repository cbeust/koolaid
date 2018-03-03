package com.beust.koolaid

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Singleton

class DemoModule : Module {
    override fun configure(binder: Binder) {
        binder.bind(ViewsDao::class.java).to(ViewsDaoInMemory::class.java).`in`(Singleton::class.java)
    }
}
