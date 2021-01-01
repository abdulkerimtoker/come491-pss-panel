package toker.pss.entity

import javax.persistence.*

@Entity
class Module(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int?,

    @Column(nullable = false, unique = true)
    var name: String,

    @Column
    var description: String
)