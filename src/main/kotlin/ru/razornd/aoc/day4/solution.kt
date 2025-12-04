package ru.razornd.aoc.day4

import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader


class Grid(
    private val data: CharArray,
    private val width: Int,
    private val height: Int
) {
    private val xRange get() = 0 until width
    private val yRange get() = 0 until height

    inner class Index(private val x: Int, private val y: Int) {

        var value: Char
            get() = data[y * width + x]
            set(value) {
                data[y * width + x] = value
            }

        private val isValid: Boolean get() = x in xRange && y in yRange

        fun adjacentIndexes(): Sequence<Index> {
            return allAdjacentIndexes().filter { it.isValid }
        }

        private fun allAdjacentIndexes(): Sequence<Index> = sequence {
            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (dx == 0 && dy == 0) continue
                    yield(Index(x + dx, y + dy))
                }
            }
        }
        override fun toString() = "Index(x=$x, y=$y)"
    }

    fun indexes(): Sequence<Index> = sequence {
        for (x in xRange) {
            for (y in yRange) {
                yield(Index(x, y))
            }
        }
    }
}

fun main() {
    println("Part1 (test): ${part1(Path("input/day4-test.txt"))}")
    println("Part1 (puzzle): ${part1(Path("input/day4-puzzle.txt"))}")
    println("Part2 (test): ${part2(Path("input/day4-test.txt"))}")
    println("Part2 (puzzle): ${part2(Path("input/day4-puzzle.txt"))}")
}

fun part1(path: Path): Int {
    return readGrid(path.reader())
        .paperIndexes()
        .count { it.canAccess }
}

fun part2(path: Path): Int {
    val grid = readGrid(path.reader())
    var count = 0

    do {
        val accessiblePaperIndexes = grid.paperIndexes()
            .filter { it.canAccess }
            .toList()

        count += accessiblePaperIndexes.size

        accessiblePaperIndexes.forEach { it.removePaper() }

    } while (accessiblePaperIndexes.isNotEmpty())

    return count
}

fun Grid.paperIndexes(): Sequence<Grid.Index> = indexes().filter { it.isPaper }

val Grid.Index.isPaper: Boolean get() = value == '@'

val Grid.Index.canAccess: Boolean get() = adjacentIndexes().count { it.isPaper } < 4

private fun Grid.Index.removePaper() {
    value = '.'
}

fun readGrid(reader: Reader): Grid {
    val data = reader.buffered().lineSequence().map { it.toCharArray() }.toList()

    val width = data.first().size
    val height = data.size

    return Grid(
        CharArray(width * height) {
            data[it / width][it % width]
        },
        width,
        height
    )
}
