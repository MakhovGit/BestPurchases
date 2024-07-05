package org.purchases.core_ui.ui.base.reducer

interface Reducer<S, R> {
    fun reduce(currentState: S, result: R): S
}