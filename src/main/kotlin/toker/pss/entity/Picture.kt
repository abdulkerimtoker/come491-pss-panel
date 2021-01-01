package toker.pss.entity

import javax.persistence.*

@Entity
class Picture(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int?,

    @ManyToOne
    var person: Person
)