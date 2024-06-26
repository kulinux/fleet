package logistic

import io.kotest.core.spec.style.ShouldSpec
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.assertThrows

class TrucksTest : ShouldSpec({
    val size = Size.L
    val waiter = mockk<Waiter>(relaxed = true)
    val fleet = mockk<Fleet>(relaxed = true)
    val trucks = Trucks(fleet, waiter)
    val good = Good(size)

    should("call waiter on trucks consume") {
        trucks.consume(good)
        coVerifyOrder {
            fleet.start(size)
            waiter.waitRandom()
            fleet.end(size)
        }
    }


})
