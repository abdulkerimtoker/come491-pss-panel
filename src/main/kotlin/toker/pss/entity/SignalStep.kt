package toker.pss.entity

import javax.persistence.*

@Entity
class SignalStep(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null,

    @Column(nullable = false)
    var step: Int,

    @Column(nullable = false)
    var bits: Int
) {
    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var signal: Signal
}