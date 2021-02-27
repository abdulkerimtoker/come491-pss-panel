package toker.pss.entity.pk

import toker.pss.annotation.PKClass
import java.io.Serializable

@PKClass
data class PermissionAssignmentPK(
    var user: Int?,
    var permission: Int?
) : Serializable