package br.com.sknc.beagle.bff.domain.models

import java.util.*

sealed class BalanceDataConfig(open val endpoint: String, val identifier: String) {
    data class Success(val balance: Double, override val endpoint: String) :
        BalanceDataConfig(endpoint, UUID.randomUUID().toString().substring(0,3))

    data class Error(override val endpoint: String) :
        BalanceDataConfig(endpoint, UUID.randomUUID().toString().substring(0,3))
}



