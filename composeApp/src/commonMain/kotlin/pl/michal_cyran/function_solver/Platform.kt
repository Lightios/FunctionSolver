package pl.michal_cyran.function_solver

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform