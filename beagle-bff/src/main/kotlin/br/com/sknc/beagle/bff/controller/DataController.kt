package br.com.sknc.beagle.bff.controller

import br.com.sknc.beagle.bff.data.repository.BalanceRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DataController(private val repository: BalanceRepository) {

    @GetMapping("home/data/balance")
    fun getScreenIti() = repository.fetchBalance()

    @GetMapping("home/data/creditcard")
    fun getBannerScreen() = repository.getCreditCardBalance()

}

