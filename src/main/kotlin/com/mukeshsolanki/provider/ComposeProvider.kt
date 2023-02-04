package com.mukeshsolanki.provider

import com.mukeshsolanki.rules.DpInComposable
import com.mukeshsolanki.rules.LargeClass
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class ComposeProvider : RuleSetProvider {

    override val ruleSetId: String = "compose"

    override fun instance(config: Config): RuleSet = RuleSet(
        ruleSetId,
        listOf(
            DpInComposable(config),
            LargeClass(config),
        )
    )
}
