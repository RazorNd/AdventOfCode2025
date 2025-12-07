package ru.razornd.aoc.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun allIndexOf() {
        val actual = "..^...^.....^..".allIndexesOf('^')

        assertThat(actual).containsExactly(2, 6, 12)
    }

}
