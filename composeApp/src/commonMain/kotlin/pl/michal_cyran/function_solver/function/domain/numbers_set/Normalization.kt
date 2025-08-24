package pl.michal_cyran.function_solver.function.domain.numbers_set

fun List<NumbersContainer>.normalize(): List<NumbersContainer> {
    val intervals = this.filterIsInstance<NumbersInterval>().toList().normalize()
    val set = this.filterIsInstance<NumbersSet>().toList().normalize()

    val result: MutableList<NumbersContainer> = intervals.toMutableList()



    if (set != null) {
        val reducedNumbers = mutableListOf<Float>()
        for (number in set.numbers) {
            if (!intervals.any { interval ->
                    (number > interval.start && number < interval.end) ||
                            (number == interval.start && interval.isStartIncluded) ||
                            (number == interval.end && interval.isEndIncluded)
                }
            ) {
                reducedNumbers.add(number)
            }
        }

        if (reducedNumbers.isNotEmpty())
            result += NumbersSet(reducedNumbers)
    }

    return result
}