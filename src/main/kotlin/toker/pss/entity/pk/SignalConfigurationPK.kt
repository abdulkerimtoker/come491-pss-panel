package toker.pss.entity.pk

import java.io.Serializable

class SignalConfigurationPK(
    var module: Int,
    var signal: Int
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SignalConfigurationPK

        if (module != other.module) return false
        if (signal != other.signal) return false

        return true
    }

    override fun hashCode(): Int {
        var result = module
        result = 31 * result + signal
        return result
    }
}