package br.com.sknc.beagle.bff.data.repository

import br.com.sknc.beagle.bff.data.MockDatabase
import org.springframework.stereotype.Repository

@Repository
class MenuRepository(private val database: MockDatabase) {

    fun fetchMenu() = database.getMenu()

}