package nl.kadaster.pdok.boot.model

import java.io.Serializable
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractHibernateObject(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) private val id: Long = 0L
) : Serializable
