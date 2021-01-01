package toker.pss.entity

import toker.pss.entity.pk.SignalConfigurationPK
import javax.persistence.*

@Entity
@IdClass(SignalConfigurationPK::class)
class SignalConfiguration(
    @Id
    @ManyToOne
    var module: Module,

    @Id
    @ManyToOne
    var signal: Signal,

    @Column(nullable = false)
    var isEnabled: Boolean
)