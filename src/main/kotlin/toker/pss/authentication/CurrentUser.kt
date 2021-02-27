package toker.pss.authentication

import org.springframework.security.core.context.SecurityContextHolder

val CurrentUsername: String
    get() {
        val token = SecurityContextHolder.getContext().authentication as JWTAuthenticationToken
        return token.username
    }