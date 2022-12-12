fun day11part1(testInput: List<String>) = createMonkeys(testInput)
    .let { monkeys ->
        repeat(20) {
            monkeys.forEach(Monkey::inspect)
            monkeys.forEach(Monkey::endRound)
        }
        monkeys.sortedByDescending { it.inspectionCount }
            .take(2)
            .map { it.inspectionCount.toLong() }
            .let { (m1, m2) -> m1 * m2 }
    }

fun day11part2(testInput: List<String>) = createMonkeys(testInput)
    .let { monkeys ->
        repeat(10000) {
            monkeys.forEach(Monkey::inspect2)
            monkeys.forEach(Monkey::endRound)
        }
        monkeys.sortedByDescending { it.inspectionCount }
            .take(2)
            .map { it.inspectionCount.toLong() }
            .let { (m1, m2) -> m1 * m2 }
    }


class Monkey(
    val id: Int,
    startingItems: List<Long>,
    val operation: OperationType,
    val dividerNumber: Int,
    private val positiveMonkeyIndex: Int,
    private val negativeMonkeyIndex: Int,
) {
    private lateinit var positiveMonkey: Monkey
    private lateinit var negativeMonkey: Monkey
    private var divisort: Long = 0

    private val currentItems = mutableListOf<Long>()
    private val nextItems = startingItems.toMutableList()
    var inspectionCount = 0

    fun inspect() {
        currentItems.addAll(nextItems)
        nextItems.clear()
        currentItems.forEach { item ->
            inspectionCount++
            val newItemLevel = operation.execute(item) / 3
            if (newItemLevel.mod(dividerNumber) == 0) {
                positiveMonkey.nextItems.add(newItemLevel)
            } else {
                negativeMonkey.nextItems.add(newItemLevel)
            }
        }
        currentItems.clear()
    }

    fun inspect2() {
        currentItems.addAll(nextItems)
        nextItems.clear()
        currentItems.forEach { item ->
            inspectionCount++
            val newItemLevel = operation.execute(item).mod(divisort)
            if (newItemLevel.mod(dividerNumber) == 0) {
                positiveMonkey.nextItems.add(newItemLevel)
            } else {
                negativeMonkey.nextItems.add(newItemLevel)
            }
        }
        currentItems.clear()
    }


    fun meetOthers(monkeys: List<Monkey>) {
        divisort = monkeys.fold(1) { a, b -> a * b.dividerNumber }
        positiveMonkey = monkeys[positiveMonkeyIndex]
        negativeMonkey = monkeys[negativeMonkeyIndex]
    }

    fun endRound() {
    }

    override fun toString(): String {
        return "Monkey(id=$id, currentItems=$currentItems, nextItems=$nextItems, inspectionCount=$inspectionCount)"
    }
}

fun createMonkeys(testInput: List<String>) = testInput
    .windowed(6, 7, true)
    .mapIndexed { monkeyId, properties ->
        val items = getItems(properties[1])
        val operation = getOperation(properties[2])
        Monkey(
            id = monkeyId,
            startingItems = items,
            operation = operation,
            dividerNumber = lastNumber(properties[3]),
            positiveMonkeyIndex = lastNumber(properties[4]),
            negativeMonkeyIndex = lastNumber(properties[5])
        )
    }
    .apply {
        forEach { it.meetOthers(this) }
    }

private fun lastNumber(property: String) = property.split(" ").last().toInt()

private fun getItems(property: String) =
    property.split(": ")[1].split(", ").map { it.toLong() }

private fun getOperation(property: String) = property
    .split(" ")
    .let { propertyParts ->
        val amount = propertyParts.last().toLongOrNull()
        propertyParts.dropLast(1).last()
            .let { operation ->
                when (operation) {
                    "*" -> OperationType { it * (amount ?: it) }
                    "+" -> OperationType { it + (amount ?: it) }
                    else -> throw IllegalArgumentException(operation)
                }
            }
    }

fun interface OperationType {
    fun execute(value: Long): Long
}

