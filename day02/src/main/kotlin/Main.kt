import java.io.File

fun main() {
    val strings = File(ClassLoader.getSystemResource("input.txt").file).readLines()
    strings.map { it.groupingBy { it }.eachCount() }.run {
        println(count { it.containsValue(2) } * count { it.containsValue(3) })
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