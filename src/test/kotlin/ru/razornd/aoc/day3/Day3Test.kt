package ru.razornd.aoc.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day3Test {
    @Test
    fun readInput() {
        val input = """
            987654321111111
            811111111111119
            234234234234278
            818181911112111
        """.trimIndent()

        val actual = readInput(input.reader()).toList()

        assertThat(actual).containsExactly(
            listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1),
            listOf(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9),
            listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8),
            listOf(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1)
        )

    }

    @Test
    fun findTwoLargestNumber() {
        assertThat(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1).findTwoLargestNumber()).isEqualTo(9 to 8)
        assertThat(listOf(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9).findTwoLargestNumber()).isEqualTo(8 to 9)
        assertThat(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8).findTwoLargestNumber()).isEqualTo(7 to 8)
        assertThat(listOf(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1).findTwoLargestNumber()).isEqualTo(9 to 2)
    }

    @Test
    fun findLargestJoltage() {
        assertThat(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1).findLargestJoltage()).isEqualTo(987654321111)
        assertThat(listOf(8, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9).findLargestJoltage()).isEqualTo(811111111119)
        assertThat(listOf(2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 3, 4, 2, 7, 8).findLargestJoltage()).isEqualTo(434234234278)
        assertThat(listOf(8, 1, 8, 1, 8, 1, 9, 1, 1, 1, 1, 2, 1, 1, 1).findLargestJoltage()).isEqualTo(888911112111)
    }
}
