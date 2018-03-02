package com.beust.koolaid

import io.dropwizard.Application
import io.dropwizard.setup.Environment

class RestStubApp : Application<RestStubConfig>() {

    override fun run(config: RestStubConfig, env: Environment) {
        env.jersey().register(ViewService())

        env.healthChecks().register("template", RestStubCheck(config.version))
    }
}