package com.gregspitz.kelvinwidget

/**
 * abstract super class for all Use Cases
 */
abstract class UseCase<Q : UseCase.RequestValues, P : UseCase.ResponseValue> {

    private lateinit var requestValues: Q
    private lateinit var useCaseCallback: UseCaseCallback<P>

    fun setRequestValues(requestValues: Q) {
        this.requestValues = requestValues
    }

    fun getRequestValues() : Q {
        return requestValues
    }

    fun setUseCaseCallback(useCaseCallback: UseCaseCallback<P>) {
        this.useCaseCallback = useCaseCallback
    }

    fun getUseCaseCallback() : UseCaseCallback<P> {
        return useCaseCallback
    }

    fun run() { executeUseCase(requestValues) }

    protected abstract fun executeUseCase(requestValues: Q)

    interface RequestValues

    interface ResponseValue

    interface UseCaseCallback<R> {

        fun onSuccess(response: R)

        fun onError()
    }
}
