package br.com.sknc.beagle.bff.domain.models

import javax.swing.Action

data class Insight(
    val title: String,
    val description: String,
    val buttonTitle: String,
    val actionClick: List<Action>,
    val imageRes: String? = null,
    val imageUlr: String? = null
)