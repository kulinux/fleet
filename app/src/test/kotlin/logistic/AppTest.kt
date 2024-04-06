package logistic

import io.kotest.common.runBlocking
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.launch

class AppTest : ShouldSpec({
    lateinit var fleet: Fleet
    lateinit var goods: Goods
    lateinit var trucks: Trucks

    beforeAny {
        fleet = Fleet.apply(mapOf(Size.L to 1, Size.M to 1))
        val waiter = Waiter()
        val randomSize = RandomSize()
        goods = Goods(waiter, randomSize)
        trucks = Trucks(fleet, waiter)
    }

    should("consume 1 items") {
        repeat(10) {
            val good = goods.next()
            val truck: Truck = trucks.consume(good)

            truck.size shouldBe good.size
        }
    }

    should("consume 1 items several threads") {
        runBlocking {
            repeat(10) {
                launch {
                    val good = goods.next()
                    val truck: Truck = trucks.consume(good)

                    truck.size shouldBe good.size
                }
            }
        }
    }

})
