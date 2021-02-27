package toker.pss.entity.pk

import toker.pss.annotation.PKClass
import java.io.Serializable

@PKClass
data class ModuleAccessPK(
    var module: Int?,
    var person: Int?
) : Serializable