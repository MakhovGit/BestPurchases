package org.purchases.best.ui.base.reducer

interface Reducer<S, R> {
    fun reduce(currentState: S, result: R): S
}