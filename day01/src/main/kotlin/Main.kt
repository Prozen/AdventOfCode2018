import java.io.File

fun main() {
    val input = File(ClassLoader.getSystemResource("input.txt").file).readLines()
    println(input.map { it.toInt() }.sum())
    println(doStuff(input.map { it.toInt() }, mutableSetOf()))

}

fun doStuff(values: List<Int>, visited: MutableSet<Int>): Int {
    var position = 0
    var sumSofar = 0
    while (sumSofar !in visited) {
        visited.add(sumSofar)
        sumSofar += values[position]
        position = (position + 1) % values.size
    }
    return sumSofar
}
