fun day03part1(testInput: List<String>) = testInput
    .sumOf { Rucksack.create(it).repeatableItem().convertToScore() }

fun day03part2(testInput: List<String>) = testInput
    .map { Rucksack.create(it) }
    .windowed(3, 3)
    .map { it.repeatableItem() }
    .sumOf { it.convertToScore() }

private fun List<Rucksack>.repeatableItem(): Char {
    val first = this[0]
    val second = this[1]
    val third = this[2]
    return first.items.first { second.items.contains(it) && third.items.contains(it) }
}

private data class Rucksack(
    val items: String,
    val leftCompartment: String,
    val rightCompartment: String,
) {

    fun repeatableItem(): Char {
        return leftCompartment.first { rightCompartment.contains(it) }
    }

    companion object {
        fun create(contents: String): Rucksack {
            return Rucksack(
                contents,
                contents.slice(0 until contents.length / 2),
                contents.slice(contents.length / 2 until contents.length)
            )
        }
    }
}

private fun Char.convertToScore(): Int {
    return if (isUpperCase()) code - 38
    else code - 96
}
