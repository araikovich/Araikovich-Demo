package araikovich.inc.araikovichdemo.ui.base.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Currently doesn't use, but very useful for feature features
 */
fun <T> MutableLiveData<ActionResource<T>>.setSuccess(data: T?) =
    postValue(ActionResource(ActionState.SUCCESS, data))

fun <T> MutableLiveData<ActionResource<T>>.setLoading() =
    postValue(ActionResource(ActionState.LOADING, value?.data))

fun <T> MutableLiveData<ActionResource<T>>.setError(message: String? = null) =
    postValue(ActionResource(ActionState.ERROR, value?.data, message))