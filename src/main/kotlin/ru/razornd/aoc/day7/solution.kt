package ru.razornd.aoc.day7

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader

fun main() {
    println("Part 1 (test): ${part1(Path("input/day7-test.txt"))}")
    println("Part 1 (puzzle): ${part1(Path("input/day7-puzzle.txt"))}")

    println("Part 2 (test): ${part2(Path("input/day7-test.txt"))}")
    println("Part 2 (puzzle): ${part2(Path("input/day7-puzzle.txt"))}")
}

fun part1(path: Path): Int {
    val lines = path.reader().readLines()
    var result = 0

    val beams = mutableSetOf(lines.first().indexOf('S'))

    for (line in lines.drop(1)) {
        val splitters = line.allIndexesOf('^')

        val intersect = beams intersect splitters

        result += intersect.size

        beams += intersect.flatMap { listOf(it - 1, it + 1) }
        beams -= intersect
    }

    return result
}

fun part2(path: Path): Long {
    val lines = path.reader().readLines()

    val beams = mutableMapOf(lines.first().indexOf('S') to 1L)

    for (line in lines.drop(1)) {
        val splitters = line.allIndexesOf('^')
        if (splitters.isEmpty()) continue

        val intersect = beams.keys intersect splitters

        intersect.forEach { idx ->
            beams[idx - 1] = beams[idx]!! + beams.getOrDefault(idx - 1, 0L)
            beams[idx + 1] = beams[idx]!! + beams.getOrDefault(idx + 1, 0L)
        }

        beams -= intersect
    }

    return beams.values.sum()
}

fun String.allIndexesOf(c: Char): Set<Int> = buildSet {
    var index = indexOf(c, startIndex = 0)
    while (index >= 0) {
        add(index)
        index = indexOf(c, startIndex = index + 1)
    }
}
