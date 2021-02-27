package toker.pss.authentication

import org.springframework.security.core.GrantedAuthority

class BareGrantedAuthority(private val authority: String) : GrantedAuthority {

    override fun getAuthority(): String {
        return authority
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as BareGrantedAuthority
        return authority == that.authority
    }
}