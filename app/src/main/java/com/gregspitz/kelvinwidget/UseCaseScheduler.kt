package com.gregspitz.kelvinwidget

/**
 * interface for all UseCaseSchedulers
 */
interface UseCaseScheduler {

    fun execute(runnable: Runnable)

    fun <V : UseCase.ResponseValue> notifyResponse(
            response: V, useCaseCallback: UseCase.UseCaseCallback<V>)

    fun <V : UseCase.ResponseValue> onError(useCaseCallback: UseCase.UseCaseCallback<V>)
}
