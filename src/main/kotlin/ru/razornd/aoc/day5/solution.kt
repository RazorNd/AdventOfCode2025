package ru.razornd.aoc.day5

import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader
import kotlin.math.max
import kotlin.math.min

fun main() {
    println("Part 1 (test): ${part1(Path("input/day5-test.txt"))}")
    println("Part 1 (puzzle): ${part1(Path("input/day5-puzzle.txt"))}")
    println("Part 2 (test): ${part2(Path("input/day5-test.txt"))}")
    println("Part 2 (puzzle): ${part2(Path("input/day5-puzzle.txt"))}")
}

fun part1(path: Path): Int {
    val (ranges, available) = readInput(path.reader())

    return available.count { id -> ranges.any { id in it } }
}

fun part2(path: Path): Long {
    val (ranges) = readInput(path.reader())

    val compacted = compactRanges(ranges)

    return compacted.fold(0L) { acc, range -> acc + range.last - range.first + 1 }
}

private fun compactRanges(ranges: Collection<LongRange>): List<LongRange> {
    if (ranges.isEmpty()) return emptyList()

    val sortedRanges = ranges.sortedBy { it.first }

    var current = sortedRanges.first()

    return buildList {
        for (next in sortedRanges.drop(1)) {
            if (current overlaps next) {
                current = current merge next
            } else {
                add(current)
                current = next
            }
        }
        add(current)
    }
}

infix fun LongRange.overlaps(other: LongRange): Boolean = endInclusive >= other.first

infix fun LongRange.merge(other: LongRange): LongRange = min(first, other.first)..max(last, other.last)

fun readInput(reader: Reader): Pair<Collection<LongRange>, Collection<Long>> {
    val lines = reader.buffered().readLines()

    val splitPosition = lines.indexOfFirst { it.isBlank() }

    val ranges = lines.subList(0, splitPosition)
        .map { line -> line.split('-', limit = 2).map { it.toLong() } }
        .map { (start, end) -> start..end }

    val available = lines.subList(splitPosition + 1, lines.size).map { it.toLong() }

    return ranges to available
}
