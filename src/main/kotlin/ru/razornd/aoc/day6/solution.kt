package ru.razornd.aoc.day6

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines

fun main() {
    println("Part 1 (test): ${part1(Path("input/day6-test.txt"))}")
    println("Part 1 (puzzle): ${part1(Path("input/day6-puzzle.txt"))}")
    println("Part 2 (test): ${part2(Path("input/day6-test.txt"))}")
    println("Part 2 (puzzle): ${part2(Path("input/day6-puzzle.txt"))}")
}

fun part1(path: Path): Long {
    return path.readLines()
        .map { line -> line.split(" ").filter { it.isNotBlank() } }
        .transpose()
        .sumOf {
            it.dropLast(1)
                .map { arg -> arg.toLong() }
                .reduce(it.last().toOperator())
        }
}

fun part2(path: Path): Long {
    val input = path.readLines()

    val arguments = input.dropLast(1)
        .map { line -> line.toCharArray().toList() }
        .transpose(' ').map { line -> line.joinToString("").trim() }
        .groupWhen { it.isNotBlank() }
        .map { it.map { arg -> arg.toLong() } }

    val operations = input.last()
        .split(" ")
        .filter { it.isNotBlank() }
        .map { it.toOperator() }

    return arguments.zip(operations).sumOf { (arguments, operation) -> arguments.reduce(operation) }
}

fun <E> List<List<E>>.transpose(defaultElement: E) = transpose { _, _ -> defaultElement }

fun <E> List<List<E>>.transpose(
    onNotExistsElement: (Int, Int) -> E = { i, j -> error("Element at position ($i, $j) not exists") }
): List<List<E>> {
    if (isEmpty()) return emptyList()

    val columns = maxOf { it.size }
    val rows = this.size

    return List(columns) { j ->
        List(rows) { i ->
            getOrNull(i)?.getOrNull(j) ?: onNotExistsElement(i, j)
        }
    }
}

fun <E> Collection<E>.groupWhen(predicate: (E) -> Boolean): List<List<E>> = buildList {
    var currentGroup = mutableListOf<E>()
    for (element in this@groupWhen) {
        if (predicate(element)) {
            currentGroup.add(element)
        } else {
            add(currentGroup)
            currentGroup = mutableListOf()
        }
    }
    add(currentGroup)
}

fun String.toOperator(): (Long, Long) -> Long = when (this) {
    "+" -> { a, b -> a + b }
    "*" -> { a, b -> a * b }
    else -> error("Unknown operator: $this")
}
