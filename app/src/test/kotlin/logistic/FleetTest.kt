package logistic

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class FleetTest : ShouldSpec({
    should("return false when empty fleet") {
        val fleet = Fleet(emptyMap())
        fleet.start(Size.L) shouldBe false
    }
    should("return true when one fleet") {
        val fleet = Fleet(mapOf(Size.L to 1))
        fleet.start(Size.L) shouldBe true
    }

    should("return false when one fleet and ask the second time") {
        val fleet = Fleet(mapOf(Size.L to 1))
        fleet.start(Size.L) shouldBe true
        fleet.start(Size.L) shouldBe false
    }

    should("return true when one fleet and return on truck") {
        val fleet = Fleet(mapOf(Size.L to 1))
        fleet.start(Size.L) shouldBe true
        fleet.end(Size.L)
        fleet.start(Size.L) shouldBe true
    }
})