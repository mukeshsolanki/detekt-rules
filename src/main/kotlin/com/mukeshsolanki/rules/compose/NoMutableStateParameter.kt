package com.mukeshsolanki.rules.compose

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtNamedFunction

class NoMutableStateParameter : Rule() {
    override val issue = Issue(
        id = "NoMutableStateParameter",
        severity = Severity.Defect,
        description = "Functions with @Composable annotation should not have MutableState parameters",
        debt = Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)

        val hasComposableAnnotation = function.annotationEntries.any { it.shortName?.asString() == "Composable" }

        if (hasComposableAnnotation) {
            val hasMutableStateParameter = function.valueParameters.any { it.typeReference?.text == "MutableState" }

            if (hasMutableStateParameter) {
                report(
                    CodeSmell(
                        issue, Entity.from(function), issue.description
                    )
                )
            }
        }
    }
}
