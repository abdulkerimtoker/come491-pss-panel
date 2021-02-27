package toker.pss.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import toker.pss.repo.PictureRepository
import java.io.File

@RestController
class PictureController(
    val pictureRepository: PictureRepository
) {
    @Value("\${pictures-path}")
    lateinit var picturesPath: String

    @GetMapping("/api/pictures/{id}", produces = [MediaType.IMAGE_JPEG_VALUE])
    fun picture(@PathVariable id: Int): Resource {
        return FileSystemResource(File(picturesPath, id.toString()))
    }

    @DeleteMapping("/api/pictures/{pictureId}")
    fun deletePicture(@PathVariable("pictureId") pictureId: Int) = pictureRepository.deleteById(pictureId)
}