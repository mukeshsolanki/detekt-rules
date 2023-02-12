package com.mukeshsolanki.provider


import com.mukeshsolanki.rules.kotlin.LargeClass
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class KotlinProvider : RuleSetProvider {

    override val ruleSetId: String = "kotlin"

    override fun instance(config: Config): RuleSet = RuleSet(
        ruleSetId,
        listOf(
            LargeClass(config),
        )
    )
}
