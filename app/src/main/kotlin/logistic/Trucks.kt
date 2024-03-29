package logistic

class Trucks(val fleet: Fleet, val waiter: Waiter) {
    suspend fun consume(good: Good): Truck {
        fleet.start(good.size)
        waiter.waitRandom()
        val truck =  Truck(good.size)
        fleet.end(good.size)
        return truck
    }

}
