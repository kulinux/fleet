package logistic

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class GoodsTest : ShouldSpec({

    should("call waiter on next good") {
        val waiter = mockk<Waiter>(relaxed = true)
        val randomSize = mockk<RandomSize>(relaxed = true)
        val goods = Goods(waiter, randomSize)
        goods.next()
        coVerify { waiter.waitRandom() }
    }

    should("build next Good with random size") {
        val size = Size.L
        val waiter = mockk<Waiter>(relaxed = true)
        val randomSize = mockk<RandomSize>(relaxed = true)
        every { randomSize.size() } returns size
        val goods = Goods(waiter, randomSize)
        val good = goods.next()

        verify { randomSize.size() }
        good.size shouldBe size
    }

})
