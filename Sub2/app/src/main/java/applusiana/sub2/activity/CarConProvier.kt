package applusiana.sub2.activity

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CarConProvier{
    open val main: CoroutineContext by lazy { UI }
}
