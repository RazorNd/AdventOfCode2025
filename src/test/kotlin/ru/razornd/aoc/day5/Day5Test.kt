package ru.razornd.aoc.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun readInput() {
        val input = """
            3-5
            10-14
            16-20
            12-18

            1
            5
            8
            11
            17
            32
        """.trimIndent()

        val (ranges, ids) = readInput(input.reader())

        assertThat(ranges).containsExactly(3L..5L, 10L..14L, 16L..20L, 12L..18L)
        assertThat(ids).containsExactly(1, 5, 8, 11, 17, 32)
    }
}
