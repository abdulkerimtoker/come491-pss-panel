package toker.pss.controller

import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import toker.pss.authentication.CurrentUsername
import toker.pss.entity.PermissionAssignment
import toker.pss.entity.PermissionAssignment_
import toker.pss.entity.User
import toker.pss.entity.User_
import toker.pss.repo.PermissionAssignmentRepository
import toker.pss.repo.UserRepository

@RestController
class UserController(
    val permissionAssignmentRepository: PermissionAssignmentRepository
) {

    @GetMapping("/api/users/{id}")
    fun user(@PathVariable("id") user: User) = user
    
    @GetMapping("/api/users/current/permissions")
    fun permissions(): MutableList<PermissionAssignment> = permissionAssignmentRepository.findAll {
        root, _, builder ->
        val joinUser = root.join(PermissionAssignment_.user)
        builder.equal(joinUser.get(User_.name), CurrentUsername)
    }
}