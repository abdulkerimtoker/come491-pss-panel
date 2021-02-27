package toker.pss.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.FileSystemResource
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity

import org.springframework.web.client.RestTemplate

import org.springframework.http.HttpEntity

import org.springframework.util.MultiValueMap

import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import toker.pss.entity.*
import toker.pss.entity.pk.ModuleAccessPK
import toker.pss.repo.ModuleAccessRepository
import toker.pss.repo.ModuleRepository
import toker.pss.repo.SignalStepRepository
import java.io.File


@RestController
class PredictionController(
    val moduleAccessRepository: ModuleAccessRepository,
    val signalStepRepository: SignalStepRepository
) {

    @Value("\${pictures-path}")
    lateinit var picturesPath: String

    data class Prediction(
        val name: String,
        val probability: String
    )

    @PostMapping("/api/check/{moduleId}")
    fun check(@PathVariable("moduleId") module: Module, @RequestParam file: MultipartFile): Prediction? {
        val picturesFolder = File(picturesPath)
        if (!picturesFolder.exists())
            picturesFolder.mkdir()
        file.transferTo(File(picturesFolder, "x"))

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("picture", FileSystemResource(File(picturesFolder, "x")))
        val requestEntity = HttpEntity(body)
        val serverUrl = "http://localhost:8000/predict"
        val restTemplate = RestTemplate()
        val response = restTemplate.postForEntity(serverUrl, requestEntity, Prediction::class.java)
        val result = response.body

        if (result !== null) {
            return result
        }

        return null
    }
}