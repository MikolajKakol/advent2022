fun day13part1(testInput: List<String>) = testInput
    .windowed(2, 3)
    .mapIndexed { index, (lhs, rhs) ->
        val left = parseArray(lhs.toCharArray().iterator())
        val right = parseArray(rhs.toCharArray().iterator())

        if (isRhsBigger(left, right)) {
            index + 1
        } else {
            0
        }
    }
    .sum()

fun day13part2(testInput: List<String>) = testInput

private fun isRhsBigger(lhs: ArrayOItem.Array, rhs: ArrayOItem.Array) = rhs >= lhs

private sealed interface ArrayOItem : Comparable<ArrayOItem> {
    override fun compareTo(other: ArrayOItem): Int {
        return when (this) {
            is Array -> when (other) {
                is Array -> this.compareTo(other)
                is Item -> this.compareTo(Array(listOf(other)))
            }

            is Item -> when (other) {
                is Array -> Array(listOf(this)).compareTo(other)
                is Item -> this.compareTo(other)
            }
        }
    }

    data class Array(val items: List<ArrayOItem>) : ArrayOItem {
        override fun compareTo(other: ArrayOItem): Int {
            return if (other is Array) {
                this.items.zip(other.items)
                    .map { (a, b) -> a.compareTo(b) }
                    .filterNot { it == 0 }
                    .firstOrNull()
                    ?: run {
                        val sizeResult = this.items.size.compareTo(other.items.size)
                        sizeResult
                    }
            } else {
                super.compareTo(other)
            }
        }
    }

    data class Item(val item: Int) : ArrayOItem {
        override fun compareTo(other: ArrayOItem): Int {
            return if (other is Item) {
                item.compareTo(other.item)
            } else {
                super.compareTo(other)
            }

        }
    }
}

typealias Iterator = CharIterator

private fun parseArray(iter: Iterator): ArrayOItem.Array = ArrayOItemBuilder.createArray {
    var pendingNumber = ""
    do {
        val next = iter.nextChar()
        when {
            next.isDigit() -> pendingNumber += next.digitToInt()
            next.code == '['.code -> add(parseArray(iter))
            next.code == ']'.code || next.code == ','.code -> {
                add(pendingNumber.toIntOrNull())
                pendingNumber = ""
            }
        }

    } while (next.code != ']'.code && iter.hasNext())
}

private object ArrayOItemBuilder {

    fun createArray(builder: ArrayBuilder.() -> Unit): ArrayOItem.Array {
        val b = ArrayBuilder()
        builder.invoke(b)
        return b.create()
    }

    class ArrayBuilder {

        private val items = mutableListOf<ArrayOItem>()

        fun add(item: ArrayOItem) {
            items.add(item)
        }

        fun add(item: Int?) {
            if (item != null)
                items.add(ArrayOItem.Item(item))
        }

        fun create(): ArrayOItem.Array {
            return ArrayOItem.Array(items)
        }
    }
}
