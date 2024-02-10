package it.alex.lezione3

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * Created by magaz on 10/02/24
 */
class Exercise3Test {
    private lateinit var sut: it.alex.lezione4.Computer

    @BeforeEach
    fun setUp() {
        sut = Computer("Apple", "MacBook Pro", 1729.99f)
    }

    @Test
    fun `check toString`() {
        // Prepare
        val expected = "Apple - MacBook Pro - €1729,99"

        // Act
        val actual = sut.toString()

        // Verify
        println(actual)
        assert(actual == expected)
    }
}