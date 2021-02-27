package toker.pss.controller

import org.springframework.web.bind.annotation.*
import toker.pss.entity.*
import toker.pss.repo.SignalRepository
import toker.pss.repo.SignalStepRepository

@RestController
class SignalController(
    val signalRepository: SignalRepository,
    val signalStepRepository: SignalStepRepository
) {
    @GetMapping("/api/signals/{signalId}")
    fun signal(@PathVariable("signalId") signal: Signal) = signal

    @PostMapping("/api/signals")
    fun createSignal(@RequestBody signal: Signal): Signal {
        signal.id = null
        return signalRepository.saveAndFlush(signal)
    }

    @PutMapping("/api/signals/{signalId}")
    fun updateSignal(@PathVariable signalId: Int, @RequestBody newState: Signal): Signal {
        val signal = signalRepository.findById(signalId).get()
        signal.name = newState.name
        signal.description = newState.description
        return signalRepository.saveAndFlush(signal)
    }

    @GetMapping("/api/signals/{signalId}/steps")
    fun steps(@PathVariable signalId: Int): Collection<SignalStep> = signalStepRepository.findAll {
        root, _, builder ->
        builder.equal(root.get(SignalStep_.signal).get(Signal_.id), signalId)
    }

    @GetMapping("/api/signals")
    fun signals(): Collection<Signal> = signalRepository.findAll()

    @PostMapping("/api/signals/{signalId}/steps")
    fun createSignalStep(@PathVariable signalId: Int, @RequestBody signalStep: SignalStep): SignalStep {
        signalStep.id = null
        signalStep.signal = signalRepository.getOne(signalId)
        return signalStepRepository.saveAndFlush(signalStep)
    }

    @PutMapping("/api/signals/steps/{signalStepId}")
    fun updateSignalStep(@PathVariable signalStepId: Int, @RequestBody newState: SignalStep): SignalStep {
        val signalStep = signalStepRepository.findById(signalStepId).get()
        signalStep.bits = newState.bits
        signalStep.step = newState.step
        return signalStepRepository.saveAndFlush(signalStep)
    }

    @DeleteMapping("/api/signals/steps/{signalStepId}")
    fun deleteSignalStep(@PathVariable signalStepId: Int) {
        signalStepRepository.deleteById(signalStepId)
    }
}