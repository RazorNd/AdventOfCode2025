package ru.razornd.aoc.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day1Tests {
    @Test
    fun readNumbers() {
        val input = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
        """.trimIndent()

        val actual = readNumbers(input.reader()).toList()

        assertThat(actual)
            .containsExactly(-68, -30, 48, -5, 60, -55, -1, -99, 14, -82)
    }

    @Test
    fun rotate() {
        assertThat(rotate(50, -68)).isEqualTo(82)
        assertThat(rotate(82, -30)).isEqualTo(52)
        assertThat(rotate(52, 48)).isEqualTo(0)
    }

    @Test
    fun rotateRounded() {
        assertThat(roundOverZero(50, -68)).isEqualTo(1)
        assertThat(roundOverZero(82, -30)).isEqualTo(0)
        assertThat(roundOverZero(52, 48)).isEqualTo(1)
        assertThat(roundOverZero(52, 300)).isEqualTo(3)
    }

    @Test
    fun part1() {
        assertThat(part1(Path("input/day1-test.txt"))).isEqualTo(3)
    }

    @Test
    fun part2() {
        assertThat(part2(Path("input/day1-test.txt"))).isEqualTo(6)
    }

}
