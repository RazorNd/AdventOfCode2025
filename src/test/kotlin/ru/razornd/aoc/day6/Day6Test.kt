package ru.razornd.aoc.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day6Test {
    @Test
    fun transpose() {
        val data = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )

        val actual = data.transpose()

        assertThat(actual)
            .isEqualTo(
                listOf(
                    listOf(1, 4, 7),
                    listOf(2, 5, 8),
                    listOf(3, 6, 9)
                )
            )
    }

    @Test
    fun `transpose with empty elements`() {
        val input = listOf(
            "64",
            "23",
            "314"
        ).map { it.toCharArray().map { c -> c.digitToInt() } }

        val actual = input.transpose(0).map { it.joinToString("").toInt() }

        assertThat(actual)
            .isEqualTo(listOf(623, 431, 4))
    }

}
