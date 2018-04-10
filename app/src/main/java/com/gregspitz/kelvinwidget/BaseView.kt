package com.gregspitz.kelvinwidget

/**
 * Interface for all Views
 */
interface BaseView<in T : BasePresenter> {
    fun setPresenter(presenter: T)
}
