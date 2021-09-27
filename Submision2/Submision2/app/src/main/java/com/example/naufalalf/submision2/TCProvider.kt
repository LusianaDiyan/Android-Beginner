package com.example.naufalalf.submision2

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TCProvider : CCProvier() {
    override val main: CoroutineContext=Unconfined
}