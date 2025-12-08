package ru.razornd.aoc.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day8Test {
    @Test
    fun readPoints() {
        val input = """
            162,817,812
            57,618,57
            906,360,560
            592,479,940
            352,342,300
            466,668,158
        """.trimIndent()

        val actual = readPoints(input.reader())

        assertThat(actual).containsExactly(
            Point(162, 817, 812),
            Point(57, 618, 57),
            Point(906, 360, 560),
            Point(592, 479, 940),
            Point(352, 342, 300),
            Point(466, 668, 158)
        )
    }

    @Test
    fun distanceTo() {
        val point = Point(162, 817, 812)
        assertThat(point.distanceTo(Point(57, 618, 57))).isEqualTo(787.814064357828)
    }
}
