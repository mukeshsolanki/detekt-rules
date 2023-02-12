package com.mukeshsolanki.rules.kotlin

import io.gitlab.arturbosch.detekt.api.*
import io.gitlab.arturbosch.detekt.rules.hasAnnotation
import io.gitlab.arturbosch.detekt.rules.isOverride
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtNamedFunction

class LargeClass(config: Config) : Rule(config) {
    override val issue = Issue(
        id = "LargeClass",
        severity = Severity.Warning,
        description = "Classes with more than 10 functions should be split into smaller classes.",
        Debt.FIVE_MINS
    )

    override fun visitClass(klass: KtClass) {
        val functions = klass.declarations
            .filterIsInstance<KtNamedFunction>()
            .filter { !it.hasAnnotation("CustomAnnotation") }
            .filter { !it.isOverride() }
        if (functions.size > 10) {
            report(CodeSmell(issue, Entity.from(klass), "Large class"))
        }
    }
}
