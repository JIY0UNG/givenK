package givenk

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GivenTest {
    class TestObject(val stringProperty:String)

    var givenString by Given { "" }
    var obj by Given { TestObject(givenString) }

    @Nested
    @DisplayName("When nothing given")
    inner class Context1 {
        @Test
        @DisplayName("stringProperty of TestObject is empty string")
        fun test1() {
            assertEquals("", obj.stringProperty)
        }
    }

    @Nested
    @DisplayName("When givenString is \"HelloWorld\"")
    inner class Context2 {
        @BeforeEach
        fun setUp() {
            givenString = "HelloWorld"
        }

        @Test
        @DisplayName("stringProperty of obj is \"HelloWorld\"")
        fun test2() {
            assertEquals("HelloWorld", obj.stringProperty)
        }
    }
}
