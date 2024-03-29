package logistic

import kotlinx.coroutines.delay

class Waiter {
    suspend fun waitRandom() {
       val time = (0..10).random()
       delay(time.toLong())
    }

}
