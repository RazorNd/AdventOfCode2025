package ru.razornd.aoc.day2

import java.io.Reader
import java.nio.file.Path
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.reader

fun main() {
    val path = Path("input/day2-puzzle.txt")
    println("Part1: ${part1(path)}")
    println("Part2: ${part2(path)}")
}

internal fun part1(path: Path): Long {
    return readInput(path.reader())
        .flatMap { range -> range.filter { it.isTwice } }
        .sum()
}

internal fun part2(path: Path): Long {
    return readInput(path.reader())
        .flatMap { range -> range.filter { it.isRepeated } }
        .sum()
}


internal fun readInput(reader: Reader): Sequence<LongRange> {
    return Scanner(reader.buffered()).useDelimiter("[,\\n\\r]")
        .asSequence()
        .map { it.split("-", limit = 2).map { n -> n.toLong() }.let { (start, end) -> start..end } }
}

internal val Long.isTwice: Boolean
    get() {
        val string = toString()
        if (string.length % 2 != 0) return false
        return string.substring(0, string.length / 2).toInt() == string.substring(string.length / 2).toInt()
    }

internal val Long.isRepeated: Boolean
    get() {
        val string = toString()
        return string.length.findDividers()
            .map { divider -> string.windowed(divider, divider, false).map { it.toInt() } }
            .any { groups -> groups.all { it == groups.first() } }
    }

private fun Int.findDividers(): Sequence<Int> = sequence {
    for (i in 1..this@findDividers / 2) {
        if (this@findDividers % i == 0) yield(i)
    }
}
