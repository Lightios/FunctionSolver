package pl.michal_cyran.function_solver.function.domain.numbers_set

fun List<NumbersInterval>.normalize(): List<NumbersInterval> {
    if (this.isEmpty()) return emptyList()

    val sortedIntervals = this.sortedBy { it.start }.toMutableList()
    var needsToRestart = false
    var i = 0

    while (i < sortedIntervals.size - 1) {
        val sum = sortedIntervals[i] + sortedIntervals[i + 1]
        if (sum.size == 1) {
            needsToRestart = true
            sortedIntervals.removeAt(i)
            sortedIntervals.removeAt(i) // same index bcs everything shifted left
            sortedIntervals.add(i, sum.first())
        } else {
            i++
        }

        if (needsToRestart) {
            i = 0
            needsToRestart = false
        }
    }

    return sortedIntervals
}