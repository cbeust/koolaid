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

//    fun getOrThrows(name: String) = get(name)
//                ?: throw IllegalArgumentException("Couldn't find $name in local.properties")
}
