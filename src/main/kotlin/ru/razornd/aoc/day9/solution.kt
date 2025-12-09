package ru.razornd.aoc.day9

import ru.razornd.aoc.day8.unorderedPairs
import java.io.Reader
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.reader
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)

data class Rectangle(val a: Point, val b: Point)

data class Line(val a: Point, val b: Point)

fun main() {
    println("Part 1 (test): ${part1(Path("input/day9-test.txt"))}")
    println("Part 1 (puzzle): ${part1(Path("input/day9-puzzle.txt"))}")
    println("Part 2 (test): ${part2(Path("input/day9-test.txt"))}")
    println("Part 2 (puzzle): ${part2(Path("input/day9-puzzle.txt"))}")
}

fun part1(path: Path): Long {
    return readPoints(path.reader())
        .unorderedPairs()
        .maxOf { (a, b) -> a area b }
}

fun part2(path: Path): Long {
    val points = readPoints(path.reader())

    val lines = points.windowed(2)
        .map { (a, b) -> Line(a, b) }
        .toList() + listOf(Line(points.last(), points.first()))

    return points
        .unorderedPairs()
        .filter { (a, b) ->
            val rectangle = Rectangle(a, b)
            lines.none { line -> line intersectsInterior rectangle }
        }
        .maxOf { (a, b) -> a area b }
}

infix fun Line.intersectsInterior(rectangle: Rectangle): Boolean {
    val x1 = min(rectangle.a.x, rectangle.b.x)
    val x2 = max(rectangle.a.x, rectangle.b.x)
    val y1 = min(rectangle.a.y, rectangle.b.y)
    val y2 = max(rectangle.a.y, rectangle.b.y)

    val sx1 = min(a.x, b.x)
    val sx2 = max(a.x, b.x)
    val sy1 = min(a.y, b.y)
    val sy2 = max(a.y, b.y)

    return !(sx2 <= x1 || sx1 >= x2 || sy2 <= y1 || sy1 >= y2)
}

infix fun Point.area(other: Point): Long = (abs(x - other.x) + 1L) * (abs(y - other.y) + 1L)

fun readPoints(reader: Reader): List<Point> {
    return reader.buffered().use {
        it.lines()
            .map { line -> line.split(',', limit = 2).map { n -> n.toInt() } }
            .map { (x, y) -> Point(x, y) }
            .toList()
    }
}
