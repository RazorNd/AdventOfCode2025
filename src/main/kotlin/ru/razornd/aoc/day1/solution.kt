package ru.razornd.aoc.day1

import java.io.Reader
import java.lang.StrictMath.floorMod
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader
import kotlin.math.absoluteValue

fun main() {
    val input = Path("input/day1-puzzle.txt")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

internal fun part1(path: Path): Int {
    var dial = 50
    return readNumbers(path.reader())
        .map { n -> rotate(dial, n).also { dial = it } }
        .count { it == 0 }
}

internal fun part2(path: Path): Int {
    var dial = 50
    var result = 0
    for (n in readNumbers(path.reader())) {
        result += roundOverZero(dial, n)
        dial = rotate(dial, n)
    }
    return result
}

internal fun readNumbers(reader: Reader): Sequence<Int> {
    return reader.buffered()
        .lineSequence()
        .map {
            val sign = when (it[0]) {
                'L' -> -1
                'R' -> 1
                else -> error("Unknown direction: $it")
            }
            sign * it.substring(1).toInt()
        }
}

internal fun rotate(base: Int, direction: Int): Int = floorMod(base + direction, 100)

internal fun roundOverZero(base: Int, direction: Int): Int {
    val start = if (direction < 0) (100 - base) % 100 else base
    return (start + direction.absoluteValue) / 100
}

