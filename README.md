# givenK
Lazy variable evaluation for test in Kotlin (inspired by [Given2](https://github.com/tatyshev/given2))
![givenK](https://user-images.githubusercontent.com/98736689/216838711-8ee026bf-1a6f-4d27-be8e-440b0cf2ce03.png)


## Installation
Step 1. Add the JitPack repository to your build file
```bash
allprojects {
    repositories {
        ...
        maven("https://jitpack.io")
    }
}
```

Step2. Add the dependency
```bash
dependencies {
    implementation("com.github.JIY0UNG:givenK:1.0.0")
}
```

## Usage
```kotlin
class Student(
    val name: String,
    val score: Int,
    val subject: String,
)

fun grade(
    student: Student,
): String = if (student.score in 50..100) "PASS" else "FAIL"

class ExampleTest {
    var score by Given { 100 }
    var student by Given {
        Student(
            name = "John Doe",
            score = score,
            subject = "algorithm",
        )
    }

    @Test
    @DisplayName("Grade should be PASS when score is 100")
    fun test1() {
        score = 100
        assertEquals("PASS", grade(student))
    }

    @Test
    @DisplayName("Grade should be PASS when score is 70")
    fun test2() {
        score = 70
        assertEquals("PASS", grade(student))
    }

    @Test
    @DisplayName("Grade should be FAIL when score is 40")
    fun test3() {
        score = 40
        assertEquals("FAIL", grade(student))
    }
}
```

## More examples
If you use givenK with [Nested annotation](https://junit.org/junit5/docs/5.4.1/api/org/junit/jupiter/api/Nested.html) in [JUnit 5](https://junit.org/junit5/) or [DescribeSpec](https://kotest.io/docs/framework/testing-styles.html#describe-spec) in [Kotest](https://github.com/kotest/kotest), you can see the changed values more clearly.

```kotlin
// Using Nested annotation in JUnit 5
@Nested
@DisplayName("Describe Grade")
inner class Describe {

    @Nested
    @DisplayName("When score is 100")
    inner class Context1 {

        @BeforeEach
        fun setUp() {
            score = 100
        }

        @Test
        @DisplayName("It should be PASS")
        fun test() {
            assertEquals("PASS", grade(student))
        }
    }

    @Nested
    @DisplayName("When score is 40")
    inner class Context2 {

        @BeforeEach
        fun setUp() {
            score = 40
        }

        @Test
        @DisplayName("It should be FAIL")
        fun test() {
            assertEquals("FAIL", grade(student))
        }
    }
}
```

```kotlin
// Using DescribeSpec in Kotest
init {
    describe("Describe Grade") {
        context("When score is 100") {
            beforeEach { score = 100 }

            it("It should be PASS") {
                grade(student) shouldBe "PASS"
            }
        }

        context("When score is 40") {
            beforeEach { score = 40 }

            it("It should be FAIL") {
                grade(student) shouldBe "FAIL"
            }
        }
    }
}
```
