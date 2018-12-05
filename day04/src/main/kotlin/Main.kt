import java.io.File
import java.lang.Integer.min

fun main() {
    val input = File(ClassLoader.getSystemResource("input.txt").file).readLines().sorted()
    val data = mutableMapOf<String, IntArray>()
    var currentGuard: String? = null
    var fallenAsleep = -1
    input.forEach {
        val split = it.split(" ")
        when (split[2]) {
            "Guard" -> {
                currentGuard = split[3]
                data.putIfAbsent(split[3], IntArray(60))
            }
            "falls" -> fallenAsleep = split[1].dropLast(1).split(":").map(String::toInt)[1]
            "wakes" -> fallenAsleep
                .rangeTo(min(59, split[1]
                    .dropLast(1)
                    .split(":")
                    .map(String::toInt)[1] + 1))
                .map { data[currentGuard!!]!![it]++ }
        }
    }
    println(data.maxBy { it.value.sum() }?.run {
        key to value
            .mapIndexed { index, i -> index to i }
            .maxBy { it.second }
    })
    println(data.maxBy { it.value.max() ?: 0 }?.run {
        key to value
            .mapIndexed { index, i -> index to i }
            .maxBy { it.second }
    })
}
