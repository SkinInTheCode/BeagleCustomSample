package br.com.sknc.beagle.bff.repository

import br.com.sknc.beagle.bff.data.MockDatabase
import org.springframework.stereotype.Repository

@Repository
class BannerRepository(private val database: MockDatabase) {

    fun getBanners() = database.getBanners()
}