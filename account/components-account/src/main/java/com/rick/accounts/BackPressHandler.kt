package com.rick.accounts

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.*

/*we create an OnBackPressedCallback and add it to the OnBackPressedDispatcher that controls
 dispatching system back presses. We enable the callback whenever our Composable is recomposed,
  which disables other internal callbacks responsible for back press handling.
  The callback is added on any lifecycle owner change and removed on dispose. */
@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
){
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose { backCallback.remove() }
    }
}