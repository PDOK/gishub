package nl.kadaster.pdok.boot.controller

import io.swagger.annotations.ApiOperation
import nl.kadaster.pdok.boot.model.User
import nl.kadaster.pdok.boot.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = RouteConstants.MOUNT + "/client")
class TestUserController(
        @Autowired private val userRepository: UserRepository,
        @Autowired private val passwordEncoder : PasswordEncoder
) {


    @RequestMapping(value = "register", method = arrayOf(RequestMethod.POST))
    @ApiOperation(value = "Create a user with name", notes = "", response = String::class)
    fun create(): String {

        val user = User(
                actief = true,
                enabled = true,
                expired = false,
                locked = false,
                password = passwordEncoder.encode("wachtwoord"),
                username = "user1"
        )
        userRepository.save(user)

        return "username: ${user.username}"
    }
}