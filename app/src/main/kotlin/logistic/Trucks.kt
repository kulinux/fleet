package logistic

class Trucks(val fleet: Fleet, val waiter: Waiter) {
    suspend fun consume(good: Good): Truck {
        if(!fleet.start(good.size)) {
            throw RuntimeException("no ${good.size} available")
        }
        waiter.waitRandom()
        val truck =  Truck(good.size)
        fleet.end(good.size)
        return truck
    }

}
