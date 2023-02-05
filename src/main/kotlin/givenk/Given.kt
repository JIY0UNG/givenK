package givenk

import kotlin.reflect.KProperty

class Given<T>(private var getter: () -> T) {
    operator fun getValue(
        thisRef: Any?,
        property: KProperty<*>
    ): T = getter()

    operator fun setValue(
        thisRef: Any?,
        property: KProperty<*>,
        value: T
    ) {
        this.getter = { value }
    }
}
