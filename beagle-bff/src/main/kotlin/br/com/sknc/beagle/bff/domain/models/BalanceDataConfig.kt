package br.com.sknc.beagle.bff.domain.models

sealed class BalanceDataConfig(open val endpoint: String) {
    data class Success(val balance: Double, override val endpoint: String) : BalanceDataConfig(endpoint)
    data class Error(override val endpoint: String) : BalanceDataConfig(endpoint)
}



