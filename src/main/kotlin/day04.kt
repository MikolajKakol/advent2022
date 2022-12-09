fun day04part1(testInput: List<String>) = testInput.count {
    val (shift1, shift2) = shifts(it)
    shift2.contains(shift1) || shift1.contains(shift2)
}

fun day04part2(testInput: List<String>) = testInput.count {
    val (shift1, shift2) = shifts(it)
    shift2.overlaps(shift1) || shift1.overlaps(shift2)
}

private fun shifts(it: String): Pair<IntRange, IntRange> {
    val split = it.split(",")
    val elf1 = split[0].split("-")
    val elf2 = split[1].split("-")
    val shift1 = elf1[0].toInt()..elf1[1].toInt()
    val shift2 = elf2[0].toInt()..elf2[1].toInt()
    shift1 to shift2
    return Pair(shift1, shift2)
}


private fun IntRange.contains(other: IntRange): Boolean {
    return first <= other.first && other.last <= last
}

private fun IntRange.overlaps(other: IntRange): Boolean {
    return contains(other.first) || contains(other.last)
}
