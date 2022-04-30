package br.com.sknc.beagle.bff.data.repository

import br.com.sknc.beagle.bff.data.MockDatabase
import br.com.sknc.beagle.bff.domain.models.BalanceDataConfig
import org.springframework.stereotype.Repository

@Repository
class BalanceRepository(private val database: MockDatabase) {

    fun fetchBalance() = database.getBalance()

    fun getCreditCardBalance() = database.getCreditCardBalance()

    fun getBalanceEndpoint() =  "http://10.0.2.2:3000/home/data/balance"

}