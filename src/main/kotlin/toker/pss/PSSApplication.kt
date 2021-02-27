package toker.pss

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class PSSApplication

fun main(args: Array<String>) {
    runApplication<PSSApplication>(*args) {

    }
}