fun day10part1(testInput: List<String>) = with(Screen()) {
    testInput.forEach { runInstruction(it) }
    keyCycles.zip(keyCyclesValues) { a, b -> a * b }.sum()
}

fun day10part2(testInput: List<String>) = with(Screen()) {
    testInput.forEach { runInstruction(it) }
    buffer.toString().trim()
}


class Screen {
    private var regX: Int = 1
    private var finishedCyclesCount = 0

    val buffer = StringBuilder()

    val keyCycles = listOf(20, 60, 100, 140, 180, 220)
    val keyCyclesValues = mutableListOf<Int>()

    fun runInstruction(instruction: String) {
        val (cmd, value) = instruction.split(" ")
            .let { it[0] to it.getOrNull(1)?.toInt() }

        when (cmd) {
            "noop" -> noop()
            "addx" -> addX(requireNotNull(value))
        }
    }

    private fun noop() {
        advanceCycle()
    }

    private fun addX(value: Int) {
        advanceCycle()
        advanceCycle()
        regX += value
    }

    private fun advanceCycle() {
        val nextCycle = finishedCyclesCount + 1
        if (keyCycles.contains(nextCycle)) {
            keyCyclesValues.add(regX)
        }

        val renderPoint = (nextCycle % 40).let { if (it == 0) 40 else it }
        val spritePosition = regX until regX + 3
        val renderPixel = if (spritePosition.contains(renderPoint))
            "#"
        else
            "."
        buffer.append(renderPixel)
        if (nextCycle % 40 == 0) {
            buffer.append("\n")
        }

        finishedCyclesCount++
    }
}
