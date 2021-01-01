package toker.pss.entity

import toker.pss.entity.pk.PermissionAssignmentPK
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.ManyToOne

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