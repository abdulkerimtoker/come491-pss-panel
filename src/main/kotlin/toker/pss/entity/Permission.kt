package toker.pss.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Permission(
    @Id
    var id: Int?,

    @Column(nullable = false, unique = true)
    var name: String,

    @Column(nullable = true)
    var description: String?
)