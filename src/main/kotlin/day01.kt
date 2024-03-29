fun day01part1(testInput: List<String>) =
    createElfs(testInput)
        .maxBy { it.total }
        .total

fun day01part2(testInput: List<String>) =
    createElfs(testInput)
        .sortedByDescending { it.total }
        .take(3)
        .sumOf { it.total }

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
