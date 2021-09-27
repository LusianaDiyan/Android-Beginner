package applusiana.sub2.activity

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TCProvider : CarConProvier() {
    override val main: CoroutineContext = Unconfined
}