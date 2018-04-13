package nl.kadaster.pdok.boot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableAutoConfiguration
class GishubApplication

fun main(args: Array<String>) {
    SpringApplication.run(GishubApplication::class.java, *args)
}


