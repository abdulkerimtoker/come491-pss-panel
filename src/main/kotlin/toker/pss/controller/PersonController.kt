package toker.pss.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import toker.pss.entity.*
import toker.pss.entity.pk.ModuleAccessPK
import toker.pss.repo.ModuleAccessRepository
import toker.pss.repo.ModuleRepository
import toker.pss.repo.PersonRepository
import toker.pss.repo.PictureRepository
import java.io.File

@RestController
@Secured("ROLE_PERSON_MANAGER")
class PersonController(
    val personRepository: PersonRepository,
    val pictureRepository: PictureRepository,
    val moduleRepository: ModuleRepository,
    val moduleAccessRepository: ModuleAccessRepository
) {

    @GetMapping("/api/people/{personId}")
    fun person(@PathVariable("personId") person: Person) = person

    @PostMapping("/api/people")
    fun createPerson(@RequestBody person: Person): Person {
        person.id = null
        return personRepository.saveAndFlush(person)
    }

    @PutMapping("/api/people/{personId}")
    fun updatePerson(@PathVariable personId: Int, @RequestBody newState: Person): Person {
        val person = personRepository.findById(personId).get()
        person.name = newState.name
        person.description = newState.description
        return personRepository.saveAndFlush(person)
    }

    @GetMapping("/api/people")
    fun people(): Collection<Person> = personRepository.findAll()

    @GetMapping("/api/people/{personId}/pictures")
    fun pictures(@PathVariable personId: Int): Collection<Picture> = pictureRepository.findAll {
        root, _, builder -> builder.equal(root.get(Picture_.person).get(Person_.id), personId)
    }

    @Value("\${pictures-path}")
    lateinit var picturesPath: String

    @PostMapping("/api/people/{personId}/pictures")
    fun assignPicture(@PathVariable personId: Int,
                      @RequestParam file: MultipartFile): Picture {
        val picture = pictureRepository.saveAndFlush(Picture(person = personRepository.getOne(personId)))
        val picturesFolder = File(picturesPath)
        if (!picturesFolder.exists())
            picturesFolder.mkdir()
        file.transferTo(File(picturesFolder, picture.id.toString()))
        return picture
    }

    @PostMapping("/api/people/{personId}/modules/{moduleId}")
    fun grantAccessToModule(@PathVariable personId: Int, @PathVariable moduleId: Int): ModuleAccess {
        return moduleAccessRepository.saveAndFlush(
            ModuleAccess(module = moduleRepository.getOne(moduleId), person = personRepository.getOne(personId))
        )
    }

    @DeleteMapping("/api/people/{personId}/modules/{moduleId}")
    fun revokeAccessToModule(@PathVariable personId: Int, @PathVariable moduleId: Int) {
        moduleAccessRepository.deleteById(ModuleAccessPK(moduleId, personId))
    }

    @GetMapping("/api/people/{personId}/modules")
    fun moduleAccessList(@PathVariable personId: Int): Collection<ModuleAccess> = moduleAccessRepository.findAll {
        root, _, builder ->
        builder.equal(root.get(ModuleAccess_.person).get(Person_.id), personId)
    }
}