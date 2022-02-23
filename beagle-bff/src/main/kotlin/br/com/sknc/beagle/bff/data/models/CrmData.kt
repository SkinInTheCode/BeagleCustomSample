package br.com.sknc.beagle.bff.data.models

import br.com.zup.beagle.widget.action.Action

data class CrmData(val urlBackground: String, val route: String, val title: String? = null, val subTitle: String? = null, val listAction: List<Action>)