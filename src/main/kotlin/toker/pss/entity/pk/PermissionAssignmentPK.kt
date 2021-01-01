package toker.pss.entity.pk

import java.io.Serializable

class PermissionAssignmentPK(
    var user: Int,
    var permission: Int
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PermissionAssignmentPK

        if (user != other.user) return false
        if (permission != other.permission) return false

        return true
    }

    override fun hashCode(): Int {
        var result = user
        result = 31 * result + permission
        return result
    }
}