package com.beust.koolaid

import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class DemoApp : Application<DemoConfig>() {

    override fun initialize(configuration: Bootstrap<DemoConfig>) {
        configuration.addBundle(AssetsBundle("/assets", "/", "index.html", "static"));
    }

    override fun run(config: DemoConfig, env: Environment) {
        env.jersey().register(ViewService())

        env.healthChecks().register("template", DemoCheck(config.version))
    }
}