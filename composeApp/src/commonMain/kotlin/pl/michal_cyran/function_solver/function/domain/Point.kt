package pl.michal_cyran.function_solver.function.domain

data class Point(
    val x: Float,
    val y: Float,
    val including: Boolean = true,
) {
    constructor(x: Int, y: Int): this(x.toFloat(), y.toFloat())
}