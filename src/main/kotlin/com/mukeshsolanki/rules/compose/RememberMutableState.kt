package com.mukeshsolanki.rules.compose

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.psi.KtVariableDeclaration

class RememberMutableState : Rule() {
    override val issue = Issue(
        "RememberMutableState",
        Severity.Warning,
        "Mutable state variables in functions with the @composable annotation should be wrapped in `remember {}`.",
        Debt.FIVE_MINS
    )

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)

        val isComposable = function.annotationEntries.any {
            it.text == "@Composable"
        }

        if (!isComposable) {
            return
        }
        val mutableStateVariables = function.bodyExpression?.children?.filterIsInstance<KtVariableDeclaration>()
            ?.filter { it.isVar && it.parent != null && it.parent.text == "remember" }

        mutableStateVariables?.forEach {
            report(
                CodeSmell(
                    issue,
                    Entity.from(it),
                    "Mutable state variable `${it.text}` should be wrapped in `remember {}`."
                )
            )
        }
    }
}
