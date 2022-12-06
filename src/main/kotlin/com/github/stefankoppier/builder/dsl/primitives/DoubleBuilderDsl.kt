package com.github.stefankoppier.builder.dsl.primitives

import com.github.stefankoppier.builder.dsl.BuilderDsl
import com.github.stefankoppier.builder.dsl.Faker

class DoubleBuilderDsl(private val faker: Faker = Faker()) : BuilderDsl<Double> {

    private var constant: Double? = null

    override fun invoke(): Double {
        return constant ?: faker.nextDouble()
    }

    fun constant(value: Double): DoubleBuilderDsl {
        constant = value
        return this
    }
}

fun Double.Companion.of(
    transform: DoubleBuilderDsl.() -> DoubleBuilderDsl = { DoubleBuilderDsl() }
): Double {
    return transform(DoubleBuilderDsl())()
}
