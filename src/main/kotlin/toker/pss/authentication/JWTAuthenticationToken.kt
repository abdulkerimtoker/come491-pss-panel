package toker.pss.authentication

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.authentication.AbstractAuthenticationToken

class JWTAuthenticationToken(
    permissions: Collection<GrantedAuthority>,
    val username: String,
    val jwt: String,
    val sessionId: Int
) : AbstractAuthenticationToken(permissions) {
    override fun getCredentials(): Any {
        return jwt
    }

    override fun getPrincipal(): Any {
        return username
    }

    override fun getDetails(): Any {
        return username
    }

    init {
        isAuthenticated = true
    }
}