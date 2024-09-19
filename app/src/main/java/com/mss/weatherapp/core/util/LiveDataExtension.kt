package com.mss.weatherapp.core.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun LiveData<Boolean>.observeAsAction(
    lifecycleOwner: LifecycleOwner,
    action: () -> Unit,
    onActionResolved: () -> Unit
) {
    this.removeObservers(lifecycleOwner)
    this.observe(lifecycleOwner) { isActionRequested ->
        if (isActionRequested == true) {
            action.invoke()
            onActionResolved.invoke()
        }
    }
}

fun <T> LiveData<T?>.observeAsAction(
    lifecycleOwner: LifecycleOwner,
    action: (T) -> Unit,
    onActionResolved: () -> Unit
) {
    this.removeObservers(lifecycleOwner)
    this.observe(lifecycleOwner) { actionParams ->
        if (actionParams != null) {
            action.invoke(actionParams)
            onActionResolved.invoke()
        }
    }
}