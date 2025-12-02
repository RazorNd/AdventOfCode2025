package ru.razornd.aoc.day2

import org.assertj.core.api.Assertions.BOOLEAN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Test {
    @Test
    fun readInput() {
        val actual = readInput("11-22,95-115,998-1012,1188511880-1188511890\n".reader()).toList()

        assertThat(actual).containsExactly(11L..22, 95L..115, 998L..1012, 1188511880L..1188511890)
    }

    @Test
    fun isTwice() {
        assertThat(11L).extracting({ it.isTwice }, BOOLEAN).isTrue()
        assertThat(22L).extracting({ it.isTwice }, BOOLEAN).isTrue()
        assertThat(10L).extracting({ it.isTwice }, BOOLEAN).isFalse()
        assertThat(101L).extracting({ it.isTwice }, BOOLEAN).isFalse()
        assertThat(6464L).extracting({ it.isTwice }, BOOLEAN).isTrue()
        assertThat(123123L).extracting({ it.isTwice }, BOOLEAN).isTrue()
        assertThat(38593859L).extracting({ it.isTwice }, BOOLEAN).isTrue()
    }

    @Test
    fun isRepeated() {
        assertThat(12341234L).extracting({ it.isRepeated }, BOOLEAN).isTrue()
        assertThat(123123123L).extracting({ it.isRepeated }, BOOLEAN).isTrue()
        assertThat(10L).extracting({ it.isRepeated }, BOOLEAN).isFalse()
        assertThat(101L).extracting({ it.isRepeated }, BOOLEAN).isFalse()
        assertThat(1212121212L).extracting({ it.isRepeated }, BOOLEAN).isTrue()
        assertThat(1111111L).extracting({ it.isRepeated }, BOOLEAN).isTrue()
    }
}
