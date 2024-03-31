package logistic

class Fleet(initial: Map<Size, Int>) {
    private var fleet = initial.toMap()

    fun start(size: Size): Boolean {
        val res = fleet.getOrDefault(size, 0) > 0
        fleet = fleet.map { (key, value) ->
            when (key) {
                size -> key to value - 1
                else -> key to value
            }
        }.toMap()
        return res
    }

    fun end(size: Size) {
        fleet = fleet.map { (key, value) ->
            when (key) {
                size -> key to value + 1
                else -> key to value
            }
        }.toMap()
    }
}
