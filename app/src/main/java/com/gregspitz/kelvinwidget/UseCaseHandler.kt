package com.gregspitz.kelvinwidget

/**
 * Runs use cases
 */
class UseCaseHandler(private val useCaseScheduler: UseCaseScheduler) {

    companion object : SingletonHolder<UseCaseHandler, UseCaseScheduler>(::UseCaseHandler)

    private fun runnable(f: () -> Unit): Runnable = Runnable { f() }

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(
            useCase: UseCase<T, R>, values: T, callback: UseCase.UseCaseCallback<R>) {
        useCase.setRequestValues(values)
        useCase.setUseCaseCallback(UiCallbackWrapper(callback, this))
        useCaseScheduler.execute(runnable { useCase.run() })
    }

    fun <V : UseCase.ResponseValue> notifyResponse(
            response: V, callback: UseCase.UseCaseCallback<V>) {
        useCaseScheduler.notifyResponse(response, callback)
    }

    fun <V : UseCase.ResponseValue> notifyError(callback: UseCase.UseCaseCallback<V>) {
        useCaseScheduler.onError(callback)
    }

    class UiCallbackWrapper<V : UseCase.ResponseValue>(
            private val callback: UseCase.UseCaseCallback<V>,
            private val useCaseHandler: UseCaseHandler
    ) : UseCase.UseCaseCallback<V> {
        override fun onSuccess(response: V) {
            useCaseHandler.notifyResponse(response, callback)
        }

        override fun onError() {
            useCaseHandler.notifyError(callback)
        }
    }
}
