package toker.pss.entity

import toker.pss.entity.pk.ModuleAccessPK
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.ManyToOne

@Entity
@IdClass(ModuleAccessPK::class)
class ModuleAccess(
    @Id
    @ManyToOne
    var module: Module,

    @Id
    @ManyToOne
    var person: Person
)