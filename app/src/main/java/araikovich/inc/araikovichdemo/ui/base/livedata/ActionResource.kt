package araikovich.inc.araikovichdemo.ui.base.livedata

data class ActionResource<out T>(
    val state: ActionState,
    val data: T? = null,
    val message: String? = null
)