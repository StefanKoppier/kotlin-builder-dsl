package com.github.stefankoppier.builder.dsl.primitives

import com.github.stefankoppier.builder.dsl.BuilderDsl
import com.github.stefankoppier.builder.dsl.Faker

class FloatBuilderDsl(private val faker: Faker = Faker()) : BuilderDsl<Float> {

    private var constant: Float? = null

    override fun invoke(): Float {
        return constant ?: faker.nextFloat()
    }

    fun constant(value: Float): FloatBuilderDsl {
        constant = value
        return this
    }
}

fun Float.Companion.of(
    transform: FloatBuilderDsl.() -> FloatBuilderDsl = { FloatBuilderDsl() }
): Float {
    return transform(FloatBuilderDsl())()
}
