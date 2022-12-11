fun day08part1(testInput: List<String>) = testInput
    .toMatrix()
    .let { matrix ->
        matrix.drop(1).dropLast(1)
            .mapIndexed { index, trees ->
                val row = index + 1

                trees.drop(1).dropLast(1)
                    .mapIndexed { index2, tree ->
                        val column = index2 + 1
                        val visibleFromLeft = (0 until column).all { tree > matrix[row][it] }
                        val visibleFromRight = (column + 1 until trees.size).all { tree > matrix[row][it] }
                        val visibleFromTop = (0 until row).all { tree > matrix[it][column] }
                        val visibleFromBottom = (row + 1 until matrix.size).all { tree > matrix[it][column] }
                        if (visibleFromLeft || visibleFromRight || visibleFromTop || visibleFromBottom) {
                            0
                        } else {
                            1
                        }
                    }
                    .sum()
            }
            .sum()
            .let { matrix.size * matrix[0].size - it }
    }


fun day08part2(testInput: List<String>) = testInput
    .toMatrix()
    .let { matrix ->
        matrix
            .mapIndexed { row, trees ->
                trees
                    .mapIndexed { column, tree ->
                        val visibleFromLeft = (0 until column).reversed()
                            .calculate(matrix, xAxis = null, yAxis = row, tree)
                        val visibleFromRight = (column + 1 until trees.size)
                            .calculate(matrix, xAxis = null, yAxis = row, tree)
                        val visibleFromTop = (0 until row).reversed()
                            .calculate(matrix, xAxis = column, yAxis = null, tree)
                        val visibleFromBottom = (row + 1 until matrix.size)
                            .calculate(matrix, xAxis = column, yAxis = null, tree)
                        visibleFromLeft * visibleFromRight * visibleFromTop * visibleFromBottom
                    }
                    .max()
            }
            .max()
    }

private fun IntProgression.calculate(matrix: List<List<Int>>, xAxis: Int?, yAxis: Int?, tree: Int): Int {
    return this
        .map {
            if (yAxis != null) {
                matrix[yAxis][it]
            } else {
                matrix[it][xAxis!!]
            }
        }
        .takeUntil { tree <= it }
        .count()
}

inline fun <T> Iterable<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val list = ArrayList<T>()
    for (item in this) {
        list.add(item)
        if (predicate(item))
            break
    }
    return list
}

private fun List<String>.toMatrix(): List<List<Int>> = map {
    it.asIterable()
        .map(Char::digitToInt)
}
