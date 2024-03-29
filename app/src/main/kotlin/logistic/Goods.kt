package logistic

class Goods(private val waiter: Waiter, private val randomSize: RandomSize) {
    suspend fun next(): Good {
        waiter.waitRandom()
        return Good(randomSize.size())
    }

}
