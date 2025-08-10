package pl.michal_cyran.function_solver.function.domain.numbers_set

data class NumbersInterval(
    val start: Float,
    val end: Float,

    val isStartIncluded: Boolean,
    val isEndIncluded: Boolean,
): NumbersContainer() {
    operator fun plus(other: NumbersInterval): List<NumbersInterval> {
        val first: NumbersInterval
        val second: NumbersInterval

        if (this.start < other.start) {
            first = this
            second = other
        } else if (this.start > other.start) {
            first = other
            second = this
        } else {
            // TODO: not sure
            if (this.isStartIncluded) {
                first = this
                second = other
            } else {
                first = other
                second = this
            }
        }

        // 1e < 2s
        if (first.end < second.start) {
            return listOf(first, second)
        }

        // 1e = 2s
        if (first.end == second.start) {
            val canMerge = first.isEndIncluded || second.isStartIncluded

            return if (canMerge) {
                listOf(
                    NumbersInterval(
                        first.start,
                        second.end,
                        first.isStartIncluded,
                        second.isEndIncluded
                    )
                )
            } else {
                listOf(first, second)
            }
        }

        // 1e > 2s

        // 1e < 2e
        if (first.end < second.end) {
            return listOf(
                NumbersInterval(
                    first.start,
                    second.end,
                    first.isStartIncluded,
                    second.isEndIncluded
                )
            )
        }

        // 1e == 2e
        if (first.end == second.end) {
            return listOf(
                NumbersInterval(
                    first.start,
                    second.end,
                    first.isStartIncluded,
                    first.isEndIncluded || second.isEndIncluded
                )
            )
        }

        // 1e > 2e
        return listOf(first)
    }

    override fun toString(): String {
        return buildString {
            append(if (isStartIncluded) "[" else "(")
            append(start.toInt())
            append(", ")
            append(end.toInt())
            append(if (isEndIncluded) "]" else ")")
        }
    }
}