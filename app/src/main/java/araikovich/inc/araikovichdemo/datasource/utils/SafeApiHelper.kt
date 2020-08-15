package araikovich.inc.araikovichdemo.datasource.utils

import android.util.Log
import retrofit2.Response

interface SafeApiHelper {

    suspend fun <T> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {
        val result: Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is Result.Success ->
                data = result.data
            is Result.Error -> {
                val errorInfoMessage = "errorMessage: $errorMessage " +
                        "| resultMessage: -> " +
                        "Exception: ${result.exception.message.orEmpty()}, " +
                        "Code: ${result.responseCode}, " +
                        "Message: ${result.responseMessage}"
                Log.e(javaClass.simpleName, errorInfoMessage)
            }
        }

        return data
    }

    suspend fun <T> safeApiResult(call: suspend () -> Response<T>, errorMessage: String): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) {
                Result.Success(response.code(), response.body())
            } else {
                Result.Error(
                    Exception(errorMessage),
                    response.code(),
                    response.message()
                )
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}