package logistic

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.coVerifyOrder
import io.mockk.mockk

class TucksTest : ShouldSpec({

    should("call waiter on trucks consume") {
        val size = Size.L
        val waiter = mockk<Waiter>(relaxed = true)
        val fleet = mockk<Fleet>(relaxed = true)
        val trucks = Trucks(fleet, waiter)
        val good = Good(size)
        trucks.consume(good)
        coVerifyOrder {
            fleet.start(size)
            waiter.waitRandom()
            fleet.end(size)
        }
    }
})
