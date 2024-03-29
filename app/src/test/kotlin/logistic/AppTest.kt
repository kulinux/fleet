package logistic

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class AppTest : ShouldSpec({
    should("consume 1 items") {
        val fleet = Fleet(mapOf(Size.L to 3, Size.M to 3))
        val waiter = mockk<Waiter>(relaxed = true)
        val randomSize = mockk<RandomSize>(relaxed = true)
        val goods = Goods(waiter, randomSize)
        val trucks = Trucks(fleet, waiter)
        val good = goods.next()
        val truck: Truck = trucks.consume(good)

        truck.size shouldBe good.size
    }

})
