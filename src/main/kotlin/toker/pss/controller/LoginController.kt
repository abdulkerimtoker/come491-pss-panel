package toker.pss.controller

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import toker.pss.entity.PermissionAssignment
import toker.pss.entity.User_
import toker.pss.repo.UserRepository
import java.util.*
import java.util.stream.Collectors
import javax.persistence.criteria.JoinType

@RestController
class LoginController(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {
    data class LoginForm(val username: String, val password: String)

    @PostMapping("/api/login")
    fun login(@RequestBody form: LoginForm): ResponseEntity<*> {
        val result = userRepository.findOne { root, query, builder ->
            root.fetch(User_.permissionAssignments, JoinType.LEFT)
            query.distinct(true)
            builder.equal(root.get(User_.name), form.username)
        }

        if (result.isPresent) {
            val user = result.get()
            if (passwordEncoder.matches(form.password, user.password)) {
                val permissions: Collection<PermissionAssignment>? = user.permissionAssignments
                val permissionsList =
                    if (permissions != null)
                        user.permissionAssignments!!
                        .stream()
                        .map { it.permission.name }
                        .collect(Collectors.toList())
                    else LinkedList()
                val jwt = JWT.create()
                    .withSubject(user.name)
                    .withClaim("Permissions", permissionsList)
                    .sign(Algorithm.HMAC512("123".toByteArray()))
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .header(HttpHeaders.AUTHORIZATION, "Bearer $jwt")
                    .build<Any>()
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<Any>()
    }
}