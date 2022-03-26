package br.com.sknc.beagle.bff.repository

import br.com.sknc.beagle.bff.data.MockDatabase
import org.springframework.stereotype.Repository

@Repository
class MenuRepository(private val database: MockDatabase) {

    fun getMenu() = database.getMenu()

}