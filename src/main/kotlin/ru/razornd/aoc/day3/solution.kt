package ru.razornd.aoc.day3

import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader

fun main() {
    println("Part 1: ${part1(Path("input/day3-puzzle.txt"))}")
    println("Part 2: ${part2(Path("input/day3-puzzle.txt"))}")
}

fun part1(path: Path): Int = readInput(path.reader())
    .map { it.findTwoLargestNumber() }
    .map { numbers -> numbers.toList().joinToString("").toInt() }
    .sum()

fun part2(path: Path): Long = readInput(path.reader())
    .map { it.findLargestJoltage() }
    .sum()

fun <E: Comparable<E>> List<E>.findLargestJoltage(): Long {
    val sorted = withIndex().sortedByDescending { it.value }.toMutableList()

    val result = ArrayList<IndexedValue<E>>(12)
    var minIndex = 0

    for (position in 12 downTo 1) {
        val index = sorted.indexOfFirst { size - it.index >= position && it.index >= minIndex }
        val current = sorted.removeAt(index)
        minIndex = current.index + 1
        result.add(current)
    }

    return result.map { it.value }.joinToString("").toLong()
}

fun readInput(reader: Reader): Sequence<List<Short>> {
    return reader.buffered()
        .lineSequence()
        .map { line -> line.toCharArray().map { it.digitToInt().toShort() } }
}

fun <E: Comparable<E>> List<E>.findTwoLargestNumber(): Pair<E, E> {
    val first = this.withIndex().maxBy { it.value }
    if (first.index != lastIndex) return first.value to drop(first.index + 1).max()
    return take(first.index).max() to first.value
}
