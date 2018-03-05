package com.beust.koolaid

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Singleton

class DemoModule : Module {
    override fun configure(binder: Binder) {
        val localProperties = LocalProperties()
        binder.bind(LocalProperties::class.java).toInstance(localProperties)

        val envDbUrl = System.getenv("DATABASE_URL") != null
        val local = localProperties.get(LocalProperty.DATABASE)
        val daoClass =
            if (envDbUrl || local == Database.POSTGRESQL.value) ViewsDaoPostgres::class.java
            else ViewsDaoInMemory::class.java

//        val daoClass = when(localProperties.get(LocalProperty.DATABASE)) {
//            Database.POSTGRESQL.value -> ViewsDaoPostgres::class.java
//            else -> ViewsDaoInMemory::class.java
//        }
        binder.bind(ViewsDao::class.java).to(daoClass).`in`(Singleton::class.java)
    }
}
