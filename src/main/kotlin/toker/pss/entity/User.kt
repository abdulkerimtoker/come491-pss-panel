package toker.pss.entity

import javax.persistence.*

@Entity
@Table(name = "panel_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int?,

    @Column(nullable = false, unique = true)
    var name: String,

    @ManyToOne
    @JoinColumn(nullable = false)
    var rank: Rank
)