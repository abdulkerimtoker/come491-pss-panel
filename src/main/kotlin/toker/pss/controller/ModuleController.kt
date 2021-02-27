package toker.pss.controller

import org.springframework.web.bind.annotation.*
import toker.pss.entity.Module
import toker.pss.repo.ModuleRepository
import toker.pss.repo.SignalRepository

@RestController
class ModuleController(
    var moduleRepository: ModuleRepository,
    var signalRepository: SignalRepository
) {
    @GetMapping("/api/modules/{id}")
    fun module(@PathVariable("id") module: Module) = module

    @PutMapping("/api/modules/{moduleId}")
    fun updateModule(@PathVariable moduleId: Int, @RequestBody newState: Module): Module {
        val module = moduleRepository.findById(moduleId).get()
        module.name = newState.name
        module.description = newState.description
        module.signal = signalRepository.getOne(newState.signal.id!!)
        return moduleRepository.saveAndFlush(module)
    }

    @PostMapping("/api/modules")
    fun createModule(@RequestBody module: Module): Module {
        module.id = null
        module.signal = signalRepository.getOne(module.signal.id!!)
        return moduleRepository.saveAndFlush(module)
    }

    @GetMapping("/api/modules")
    fun modules(): Collection<Module> = moduleRepository.findAll()
}