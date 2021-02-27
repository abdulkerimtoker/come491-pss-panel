package toker.pss.entity

import javax.persistence.*

@Entity
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column
    var description: String
)