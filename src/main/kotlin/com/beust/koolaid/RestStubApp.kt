package com.beust.koolaid

import io.dropwizard.Application
import io.dropwizard.assets.AssetsBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class RestStubApp : Application<RestStubConfig>() {

    override fun initialize(configuration: Bootstrap<RestStubConfig>) {
        configuration.addBundle(AssetsBundle("/assets", "/", "index.html", "static"));
    }

    override fun run(config: RestStubConfig, env: Environment) {
        env.jersey().register(ViewService())

        env.healthChecks().register("template", RestStubCheck(config.version))
    }
}