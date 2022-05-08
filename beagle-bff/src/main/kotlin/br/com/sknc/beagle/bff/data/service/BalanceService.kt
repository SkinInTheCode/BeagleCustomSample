package br.com.sknc.beagle.bff.data.service

import br.com.sknc.beagle.bff.data.repository.BalanceRepository
import br.com.sknc.beagle.bff.domain.models.BalanceDataConfig
import org.springframework.stereotype.Service

@Service
class BalanceService(private val balanceRepository: BalanceRepository) {

    fun fetchBalance() = balanceRepository.fetchBalance()

    fun getBalanceSectionData(): BalanceDataConfig {

        val endpoint = balanceRepository.getBalanceEndpoint()

        return try {
            val balance = balanceRepository.fetchBalance()
            BalanceDataConfig.Success(
                balance = balance,
                endpoint = endpoint
            )
        } catch (e: java.lang.Exception) {
            BalanceDataConfig.Error(endpoint = endpoint)
        }
    }

}