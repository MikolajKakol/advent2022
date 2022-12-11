import kotlin.math.absoluteValue
import kotlin.math.sign

fun day09part1(testInput: List<String>) = with(Rope()) {
    testInput.forEach { move(it) }
    visitedPlaces.size
}


fun day09part2(testInput: List<String>) = with(Rope(10)) {
    testInput.forEach { move(it) }
    visitedPlaces.size
}


private class Rope(chunks: Int = 2) {
    private var chunks: MutableList<Point> = (0 until chunks).map { Point(1000, 1000) }.toMutableList()

    val visitedPlaces = mutableSetOf(this.chunks.last())

    fun move(cmd: String) {
        val (direction, amount) = cmd.split(" ")
            .let { (direction, amount) -> Direction.parse(direction) to amount.toInt() }

        repeat(amount) {
            chunks[0] = chunks[0].move(direction)
            chunks
                .drop(1)
                .forEachIndexed { index, point ->
                    chunks[index + 1] = point.followHead(chunks[index])
                }
            visitedPlaces.add(chunks.last())
        }
    }

    private fun Point.followHead(headPosition: Point): Point {
        val tailPosition = this
        return if (!tailPosition.touches(headPosition))
            tailPosition.moveTowards(headPosition)
        else tailPosition
    }
}

private enum class Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    ;

    companion object {
        fun parse(direction: String): Direction {
            return Direction.values().first { it.name[0] == direction[0] }
        }
    }
}

private data class Point(val x: Int, val y: Int){

    fun move(direction: Direction) = when (direction) {
        Direction.UP -> Point(x, y + 1)
        Direction.DOWN -> Point(x, y - 1)
        Direction.LEFT -> Point(x - 1, y)
        Direction.RIGHT -> Point(x + 1, y)
    }

    fun moveTowards(other: Point): Point =
        Point(
            (other.x - x).sign + x,
            (other.y - y).sign + y
        )

    fun touches(other: Point): Boolean =
        (x - other.x).absoluteValue <= 1 && (y - other.y).absoluteValue <= 1
}
