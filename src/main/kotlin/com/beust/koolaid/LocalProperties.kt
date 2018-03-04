package com.beust.koolaid

import com.google.inject.Singleton
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * Encapsulate read access to local.properties.
 */
@Singleton
class LocalProperties {
    private val localProperties: Properties by lazy {
        Properties().apply {
            Paths.get("local.properties").let { path ->
                if (path.toFile().exists()) {
                    Files.newInputStream(path).use {
                        load(it)
                    }
                }
            }
        }
    }

    fun get(name: String, defaultValue: String? = null): String? = localProperties.getProperty(name) ?: defaultValue

    fun db(def: String? = "inMemory") : String? {
        val result = get("DATABASE", def)
        if (result != null && result != "postgres" && result != "inMemory") {
            throw IllegalArgumentException("Illegal database $result, should be 'postgres' or 'inMemory'")
        }
        return result
    }
    fun dbUser(def: String? = null) = get("DATABASE_USER", def)
    fun dbPassword(def: String? = null) = get("DATABASE_PASSWORD", def)
    fun dbUrl(def: String? = null) = get("DATABASE_URL", def)
}
