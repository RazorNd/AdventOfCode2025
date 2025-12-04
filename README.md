Advent of Code 2025 — Kotlin

This repository contains solutions for Advent of Code 2025 written in Kotlin. Each day lives in its own package, inputs are stored under `input/`, and solutions are verified via unit tests.

Made for local runs in IntelliJ IDEA/JetBrains IDEs and via Gradle.

—

What’s inside
- Language: Kotlin (JVM)
- Build tool: Gradle (wrapper included)
- Tests: JUnit 5 + AssertJ
- Project structure:
  - `src/main/kotlin/ru/razornd/aoc/dayN/solution.kt` — solutions for day N
  - `src/test/kotlin/ru/razornd/aoc/dayN/DayNTest.kt` — tests for day N
  - `input/dayN-test.txt` — sample input from the problem statement
  - `input/dayN-puzzle.txt` — full puzzle input

Example: Day 4
- Code: `src/main/kotlin/ru/razornd/aoc/day4/solution.kt`
- Tests: `src/test/kotlin/ru/razornd/aoc/day4/Day4Test.kt`
- Data: `input/day4-test.txt`, `input/day4-puzzle.txt`

Requirements
- JDK 24 (Gradle is configured with Java 24 target compatibility)
- Any recent IntelliJ IDEA (Community/Ultimate) or a local Gradle installation

Quick start
1) Run all tests
```
./gradlew test
```

2) Run tests for a specific day (example: Day 4)
```
./gradlew test --tests ru.razornd.aoc.day4.Day4Test
```

3) Run a day’s `main` (if present) from the IDE
- Open the project in IntelliJ IDEA
- Locate the target day’s `solution.kt`
- Run the `main()` function (most days print answers for test/puzzle inputs)

Input files
- Put files into `input/` following the `dayN-test.txt` and `dayN-puzzle.txt` pattern
- Paths inside solutions are relative to the project root, e.g., `input/day4-puzzle.txt`

Adding a new day
1) Create `src/main/kotlin/ru/razornd/aoc/dayN/solution.kt`
2) Create a test `src/test/kotlin/ru/razornd/aoc/dayN/DayNTest.kt`
3) Add inputs: `input/dayN-test.txt` and `input/dayN-puzzle.txt`
4) Verify with `./gradlew test`

Useful Gradle commands
- Build without tests: `./gradlew build -x test`
- Clean: `./gradlew clean`

License

[![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

Unless stated otherwise, this project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
