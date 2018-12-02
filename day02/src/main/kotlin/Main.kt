import java.io.File
import java.lang.Math.min

fun main() {
    val strings = File(ClassLoader.getSystemResource("input.txt").file).readLines()
    strings.map {
        it.groupBy { it }
            .mapValues { it.value.size }.run {
                (min(1, count { it.value == 2 }) to min(1, count { it.value == 3 }))
            }
    }.reduce { (left1, right1), (left2, right2) ->
        left1 + left2 to right1 + right2
    }.run {
        println(first * second)
    }

    (0 until strings.size).asSequence().flatMap { first ->
        (first + 1 until strings.size).asSequence()
            .filter { differByOne(strings[first], strings[it]) }
            .map { strings[first].filterIndexed { num, c -> strings[it][num] == c } }
    }.first().run {
        println(this)
    }
}


fun differByOne(str1: String, str2: String) =
    (0 until str1.length).map { if (str1[it] == str2[it]) 0 else 1 }.sum() == 1