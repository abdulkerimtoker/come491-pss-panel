package toker.pss.entity

import javax.persistence.*

@Entity
class Signal(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int?,

    @Column(nullable = false)
    var port: Int,

    @Column(nullable = false)
    var initialDelay: Int,

    @Column(nullable = false)
    var repetance: Int
)