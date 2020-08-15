package araikovich.inc.araikovichdemo.datasource.utils

import java.net.HttpURLConnection

sealed class Result<out T> {
    data class Success<out T>(val code: Int, val data: T?) : Result<T>() {
        fun isCodeSuccess(): Boolean =
            code == HttpURLConnection.HTTP_OK || code == HttpURLConnection.HTTP_CREATED
    }

    data class Error(
        val exception: Exception,
        val responseCode: Int = 0,
        val responseMessage: String = "-"
    ) : Result<Nothing>()
}