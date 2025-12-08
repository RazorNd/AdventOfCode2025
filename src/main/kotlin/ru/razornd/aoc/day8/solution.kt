package ru.razornd.aoc.day8

import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader
import kotlin.math.sqrt

data class Point(val x: Int, val y: Int, val z: Int)

class GenericDSU<T> {
    private val parents = HashMap<T, T>()
    private val sizes = HashMap<T, Int>()

    fun union(a: T, b: T): Boolean {
        var ra = find(a)
        var rb = find(b)
        if (ra == rb) return false
        if (size(ra) < size(rb)) {
            val t = ra
            ra = rb
            rb = t
        }
        parents[rb] = ra
        sizes[ra] = size(ra) + size(rb)
        return true
    }

    fun sizes(): Map<T, Int> = sizes

    private fun find(x: T): T = parents[x]?.let { p -> find(p).also { parents[x] = it } } ?: x

    private fun size(x: T) = sizes.getOrDefault(x, 1)
}

fun main() {
    println("Part 1 (test): ${part1(Path("input/day8-test.txt"), 10)}")
    println("Part 1 (puzzle): ${part1(Path("input/day8-puzzle.txt"), 1000)}")
    println("Part 2 (test): ${part2(Path("input/day8-test.txt"))}")
    println("Part 2 (puzzle): ${part2(Path("input/day8-puzzle.txt"))}")
}

fun part1(path: Path, maxConnection: Int): Int {
    val edges = readPoints(path.reader()).unorderedPairs().sortedBy { (a, b) -> a distanceTo b }

    val dsu = GenericDSU<Point>()

    edges.take(maxConnection).forEach { (a, b) -> dsu.union(a, b) }

    return dsu.sizes().values.sortedDescending().take(3).reduce(Int::times)
}

fun part2(path: Path): Long {
    val points = readPoints(path.reader())

    val edges = points.unorderedPairs().sortedBy { (a, b) -> a distanceTo b }

    var components = points.size

    val dsu = GenericDSU<Point>()

    val lastEdge = edges.find { (a, b) ->
        if (dsu.union(a, b)) components--
        components == 1
    } ?: error("No edges found")

    return lastEdge.toList().map { it.x.toLong() }.reduce(Long::times)
}

infix fun Point.distanceTo(other: Point) = sqrt(
    ((x - other.x).pow2() + (y - other.y).pow2() + (z - other.z).pow2()).toDouble()
)

fun Int.pow2(): Long {
    return this.toLong() * this.toLong()
}

fun <T, R> List<T>.unorderedPairs(pairwise: (T, T) -> R): Sequence<R> = sequence {
    for (i in indices) {
        for (j in i + 1 until size) {
            yield(pairwise(this@unorderedPairs[i], this@unorderedPairs[j]))
        }
    }
}

fun <T> List<T>.unorderedPairs(): Sequence<Pair<T, T>> = unorderedPairs { a, b -> a to b }

fun readPoints(reader: Reader): List<Point> {
    return reader.buffered().use {
        it.lineSequence().map { line -> line.split(',', limit = 3).map { n -> n.toInt() } }
            .map { (x, y, z) -> Point(x, y, z) }.toList()
    }
}
