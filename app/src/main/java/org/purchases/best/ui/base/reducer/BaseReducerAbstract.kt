package org.purchases.best.ui.base.reducer

abstract class BaseReducerAbstract<S, R> : Reducer<S, R> {
    abstract override fun reduce(currentState: S, result: R): S

    companion object {
        const val ERROR_MESSAGE = "Invalid event type! Don't know, how reduce this:"
    }
}