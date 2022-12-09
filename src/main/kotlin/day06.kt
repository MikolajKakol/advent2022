fun day06part1(testInput: String) = findMarker(testInput, 4)

fun day06part2(testInput: String) = findMarker(testInput, 14)

private fun findMarker(testInput: String, signalSize: Int) = testInput
    .asSequence()
    .windowed(signalSize, 1)
    .withIndex()
    .drop(1)
    .first { it.value.toSet().size == signalSize }
    .let { it.index  + signalSize }