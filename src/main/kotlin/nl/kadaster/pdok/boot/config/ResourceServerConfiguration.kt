package nl.kadaster.pdok.boot.config

import nl.kadaster.pdok.boot.config.oath.OathAuthorizationConfiguration.Companion.RESOURCE_ID
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer



@Configuration
@EnableResourceServer
class ResourceServerConfiguration : ResourceServerConfigurerAdapter()
{
    companion object {
        private val LOG = LoggerFactory.getLogger(ResourceServerConfiguration::class.java)
    }

    init {
        LOG.info("Loading ResourceServerConfiguration...")
    }


    override fun configure(resources: ResourceServerSecurityConfigurer) {
        // @formatter:off
        resources.resourceId(RESOURCE_ID)
        // @formatter:on
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // @formatter:off
        http.authorizeRequests().anyRequest().permitAll()
        // http.csrf().disable();
        // http.httpBasic().disable();``
        // @formatter:on
    }
}