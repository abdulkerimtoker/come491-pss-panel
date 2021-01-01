package toker.pss.entity.pk

import java.io.Serializable

class ModuleAccessPK(
    var module: Int,
    var person: Int
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ModuleAccessPK

        if (module != other.module) return false
        if (person != other.person) return false

        return true
    }

    override fun hashCode(): Int {
        var result = module
        result = 31 * result + person
        return result
    }
}