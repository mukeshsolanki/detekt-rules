package com.mukeshsolanki.provider

import com.mukeshsolanki.rules.compose.DpInComposable
import com.mukeshsolanki.rules.compose.MissingDefaultModifier
import com.mukeshsolanki.rules.compose.NoMutableStateParameter
import com.mukeshsolanki.rules.compose.RememberMutableState
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class ComposeProvider : RuleSetProvider {

    override val ruleSetId: String = "compose"

    override fun instance(config: Config): RuleSet = RuleSet(
        ruleSetId,
        listOf(
            DpInComposable(),
            MissingDefaultModifier(),
            NoMutableStateParameter(),
            RememberMutableState()
        )
    )
}
