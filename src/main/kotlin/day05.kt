import java.util.Stack

fun day05part1(testInput: List<String>): Any {
    val stackCount = testInput.first { it.getOrNull(1) == '1' }.length / 4
    val stacks = (0..stackCount).map { Stack<Char>() }
    testInput.forEach { line ->
        var readStack = false
        var readCommand = false
        if (line.isEmpty()) {
        } else if (line[0] == 'm') {
            readCommand = true
        } else if (line[1] == ' ' || line[1].isUpperCase()) {
            readStack = true
        }

        if (readStack) {
            readStack(line, stacks)
        }

        if (readCommand) {
            val words = line.split(" ")
            val quantity = words[1].toInt()
            val from = words[3].toInt()
            val to = words[5].toInt()

            repeat(quantity) {
                stacks[to - 1].add(stacks[from - 1].pop())
            }
        }
    }
    return stacks.map { it.peek() }.joinToString(separator = "")
}

fun day05part2(testInput: List<String>): Any {
    val stackCount = testInput.first { it.getOrNull(1) == '1' }.length / 4
    val stacks = (0..stackCount).map { Stack<Char>() }
    testInput.forEach { line ->
        var readStack = false
        var readCommand = false
        if (line.isEmpty()) {
        } else if (line[0] == 'm') {
            readCommand = true
        } else if (line[1] == ' ' || line[1].isUpperCase()) {
            readStack = true
        }

        if (readStack) {
            readStack(line, stacks)
        }

        if (readCommand) {
            val words = line.split(" ")
            val quantity = words[1].toInt()
            val from = words[3].toInt()
            val to = words[5].toInt()


            stacks[to - 1].addAll(stacks[from - 1].takeLast(quantity))
            repeat(quantity) {
                stacks[from - 1].pop()
            }
        }
    }
    return stacks.map { it.peek() }.joinToString(separator = "")
}

private fun readStack(line: String, stacks: List<Stack<Char>>) {
    line.chunked(4).forEachIndexed { index, s ->
        s[1].let {
            if (it.isUpperCase()) {
                stacks[index].add(0, it)
            }
        }
    }
}
