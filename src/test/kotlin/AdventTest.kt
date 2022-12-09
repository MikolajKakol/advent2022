import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class AdventTest : FreeSpec({

    "Day 1" - {
        "part 1" - {
            "test" {
                day01part1(readInput("day01_input_test")) shouldBe 24000
            }
            "actual" {
                day01part1(readInput("day01_input")) shouldBe 66616
            }
        }
        "part 2" - {
            "test" {
                day01part2(readInput("day01_input_test")) shouldBe 41000
            }
            "actual" {
                day01part2(readInput("day01_input")) shouldBe 199172
            }
        }
    }

    "Day 2" - {
        "part 1" - {
            "test" {
                day02part1(readInput("day02_input_test")) shouldBe 15
            }
            "actual" {
                day02part1(readInput("day02_input")) shouldBe 13924
            }
        }
        "part 2" - {
            "test" {
                day02part2(readInput("day02_input_test")) shouldBe 12
            }
            "actual" {
                day02part2(readInput("day02_input")) shouldBe 13448
            }
        }
    }

    "Day 3" - {
        "part 3" - {
            "test" {
                day03part1(readInput("day03_input_test")) shouldBe 157
            }
            "actual" {
                day03part1(readInput("day03_input")) shouldBe 7875
            }
        }
        "part 2" - {
            "test" {
                day03part2(readInput("day03_input_test")) shouldBe 70
            }
            "actual" {
                day03part2(readInput("day03_input")) shouldBe 2479
            }
        }
    }

    "Day 4" - {
        "part 4" - {
            "test" {
                day04part1(readInput("day04_input_test")) shouldBe 2
            }
            "actual" {
                day04part1(readInput("day04_input")) shouldBe 513
            }
        }
        "part 2" - {
            "test" {
                day04part2(readInput("day04_input_test")) shouldBe 4
            }
            "actual" {
                day04part2(readInput("day04_input")) shouldBe 878
            }
        }
    }

    
//    "Day X" - {
//        "part X" - {
//            "test" {
//                day0Xpart1(readInput("day0X_input_test")) shouldBe 0
//            }
//            "actual" {
//                day0Xpart1(readInput("day0X_input")) shouldBe 0
//            }
//        }
//        "part 2" - {
//            "test" {
//                day0Xpart2(readInput("day0X_input_test")) shouldBe 0
//            }
//            "actual" {
//                day0Xpart2(readInput("day0X_input")) shouldBe 0
//            }
//        }
//    }
})
