import java.io.File

fun main() {
    val strings = File(ClassLoader.getSystemResource("input.txt").file).readLines()
    val field = Array(1000) { Array(1000) { 0 } }
    strings.forEach {
        val words = it.split(" ")
        val start = words[2].dropLast(1).split(",").map { it.toInt() }
        val size = words[3].split("x").map { it.toInt() }
        (start[0] until start[0] + size[0]).forEach { x ->
            (start[1] until start[1] + size[1]).forEach { y ->
                field[x][y] += 1
            }
        }
    }
    println(field.sumBy { it.count { it > 1 } })

    val map = strings.map {
        val words = it.split(" ")
        val start = words[2].dropLast(1).split(",").map { it.toInt() }
        val size = words[3].split("x").map { it.toInt() }
        words[0] to (Point(start[0], start[1]) to Point(start[0] + size[0], start[1] + size[1]))
    }
    println(map.first { pair ->
        map.all {
            pair.first == it.first ||
                doNotOverlap(pair.second.first, pair.second.second, it.second.first, it.second.second)
        }
    }.first)

}

data class Point(val x: Int, val y: Int)

fun doNotOverlap(l1: Point, r1: Point, l2: Point, r2: Point) =
    l1.x >= r2.x || l2.x >= r1.x || l1.y >= r2.y || l2.y >= r1.y