package com.beust.koolaid

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Singleton

class DemoModule : Module {
    override fun configure(binder: Binder) {
        val s: String? = null
        when(s) {
            null -> "a"
            else -> "b"
        }
        val daoClass = when(LocalProperties().get(LocalProperty.DATABASE)) {
            Database.POSTGRESQL.value -> ViewsDaoPostgres::class.java
            else -> ViewsDaoInMemory::class.java
        }
        binder.bind(ViewsDao::class.java).to(daoClass).`in`(Singleton::class.java)
    }
}
