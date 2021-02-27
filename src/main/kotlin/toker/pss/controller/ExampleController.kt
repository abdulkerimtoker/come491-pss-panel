package toker.pss.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController{

    @GetMapping("/api/example")
    @Secured("ROLE_USER")
    fun example() = "example"
}