package ru.razornd.aoc.day10

import java.io.Reader
import java.nio.file.Path
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.reader

data class Machine(val requiredIndicators: Indicators, val buttons: List<Button>, val joltages: List<Int>)

data class Indicators(private val length: Int, private val bitSet: BitSet) {
    constructor(
        length: Int,
        lightOnPositions: List<Int> = emptyList()
    ) : this(length, BitSet(length).apply { lightOnPositions.forEach { set(it) } })

    constructor(length: Int, vararg lightOnPositions: Int) : this(length, lightOnPositions.toList())

    fun zeroState(): Indicators = Indicators(length)

    fun toggle(positions: BitSet): Indicators = Indicators(length, BitSet().apply {
        or(bitSet)
        xor(positions)
    })

    override fun toString() = (0..<length).map { if (bitSet.get(it)) '#' else '.' }
        .joinToString(separator = "", prefix = "[", postfix = "]")
}

@JvmInline
value class Button(val togglePositions: List<Int>) {

    constructor(vararg togglePositions: Int) : this(togglePositions.toList())

    infix fun pressOn(indicators: Indicators): Indicators {
        return indicators.toggle(BitSet().apply { togglePositions.forEach { set(it) } })
    }
}

fun main() {
    println("Part 1 (test): ${part1(Path("input/day10-test.txt"))}")
    println("Part 1 (puzzle): ${part1(Path("input/day10-puzzle.txt"))}")
}

fun part1(path: Path): Int {
    return path.reader().use { reader ->
        readMachineSchema(reader).sumOf { findFewestButtonPress(it.requiredIndicators, it.buttons) }
    }
}

fun findFewestButtonPress(requiredIndicators: Indicators, buttons: List<Button>): Int {
    data class State(val current: Indicators, val pressed: Set<Button>)

    val queue = ArrayDeque<State>(listOf(State(requiredIndicators.zeroState(), emptySet())))

    while (queue.isNotEmpty()) {
        val (current, pressed) = queue.removeFirst()

        if (current == requiredIndicators) return pressed.size

        queue.addAll(buttons.filter { it !in pressed }.map { State(it pressOn current, pressed + it) })
    }
    error("No solution found")
}

fun readMachineSchema(reader: Reader): Sequence<Machine> = reader.buffered().lineSequence().map { parseMachine(it) }

fun parseMachine(input: String): Machine {
    val closingBracketIndex = input.indexOf(']')
    assert(closingBracketIndex > 0) { "Invalid input: $input" }
    val lastParenthesisIndex = input.lastIndexOf(')')
    assert(lastParenthesisIndex > closingBracketIndex) { "Invalid input: $input" }

    return Machine(
        requiredIndicators = parseIndicators(input.substring(0, closingBracketIndex + 1)),
        buttons = parseButtons(input.substring(closingBracketIndex + 2, lastParenthesisIndex + 1)),
        joltages = parseJoltages(input.substring(lastParenthesisIndex + 2, input.length))
    )
}

fun parseIndicators(input: String): Indicators {
    assert(input.startsWith('[')) { "Invalid input: $input" }
    assert(input.endsWith(']')) { "Invalid input: $input" }
    return Indicators(
        input.length - 2,
        input.drop(1).dropLast(1).mapIndexedNotNull { i, c -> if (c == '#') i else null })
}

fun parseButtons(input: String): List<Button> = input.split(' ').map { parseButton(it) }

fun parseButton(input: String): Button {
    assert(input.startsWith('(')) { "Invalid input: $input" }
    assert(input.endsWith(')')) { "Invalid input: $input" }
    return Button(input.drop(1).dropLast(1).split(',').map { it.toInt() })
}

fun parseJoltages(input: String): List<Int> {
    assert(input.startsWith('{')) { "Invalid input: $input" }
    assert(input.endsWith('}')) { "Invalid input: $input" }

    return input.drop(1).dropLast(1).split(',').map { it.toInt() }
}

operator fun List<Int>.minus(button: Button): List<Int> {
    val result = toMutableList()
    button.togglePositions.forEach { result[it]-- }
    return result
}
