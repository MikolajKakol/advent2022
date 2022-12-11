fun day07part1(testInput: List<String>) = read(testInput).let { root ->
    var count = 0
    root.accept {
        if (it.size <= 100000) {
            count += it.size
        }
    }
    count
}

fun day07part2(testInput: List<String>) = read(testInput).let { root ->
    val discSpace = 70000000
    val neededSpace = 30000000
    val freeSpace = discSpace - root.size
    val sizeToDeleteNeeded = neededSpace - freeSpace
    var foundDir: Directory? = null
    root.accept {
        val dir = foundDir
        if (it.size >= sizeToDeleteNeeded) {
            if (dir == null) {
                foundDir = it
            } else if (it.size < dir.size) {
                foundDir = it
            }
        }
    }
    foundDir!!.size
}

private fun read(testInput: List<String>) = with(InputReader()) {
    testInput
        .drop(1)
        .forEach {
            if (it[0] == '$') {
                runCommand(it)
            } else {
                recordListing(it)
            }
        }
    root
}

private class InputReader {

    val root = Directory("root", null)
    var currentDirectory: Directory = root

    fun runCommand(cmd: String) {
        val cmdParts = cmd.split(" ")
        when (cmdParts[1]) {
            "cd" -> cd(cmdParts[2])
        }
    }

    private fun cd(destination: String) {
        currentDirectory = if (destination == "..") {
            currentDirectory.parent!!
        } else {
            currentDirectory.children
                .filterIsInstance<Directory>()
                .first { it.name == destination }
        }
    }

    fun recordListing(listing: String) {
        val (def, name) = listing.split(" ")
        when (def) {
            "dir" -> addDir(name)
            else -> addFile(def.toInt(), name)
        }

    }

    private fun addFile(size: Int, name: String) {
        currentDirectory.children += File(name, size)
    }

    private fun addDir(name: String) {
        currentDirectory.children += Directory(name, currentDirectory)
    }

}

private sealed interface Resource {
    val name: String
    val size: Int

    fun accept(visitor: Visitor)
}

private data class File(override val name: String, override val size: Int) : Resource {
    override fun accept(visitor: Visitor) {
        //no-op
    }
}

private data class Directory(override val name: String, val parent: Directory?) : Resource {
    val children: MutableList<Resource> = mutableListOf()

    override val size
        get() = children.sumOf { it.size }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        children.forEach {
            it.accept(visitor)
        }
    }
}

private fun interface Visitor {
    fun visit(resource: Directory)
}