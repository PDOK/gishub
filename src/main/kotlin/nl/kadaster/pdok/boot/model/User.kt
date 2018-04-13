package nl.kadaster.pdok.boot.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "users")
data class User(
    @Column(name = "actief") private val actief : Boolean = false,
    @Column(name = "enabled") private val enabled : Boolean = false,
    @Column(name = "expired") private val expired : Boolean = true,
    @Column(name = "locked") private val locked : Boolean = true,
    @Column(name = "username") private val username : String,
    @Column(name = "password") private val password : String

) : AbstractHibernateObject(), UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> {
        return Recht.values().asList()
    }

    override fun isEnabled(): Boolean {
      return enabled
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
       return !expired
    }

    override fun getPassword(): String {
       return password
    }

    override fun isAccountNonExpired(): Boolean {
        return !expired
    }

    override fun isAccountNonLocked(): Boolean {
       return !locked
    }
}