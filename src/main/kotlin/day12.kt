import java.util.LinkedList

fun day12part1(testInput: List<String>) = testInput
    .map { it.chars().toArray().toTypedArray() }
    .toTypedArray()
    .let { matrix ->
        val start = matrix.findStaringPoint()
        val end = matrix.findEndPoint()
        matrix.setValueAt(start, 'a'.code)
        matrix.setValueAt(end, 'z'.code)
        Engine(matrix, start, end).findShortestPathLength()
    }

fun day12part2(testInput: List<String>) = testInput
    .map { it.chars().toArray().toTypedArray() }
    .toTypedArray()
    .let { matrix ->
        val start = matrix.findStaringPoint()
        val end = matrix.findEndPoint()
        matrix.setValueAt(start, 'a'.code)
        matrix.setValueAt(end, 'z'.code)
        matrix.findAll { it == 'a'.code }
            .mapNotNull { Engine(matrix, it, end).findShortestPathLength() }
            .min()
    }

class Engine(private val matrix: Matrix, private val start: Point, private val end: Point) {

    fun findShortestPathLength(): Int? {
        val queue = LinkedList<Path>().apply { add(Path(listOf(start))) }
        val seen = mutableSetOf<Point>()
        var foundPath: Path? = null

        while (foundPath == null && queue.isNotEmpty()) {
            val nextPoint = queue.poll()
            val paths = nextPoint.step()
                .filterNot { seen.contains(it.point) }
                .also { seen.addAll(it.map { it.point }) }
            queue.addAll(paths)

            foundPath = paths.find { it.path.last() == end }
        }
        return if (foundPath != null) {
            foundPath.path.size - 1
        } else null
    }

    inner class Path(val path: List<Point>) {

        val point = path.last()

        fun step(): List<Path> {
            val currentPosition = path.last()
            val level = matrix.valueAt(currentPosition)

            return Direction.values()
                .mapNotNull {
                    val newPosition = currentPosition.move(it)
                    val neighbourLevel = matrix.valueAtOrNull(newPosition)
                    neighbourLevel?.let { if (neighbourLevel - level <= 1) newPosition else null }
                }
                .mapNotNull {
                    if (path.contains(it))
                        null
                    else
                        Path(path + it)
                }
        }
    }
}

private fun Matrix.findStaringPoint() =
    find { it == 'S'.code }

private fun Matrix.findEndPoint() =
    find { it == 'E'.code }

private fun Matrix.valueAt(point: Point) = this[point.y][point.x]
private fun Matrix.valueAtOrNull(point: Point) = this.getOrNull(point.y)?.getOrNull(point.x)

private fun Matrix.setValueAt(point: Point, value: Int) {
    this[point.y][point.x] = value
}

typealias Matrix = Array<Array<Int>>

private fun Matrix.find(matcher: (value: Int) -> Boolean): Point {
    for ((y, row) in this.withIndex()) {
        for ((x, item) in row.withIndex()) {
            if (matcher(item)) return Point(x, y)
        }
    }

    throw NoSuchElementException()
}

private fun Matrix.findAll(matcher: (value: Int) -> Boolean): List<Point> {
    val points = mutableListOf<Point>()
    for ((y, row) in this.withIndex()) {
        for ((x, item) in row.withIndex()) {
            if (matcher(item)) points.add(Point(x, y))
        }
    }
    return points
}
