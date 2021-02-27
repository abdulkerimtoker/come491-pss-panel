package toker.pss.entity

import toker.pss.entity.pk.PermissionAssignmentPK
import javax.persistence.*

@Entity
@IdClass(PermissionAssignmentPK::class)
class PermissionAssignment(
    @Id
    @ManyToOne
    var user: User,

    @Id
    @ManyToOne
    var permission: Permission
)