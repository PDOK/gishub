package nl.kadaster.pdok.boot.model

import org.springframework.security.core.GrantedAuthority

enum class Recht(
        private val authority : String
) : GrantedAuthority {

    ROLE_INLOGGEN("ROLE_INLOGGEN");


    override fun getAuthority(): String {
        return authority
    }
}