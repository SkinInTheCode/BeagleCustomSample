package br.com.sknc.beagle.bff.data

import br.com.sknc.beagle.bff.data.models.BannerItem
import br.com.sknc.beagle.bff.data.models.MenuItem
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class MockDatabase {

    fun getBalance(): Double = 5000.00

    fun getCreditCardBalance(): Double = 7056.85

    fun getBanners() = listOf(
        BannerItem(
            "Você tem R$ 5.000,00",
            "disponíveis para ",
            "empréstimo."

        ),
        BannerItem(
            "Tem SHOPPING no seu bank.",
            "",
            "Começe agora."

        ),
        BannerItem(
            "Conheça a conta Pj: prática e",
            "livre de burocracia para o seu ",
            "negócio."

        )
    )

    fun getMenu() = listOf(
        MenuItem(
            title = "Pix",
            icon = "\ue87b",
            deeplink = ""
        ),
        MenuItem(
            title = "Pagar",
            icon = "\ue866",
            deeplink = ""
        ),
        MenuItem(
            title = "Transferir",
            icon = "\ue886",
            deeplink = ""
        ),
        MenuItem(
            title = "Depositar",
            icon = "\ue867",
            deeplink = ""
        ),
        MenuItem(
            title = "Receber",
            icon = "\ue865",
            deeplink = ""
        )
    )
}