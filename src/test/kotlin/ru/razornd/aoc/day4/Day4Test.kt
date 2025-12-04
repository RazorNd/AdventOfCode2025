package ru.razornd.aoc.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Test {
    @Test
    fun readGrid() {
        val input = """
            ..@@.@@@@.
            @@@.@.@.@@
            @@@@@.@.@@
            @.@@@@..@.
            @@.@@@@.@@
            .@@@@@@@.@
            .@.@.@.@@@
            @.@@@.@@@@
            .@@@@@@@@.
            @.@.@@@.@.
        """.trimIndent()

        val actual = readGrid(input.reader())

        assertThat(actual).isNotNull()
    }
}
