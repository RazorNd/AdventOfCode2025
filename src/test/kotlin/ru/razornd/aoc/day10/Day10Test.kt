package ru.razornd.aoc.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun pressOn() {
        val indicators = Indicators(4)

        val button = Button(0)

        assertThat(button pressOn indicators).hasToString("[#...]")
    }

    @Test
    fun findFewestButtonPress() {
        val required = Indicators(4, 1, 2)

        val buttons = listOf(
            Button(3),
            Button(1, 3),
            Button(2),
            Button(2, 3),
            Button(0, 2),
            Button(0, 1),
        )

        val actual = findFewestButtonPress(required, buttons)

        assertThat(actual).isEqualTo(2)
    }

    @Test
    fun parseMachine() {
        val machine = parseMachine("[.##.] (3) (1,3) (2) (2,3) (0,2) (0,1) {3,5,4,7}")

        assertThat(machine)
            .usingRecursiveComparison()
            .isEqualTo(
                Machine(
                    Indicators(4, 1, 2),
                    listOf(
                        Button(3),
                        Button(1, 3),
                        Button(2),
                        Button(2, 3),
                        Button(0, 2),
                        Button(0, 1),
                    ),
                    listOf(3, 5, 4, 7)
                )
            )
    }
}
