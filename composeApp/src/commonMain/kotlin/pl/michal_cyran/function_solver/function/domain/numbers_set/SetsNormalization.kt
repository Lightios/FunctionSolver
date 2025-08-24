package pl.michal_cyran.function_solver.function.domain.numbers_set

fun List<NumbersSet>.normalize(): NumbersSet? {
    val points = this.flatMap { it.numbers }.distinct().sorted()

    if (points.isEmpty())
        return null

    return NumbersSet(points)
}