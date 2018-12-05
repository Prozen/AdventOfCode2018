import java.io.File
import java.lang.Math.abs
import java.util.ArrayDeque

fun main() {
    val input = File(ClassLoader.getSystemResource("input.txt").file).readLines().first().toList()
    println(process(input))
    println(('a'..'z').map { letter ->
        input.filterNot { it == letter || it == letter.toUpperCase() }
    }.map(::process).min())
}

private fun process(input: List<Char>): Int {
    val toProcess = ArrayDeque(input)
    val processed = ArrayDeque<Char>()

    while (toProcess.isNotEmpty())
        toProcess.poll().also {
            if (processed.isNotEmpty() && abs(processed.peek() - it) == 32)
                processed.poll()
            else
                processed.addFirst(it)
        }
    return processed.size
}