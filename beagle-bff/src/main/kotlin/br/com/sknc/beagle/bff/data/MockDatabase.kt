package br.com.sknc.beagle.bff.data

import br.com.sknc.beagle.bff.CustomAction
import br.com.sknc.beagle.bff.data.models.CrmData
import br.com.sknc.beagle.bff.data.models.CustomActionVictor
import br.com.zup.beagle.widget.action.Alert
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Service

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
class MockDatabase {

    fun getCrmData(): List<CrmData> {

        return listOf(
              /*CrmData(urlBackground = "http://10.0.2.2/images/iti_1.jpg", route = "", listAction = listOf(Alert(message = "Card 1 clicado", title = "Ação" ))),
              CrmData(urlBackground = "http://10.0.2.2/images/iti_2.png", route = "", listAction = listOf(Alert(message = "Card 2 clicado", title = "Ação" ))),
              CrmData(urlBackground = "http://10.0.2.2/images/iti_3.png", route = "", listAction = listOf(Alert(message = "Card 3 clicado", title = "Ação" ))),*/
              CrmData(urlBackground = "http://10.0.2.2/images/iti_4.png", route = "", listAction = listOf(/*CustomAction("Testando custom action")*/)),
              CrmData(urlBackground = "http://10.0.2.2/images/iti_5.jpg", route = "",listAction = listOf(CustomActionVictor("Testando custom action 546465 BFF")))
        )

    }
}