package com.mukeshsolanki.rules.compose

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression

class DpInComposable : Rule() {
    override val issue = Issue(
        id = "DpInComposable",
        severity = Severity.Defect,
        description = "Composables contains hardcoded dp values.",
        Debt.FIVE_MINS
    )

    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        val selector = expression.selectorExpression ?: return
        if (selector.text == "dp") {
            report(
                CodeSmell(
                    issue,
                    Entity.from(expression),
                    "Use of `dp` in Compose functions is discouraged, consider creating a constant"
                )
            )
        }
    }
}
