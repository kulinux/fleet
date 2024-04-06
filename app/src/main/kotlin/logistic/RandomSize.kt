package logistic

class RandomSize {
    fun size(): Size = Size.entries.toTypedArray().random()
}
