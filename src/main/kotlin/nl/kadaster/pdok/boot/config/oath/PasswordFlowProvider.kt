package nl.kadaster.pdok.boot.config.oath

import nl.kadaster.pdok.boot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.security.authentication.BadCredentialsException



@Component
class PasswordFlowProvider(
        @Autowired val userRepository: UserRepository,
        @Autowired val passwordEncoder: PasswordEncoder
) : AbstractUserDetailsAuthenticationProvider()
{

    override fun retrieveUser(username: String, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken): UserDetails {
        return userRepository.findByUsername(username) ?: throw UsernameNotFoundException(String.format("User %s does not exist!", username))

    }

    override fun additionalAuthenticationChecks(userDetails: UserDetails, usernamePasswordAuthenticationToken: UsernamePasswordAuthenticationToken) {
        val encodedPassword = userDetails.password
        val rawPassword = usernamePasswordAuthenticationToken.credentials as String
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw BadCredentialsException("Password does not match!")
        }
    }
}