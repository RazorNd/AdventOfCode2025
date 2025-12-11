package ru.razornd.aoc.day11

import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader

fun main() {
    println("Part 1 (test): ${part1(Path("input/day11-test.txt"))}")
    println("Part 1 (puzzle): ${part1(Path("input/day11-puzzle.txt"))}")
    println("Part 2 (test): ${part2(Path("input/day11_p2-test.txt"))}")
    println("Part 2 (puzzle): ${part2(Path("input/day11-puzzle.txt"))}")
}

fun part1(path: Path) = traceCount(readNetWork(path.reader()), "you", "out")

fun part2(path: Path): Long {
    val network = readNetWork(path.reader())

    val dacFft =
        traceCount(network, "svr", "dac") * traceCount(network, "dac", "fft") * traceCount(network, "fft", "out")

    val fftDac =
        traceCount(network, "svr", "fft") * traceCount(network, "fft", "dac") * traceCount(network, "dac", "out")

    return dacFft + fftDac

}

fun traceCount(network: Map<String, List<String>>, startNode: String, endNode: String): Long {
    val memo = mutableMapOf<String, Long>()

    fun dfs(node: String): Long = memo.getOrPut(node) {
        if (node == endNode) {
            1L
        } else {
            network[node].orEmpty().sumOf { next -> dfs(next) }
        }
    }

    return dfs(startNode)
}

fun readNetWork(reader: Reader): Map<String, List<String>> = reader.buffered().useLines { lines ->
    lines.map { it.split(": ", limit = 2) }.associate { (key, value) -> key to value.split(" ") }
}
