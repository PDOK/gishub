package nl.kadaster.pdok.boot.config.oath

import nl.kadaster.pdok.boot.config.WebSecurityConfiguration
import nl.kadaster.pdok.boot.controller.RouteConstants.MOUNT
import nl.kadaster.pdok.boot.model.Recht
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import java.util.stream.Stream
import javax.sql.DataSource
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore
import org.springframework.security.oauth2.provider.approval.ApprovalStore
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer


@Configuration
@EnableAuthorizationServer
class OathAuthorizationConfiguration(
        @Autowired private val dataSource : DataSource,
        @Autowired @Qualifier("authenticationManagerBean") private val authenticationManager : AuthenticationManager,
        @Autowired private val userDatailsServiceImpl : UserDetailsService
) : AuthorizationServerConfigurerAdapter()
{
    companion object {
        private val LOG = LoggerFactory.getLogger(OathAuthorizationConfiguration::class.java)
        private val OAUTH_SCOPES = arrayOf("read", "write")
        private val OAUTH_GRAND_TYPES = arrayOf("password", "refresh_token")
        val RESOURCE_ID = "gishub_resources"
    }

    init {
        LOG.info("Loading OathAuthorizationConfiguration...")
    }

    private val tokenStore: TokenStore = JdbcTokenStore(dataSource)

    @Throws(Exception::class)
    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints.prefix("/api")
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDatailsServiceImpl)
                .approvalStoreDisabled()
    }


    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients
                .jdbc(dataSource)
                .withClient("gishub")
                .secret("123456")
                .authorizedGrantTypes("password", "refresh_token")
                .authorities("ROLE_INLOGGEN")
                .scopes("read", "write")
    }

    @Bean
    fun clientDetailsService(): JdbcClientDetailsService {
        return JdbcClientDetailsService(dataSource)
    }

    @Bean
    fun approvalStore(): ApprovalStore {
        return JdbcApprovalStore(dataSource)
    }

    @Bean
    fun authorizationCodeServices(): AuthorizationCodeServices {
        return JdbcAuthorizationCodeServices(dataSource)
    }

    @Bean
    fun tokenStore(): TokenStore {
        return tokenStore
    }

    @Throws(Exception::class)
    override fun configure(oauthServer: AuthorizationServerSecurityConfigurer?) {
        // clients mogen via form POST zich authentiseren ipv alleen via Http Basic header
        oauthServer!!.allowFormAuthenticationForClients()
        oauthServer.tokenKeyAccess("isAnonymous()").checkTokenAccess("permitAll()")
    }
}