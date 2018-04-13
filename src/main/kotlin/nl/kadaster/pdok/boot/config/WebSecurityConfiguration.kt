package nl.kadaster.pdok.boot.config


import nl.kadaster.pdok.boot.config.oath.PasswordFlowProvider
import nl.kadaster.pdok.boot.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
class WebSecurityConfiguration : WebSecurityConfigurerAdapter()
{
    companion object {
        private val LOG = LoggerFactory.getLogger(WebSecurityConfiguration::class.java)
    }

    init {
        LOG.info("Loading WebSecurityConfiguration...")
    }

    @Autowired
    private lateinit var passwordFlowProvider: PasswordFlowProvider

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(passwordFlowProvider)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }


    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().anyRequest().permitAll()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        http.csrf().disable()
        http.httpBasic().disable()
    }


    @Bean
    fun passwordEncoder() : PasswordEncoder
    {
        return BCryptPasswordEncoder()
    }
}