package toker.pss.entity

import javax.persistence.*

@Entity
class Signal(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var description: String
)