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
class MyTest {
    var foo by Given { "" }

    @Nested
    @DisplayName("When nothing given")
    inner class Test1 {
    
        @Test
        @DisplayName("foo should be empty String")
        fun test() {
            assertEquals("", foo)
        }
    }

    @Nested
    @DisplayName("When \"Hello World\" given")
    inner class Test2 {
    
        @BeforeEach
        fun setUp() {
            foo = "Hello World"
        }

        @Test
        @DisplayName("foo should be \"Hello World\"")
        fun test() {
            assertEquals("Hello World", foo)
        }
    }
}
```
