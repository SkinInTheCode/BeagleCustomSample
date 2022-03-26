package br.com.sknc.beagle.bff.repository

import br.com.sknc.beagle.bff.data.MockDatabase
import org.springframework.stereotype.Repository

@Repository
class BalanceRepository(private val database: MockDatabase) {

    fun getBalance() = database.getBalance()

    fun getCreditCardBalance() = database.getCreditCardBalance()

}