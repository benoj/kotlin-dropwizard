package com.github.benoj

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.benoj.resources.HelloWorldResource
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundleConfiguration
import io.dropwizard.Application
import io.dropwizard.configuration.ResourceConfigurationSourceProvider
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.glassfish.hk2.utilities.binding.AbstractBinder
import zone.dragon.dropwizard.HK2Bundle

class Server : Application<ServerConfiguration>() {

    override fun initialize(bootstrap: Bootstrap<ServerConfiguration>) {

        bootstrap.configurationSourceProvider = ResourceConfigurationSourceProvider()
        bootstrap.addBundle(
                TemplateConfigBundle(TemplateConfigBundleConfiguration())
        )
        bootstrap.addBundle(HK2Bundle())
        bootstrap.objectMapper.apply {
            registerModule(KotlinModule())
        }
    }

    override fun run(configuration: ServerConfiguration, environment: Environment) {

        environment.jersey().apply {
            register(object: AbstractBinder(){
                override fun configure() {

                }
            })

            register(HelloWorldResource::class.java)
        }
    }
}