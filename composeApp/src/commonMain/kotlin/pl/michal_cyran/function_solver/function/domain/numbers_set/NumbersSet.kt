package pl.michal_cyran.function_solver.function.domain.numbers_set

data class NumbersSet(
    val numbers: List<Float>,
): NumbersContainer() {
    override fun toString(): String {
        return numbers.joinToString(", ", "{", "}")
    }
}