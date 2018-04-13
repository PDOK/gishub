package nl.kadaster.pdok.boot.repository

import nl.kadaster.pdok.boot.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>
{
    fun findByUsername(username: String) : User?
}
