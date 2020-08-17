package araikovich.inc.araikovichdemo.datasource.utils

sealed class Result<out T> {
    data class Success<out T>(val code: Int, val data: T?) : Result<T>()

    data class Error(
        val exception: Exception,
        val responseCode: Int = 0,
        val responseMessage: String = "-"
    ) : Result<Nothing>()
}