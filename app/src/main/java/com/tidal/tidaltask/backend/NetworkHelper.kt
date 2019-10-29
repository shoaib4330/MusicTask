package com.tidal.tidaltask.backend

import com.tidal.tidaltask.util.Constants
import com.tidal.tidaltask.util.NetworkConstants
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject


interface ServiceCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(
        error: String,
        statusCode: Int = 0,
        errorCodeString: String = Constants.EMPTY_STRING
    )
}

class NetworkHelper @Inject constructor() {

    var disposable: Disposable?

    init {
        disposable = null
    }

    fun <T> remoteCall(call: Single<Response<T>>, callback: ServiceCallback<T>) {
        call.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<T>> {
                override fun onSuccess(response: Response<T>) = if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onSuccess(it)
                    } ?: run {
                        callback.onFailure(response.message())
                    }
                } else {
                    callback.onFailure(response.message())
                }

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onError(e: Throwable) {
                    e.message?.let {
                        callback.onFailure(it)
                    } ?: run {
                        callback.onFailure(NetworkConstants.REQUEST_FAILURE_MESSAGE)
                    }
                }
            })
    }

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }
}
