package com.github.stefankoppier.builder.dsl.containers

import com.github.stefankoppier.builder.dsl.BuilderDsl
import com.github.stefankoppier.builder.dsl.Faker

/**
 * DSL for building nullable objects using the given [Faker] and element factory [B].
 *
 * For example:
 * ```kotlin
 * NullableBuilder(IntBuilder().constant(1))()
 * ```
 * will result in nullable [T] which is either the constant value `1` or `null` where each option having a probability
 * of 50%.
 */
class NullableBuilder<T, B>(private val factory: B, faker: Faker = Faker()) : BuilderDsl<T?> where B : BuilderDsl<T> {

    private var isNull = faker.boolean()

    /**
     * Generates the object according to the provided instructions.
     *
     * @return A new [List].
     */
    override fun invoke(): T? {
        return if (isNull) factory() else null
    }

    /**
     * Instruct the builder to always generate a null value.
     *
     * @return The DSL itself.
     */
    fun asNull(): NullableBuilder<T, B> {
        this.isNull = false
        return this
    }

    /**
     * Instruct the builder to always generate a value generated by [factory].
     *
     * @return The DSL itself.
     */
    fun asNonNull(): NullableBuilder<T, B> {
        this.isNull = true
        return this
    }
}
