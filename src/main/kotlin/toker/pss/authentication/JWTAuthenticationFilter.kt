package toker.pss.authentication

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import kotlin.Throws
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.FilterChain
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.stream.Collectors
import org.springframework.security.core.context.SecurityContextHolder
import java.lang.Exception

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager?) :
    BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        var jwt = request.getHeader("Authorization")
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.replace("Bearer ", "")
            try {
                val decodedJWT = JWT.require(Algorithm.HMAC512("123".toByteArray()))
                    .build()
                    .verify(jwt)
                val sessionId = decodedJWT.getClaim("Session-ID").asInt()
                val username = decodedJWT.subject
                val permissionsList = decodedJWT.getClaim("Permissions")
                    .asList(String::class.java)
                    .stream()
                    .map { authority: String -> BareGrantedAuthority(authority) }
                    .collect(Collectors.toList())
                SecurityContextHolder.getContext().authentication =
                    JWTAuthenticationToken(permissionsList, username, jwt, 0)
            } catch (ignored: Exception) { }
        }
        chain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return !request.servletPath.startsWith("/api/") || request.servletPath == "/api/login"
    }
}