package com.beust.koolaid

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Singleton

class DemoModule : Module {
    override fun configure(binder: Binder) {
        val daoClass = when(LocalProperties().get(LocalProperty.DATABASE)) {
            "postgresql" -> ViewsDaoPostgres::class.java
            else -> ViewsDaoInMemory::class.java
        }
        binder.bind(ViewsDao::class.java).to(daoClass).`in`(Singleton::class.java)
    }
}
