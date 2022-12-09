fun main() {
    val testInput = readInput("Day01_input")

    part1(testInput)
    part2(testInput)
}

private fun part1(testInput: List<String>) {
    val result = createElfs(testInput)
        .maxBy { it.total }
        .total
    println(result)
}

private fun part2(testInput: List<String>) {
    val result = createElfs(testInput)
        .sortedByDescending { it.total }
        .take(3)
        .sumOf { it.total }
    println(result)
}

private fun createElfs(calories: List<String>): List<Elf> {
    var subtotal = 0
    val elfs = mutableListOf<Elf>()
    calories
        .forEach {
            if (it.isBlank()) {
                elfs.add(Elf(subtotal))
                subtotal = 0
            } else {
                subtotal += it.toInt()
            }
        }
    return elfs
}

private class Elf(val total: Int)
