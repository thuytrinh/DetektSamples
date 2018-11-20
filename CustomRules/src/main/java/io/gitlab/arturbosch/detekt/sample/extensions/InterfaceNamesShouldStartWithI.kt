package io.gitlab.arturbosch.detekt.sample.extensions

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.KtClass

class InterfaceNamesShouldStartWithI : Rule() {
  override val issue = Issue(
    javaClass.simpleName,
    Severity.CodeSmell,
    "Interface names should start with I",
    Debt.FIVE_MINS
  )

  override fun visitClass(klass: KtClass) {
    if (klass.isInterface()) {
      klass.name?.let {
        val shouldBeginWithI = Regex("^I[A-Z]")
        if (!it.contains(shouldBeginWithI)) {
          report(
            CodeSmell(
              issue, Entity.from(klass),
              message = "The interface name $it should have been I$it."
            )
          )
        }
      }
    }
  }
}
