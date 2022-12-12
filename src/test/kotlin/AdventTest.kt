import io.kotest.assertions.assertSoftly
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

    "Day 5" - {
        "part 5" - {
            "test" {
                day05part1(readInput("day05_input_test")) shouldBe "CMZ"
            }
            "actual" {
                day05part1(readInput("day05_input")) shouldBe "TLNGFGMFN"
            }
        }
        "part 2" - {
            "test" {
                day05part2(readInput("day05_input_test")) shouldBe "MCD"
            }
            "actual" {
                day05part2(readInput("day05_input")) shouldBe "FGLQJCMBD"
            }
        }
    }

    "Day 6" - {
        "part 6" - {
            "test" {
                assertSoftly {
                    day06part1("bvwbjplbgvbhsrlpgdmjqwftvncz") shouldBe 5
                    day06part1("nppdvjthqldpwncqszvftbrmjlhg") shouldBe 6
                    day06part1("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") shouldBe 10
                    day06part1("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") shouldBe 11
                }
            }
            "actual" {
                day06part1(readInput("day06_input").first()) shouldBe 1538
            }
        }
        "part 2" - {
            "test" {
                day06part2("mjqjpqmgbljsphdztnvjfqwrcgsmlb") shouldBe 19
                day06part2("bvwbjplbgvbhsrlpgdmjqwftvncz") shouldBe 23
                day06part2("nppdvjthqldpwncqszvftbrmjlhg") shouldBe 23
                day06part2("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") shouldBe 29
                day06part2("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") shouldBe 26
            }
            "actual" {
                day06part2(readInput("day06_input").first()) shouldBe 2315
            }
        }
    }

    "Day 7" - {
        "part 7" - {
            "test" {
                day07part1(readInput("day07_input_test")) shouldBe 95437
            }
            "actual" {
                day07part1(readInput("day07_input")) shouldBe 1391690
            }
        }
        "part 2" - {
            "test" {
                day07part2(readInput("day07_input_test")) shouldBe 24933642
            }
            "actual" {
                day07part2(readInput("day07_input")) shouldBe 5469168
            }
        }
    }

    "Day 8" - {
        "part 8" - {
            "test" {
                day08part1(readInput("day08_input_test")) shouldBe 21
            }
            "actual" {
                day08part1(readInput("day08_input")) shouldBe 1763
            }
        }
        "part 2" - {
            "test" {
                day08part2(readInput("day08_input_test")) shouldBe 8
            }
            "actual" {
                day08part2(readInput("day08_input")) shouldBe 671160
            }
        }
    }

    "Day 9" - {
        "part 9" - {
            "test" {
                day09part1(readInput("day09_input_test")) shouldBe 13
            }
            "actual" {
                day09part1(readInput("day09_input")) shouldBe 6269
            }
        }
        "part 2" - {
            "test" {
                day09part2(readInput("day09_input_test_2")) shouldBe 36
            }
            "actual" {
                day09part2(readInput("day09_input")) shouldBe 2557
            }
        }
    }

    "Day 10" - {
        "part 10" - {
            "test" {
                day10part1(readInput("day10_input_test")) shouldBe 13140
            }
            "actual" {
                day10part1(readInput("day10_input")) shouldBe 14060
            }
        }
        "part 2" - {
            "test" {
                day10part2(readInput("day10_input_test")) shouldBe """
##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....""".trim()
            }
            "actual" {
                day10part2(readInput("day10_input")) shouldBe """
###...##..###..#..#.####.#..#.####...##.
#..#.#..#.#..#.#.#..#....#.#..#.......#.
#..#.#..#.#..#.##...###..##...###.....#.
###..####.###..#.#..#....#.#..#.......#.
#....#..#.#....#.#..#....#.#..#....#..#.
#....#..#.#....#..#.#....#..#.####..##..""".trim()
            }
        }
    }

    "Day 11" - {
        "part 11" - {
            "test" {
                day11part1(readInput("day11_input_test")) shouldBe 10605
            }
            "actual" {
                day11part1(readInput("day11_input")) shouldBe 50616
            }
        }
        "part 2" - {
            "test" {
                day11part2(readInput("day11_input_test")) shouldBe 2713310158
            }
            "actual" {
                day11part2(readInput("day11_input")) shouldBe 11309046332L
            }
        }
    }

    "Day 12" - {
        "part 12" - {
            "test" {
                day12part1(readInput("day12_input_test")) shouldBe 31
            }
            "actual" {
                day12part1(readInput("day12_input")) shouldBe 437
            }
        }
        "part 2" - {
            "test" {
                day12part2(readInput("day12_input_test")) shouldBe 29
            }
            "actual" {
                day12part2(readInput("day12_input")) shouldBe 430
            }
        }
    }

//    "Day X" - {
//        "part X" - {
//            "test" {
//                dayXpart1(readInput("dayX_input_test")) shouldBe 0
//            }
//            "actual" {
//                dayXpart1(readInput("dayX_input")) shouldBe 0
//            }
//        }
//        "part 2" - {
//            "test" {
//                dayXpart2(readInput("dayX_input_test")) shouldBe 0
//            }
//            "actual" {
//                dayXpart2(readInput("dayX_input")) shouldBe 0
//            }
//        }
//    }
})
