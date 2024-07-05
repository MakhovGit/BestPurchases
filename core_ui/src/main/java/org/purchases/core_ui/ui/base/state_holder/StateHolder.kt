package org.purchases.core_ui.ui.base.state_holder

interface StateHolder<R> {
    fun update(result: R)
}