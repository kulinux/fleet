package logistic


class Trucks(private val fleet: Fleet, private val waiter: Waiter) {

    suspend fun consume(good: Good): Truck {
        println("${Thread.currentThread().name} start ${good.size}")
        fleet.start(good.size)
        println("${Thread.currentThread().name} i have ${good.size}")
        waiter.waitRandom()
        val truck = Truck(good.size)
        fleet.end(good.size)
        println(" ${Thread.currentThread().name} end consume ${good.size}")
        return truck
    }

}
