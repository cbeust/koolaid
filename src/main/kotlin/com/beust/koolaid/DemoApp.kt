package com.beust.koolaid

import com.hubspot.dropwizard.guice.GuiceBundle
import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class DemoApp : Application<DemoConfig>() {
    private lateinit var guiceBundle: GuiceBundle<DemoConfig>

    override fun initialize(configuration: Bootstrap<DemoConfig>) {
        configuration.addBundle(AssetsBundle("/assets", "/", "index.html", "static"));
        guiceBundle = GuiceBundle.newBuilder<DemoConfig>()
                .addModule(DemoModule())
                .setConfigClass(DemoConfig::class.java!!)
                .build()

        configuration.addBundle(guiceBundle)
    }

    override fun run(config: DemoConfig, env: Environment) {
        env.jersey().register(guiceBundle.injector.getInstance(ViewService::class.java))

        env.healthChecks().register("template", DemoCheck(config.version))
    }
}