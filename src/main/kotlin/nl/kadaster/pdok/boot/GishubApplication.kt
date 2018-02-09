package nl.kadaster.pdok.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class GishubApplication

fun main(args: Array<String>) {
    SpringApplication.run(GishubApplication::class.java, *args)
}


