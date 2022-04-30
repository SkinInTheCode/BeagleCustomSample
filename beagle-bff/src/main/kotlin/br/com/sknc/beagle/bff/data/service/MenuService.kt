package br.com.sknc.beagle.bff.data.service


import br.com.sknc.beagle.bff.data.repository.MenuRepository
import br.com.sknc.beagle.bff.domain.models.BalanceDataConfig
import org.springframework.stereotype.Service


@Service
class MenuService(private val menuRepository: MenuRepository) {

    fun getMenuData() {

        return try {
            val menu = menuRepository.fetchMenu()
        } catch (e: java.lang.Exception) {

        }

    }

}