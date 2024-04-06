package logistic

import kotlinx.coroutines.channels.Channel

class Fleet(private val initial: Map<Size, Int>) {
    private lateinit var channel: Map<Size, Channel<Size>>

    suspend fun init() {
        channel = Size.entries.associateWith { Channel<Size>(capacity = Channel.UNLIMITED) }
        println("start init")
        repeat(initial.getOrDefault(Size.L, 0)) { channel[Size.L]?.send(Size.L) }
        repeat(initial.getOrDefault(Size.M, 0)) { channel[Size.M]?.send(Size.M) }
        println("end init")
    }

    suspend fun start(size: Size) {
        println("${Thread.currentThread().name} waiting for $size")
        val trySize = channel[size]?.receive()
        println("${Thread.currentThread().name} waiting for $size ends $trySize")
    }

    suspend fun end(size: Size) {
        channel[size]?.send(size)
    }
}
