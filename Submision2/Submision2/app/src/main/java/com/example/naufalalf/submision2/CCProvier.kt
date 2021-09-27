package com.example.naufalalf.submision2

import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

open class CCProvier {
    open val main : CoroutineContext by lazy {UI}
}