package logistic

import kotlinx.coroutines.channels.Channel

class Fleet private constructor(private val initial: Map<Size, Int>) {
    private lateinit var channel: Map<Size, Channel<Size>>

    private suspend fun init() {
        channel = Size.entries.associateWith { Channel(capacity = Channel.UNLIMITED) }

        println("start init")
        Size.entries.forEach { size: Size ->
            repeat(initial.getOrDefault(size, 0)) { channel[size]?.send(size) }
        }
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

    companion object {
        suspend fun apply(initial: Map<Size, Int>): Fleet = Fleet(initial).also { it.init() }
    }
}