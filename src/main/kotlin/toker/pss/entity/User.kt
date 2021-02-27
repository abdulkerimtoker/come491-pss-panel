package toker.pss.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "panel_user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Int? = null,

    @Column(nullable = false, unique = true)
    var name: String,

    @Column(nullable = false, length = 1024)
    @JsonIgnore
    var password: String,

    @ManyToOne
    @JoinColumn(nullable = false)
    var rank: Rank
) {
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    var permissionAssignments: MutableSet<PermissionAssignment>? = null
}