package com.beust.koolaid

import com.codahale.metrics.health.HealthCheck


class DemoCheck(private val version: String) : HealthCheck() {

    @Throws(Exception::class)
    override fun check(): HealthCheck.Result {
        return Result.healthy("Version $version, health is good")
//        return if (PersonDB.getCount() === 0) {
//            HealthCheck.Result.unhealthy("No persons in DB! Version: " + this.version)
//        } else HealthCheck.Result.healthy("OK with version: " + this.version +
//                ". Persons count: " + PersonDB.getCount())
    }
}