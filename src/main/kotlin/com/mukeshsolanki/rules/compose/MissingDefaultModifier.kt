package com.mukeshsolanki.rules.compose

import io.gitlab.arturbosch.detekt.api.*
import io.gitlab.arturbosch.detekt.rules.hasAnnotation
import org.jetbrains.kotlin.psi.KtNamedFunction

class MissingDefaultModifier : Rule() {
    override val issue = Issue(
        id = "MissingDefaultModifier",
        severity = Severity.Warning,
        description = "Composable functions should have the 'modifier' parameter with a default value",
        debt = Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)
        if (!function.hasAnnotation("androidx.compose.runtime.Composable")) {
            return
        }
        val modifierParameter = function.valueParameters.find { it.name == "modifier" }
        if (modifierParameter == null) {
            report(
                CodeSmell(
                    issue,
                    Entity.from(function),
                    "Function with @Composable annotation should have a 'modifier' parameter"
                )
            )
            return
        }

        if (modifierParameter.defaultValue == null) {
            report(
                CodeSmell(
                    issue,
                    Entity.from(modifierParameter),
                    "The 'modifier' parameter should have a default value"
                )
            )
        }
    }
}
